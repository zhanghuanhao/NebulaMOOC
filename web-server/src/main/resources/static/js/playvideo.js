var player_width;
var play_height;
var on_moveword = true;//是否开启弹幕
var showchat = $("#textArea");
var wordWeb;
var minwidth;
var liveId = window.location.href.split("=")[1];
var liveInfo;

function init() {
    document.getElementsByTagName("body")[0].style.zoom = 1;
    minwidth = $('#sw').width();
    player_width = minwidth * 0.7;
    play_height = player_width * window.screen.height / window.screen.width + 80;
    $('#sw').css("min-width", minwidth + 'px');
    $('#sw').css("width", minwidth + 'px');
    $('#playarea').css("height", play_height + 'px');
    $('#playarea').css("width", player_width + 'px');
    $('#textArea').css("height", play_height + 60 + 'px');
    $('#textArea').css("width", minwidth * 0.28 + 'px');
    $('.sendarea').css("top", play_height + 10 + 'px');
    $('#word').css("width", $('#word').parent('.input-top').width() - 50 + 'px');


    getLive({id: liveId}, function (data) {
        if (data.code == 100) {
            liveInfo = data.data;
            $('#title').html(liveInfo.title);
            $('#introduction').html(liveInfo.introduction);
            $('#tc-pic').attr('src', 'https://nebula-head.oss-cn-shenzhen.aliyuncs.com/' + liveInfo.userInfo.headUrl + '/head100');
            $('#teacher-name').html(liveInfo.userInfo.nickName);
            var time = new Date(liveInfo.createdTime);
            var createdtime = time.getFullYear() + "-" + filterNum(time.getMonth() + 1) + "-" + filterNum(time.getDate()) + " "
                + filterNum(time.getHours()) + ":" + filterNum(time.getMinutes());
            $('#create_time').html(createdtime);
        } else {
            toastr.warning('加载失败');
        }

    });


    flashChecker();
    loadplayer();
    webSocketConnect();

}

//下拉菜单
var usermenu = $('#user-menu');
$('#user').hover(function () {
    usermenu.slideDown();
}, function () {
    usermenu.hide();
});
usermenu.hover(function () {
    usermenu.show();
}, function () {
    usermenu.slideUp();
});

//flash检查
function flashChecker() {
    var hasFlash = 0;              //是否安装了flash
    var flashVersion = 0;          //flash版本
    var isIE = /*@cc_on!@*/0;      //是否IE浏览器

    if (isIE) {
        var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');
        if (swf) {
            hasFlash = 1;
            VSwf = swf.GetVariable("$version");
            flashVersion = parseInt(VSwf.split(" ")[1].split(",")[0]);
        }
    } else {
        if (navigator.plugins && navigator.plugins.length > 0) {
            var swf = navigator.plugins["Shockwave Flash"];
            if (swf) {
                hasFlash = 1;
                var words = swf.description.split(" ");
                for (var i = 0; i < words.length; ++i) {
                    if (isNaN(parseInt(words[i]))) continue;
                    flashVersion = parseInt(words[i]);
                }
            }
        }
    }
    if (hasFlash) {
        document.getElementsByClassName("openFlash")[0].style.display = "none";
    } else {
        document.getElementsByClassName("openFlash")[0].style.display = "block";
    }

}

//加载播放器
function loadplayer() {
    jwplayer.key = "iP+vLYU9H5KyhZeGt5eVuJJIoULUjltoaMeHXg==";
    jwplayer('myplayer').setup({
        flashplayer: "js/jwplayer.flash.swf",
        file: 'rtmp://' + window.location.host + '/live/' + liveId,
        autostart: true,
        bufferlength: 1,
        width: player_width,
        height: play_height
    });
}


/* 弹幕 */

//连接弹幕服务器
function webSocketConnect() {
    var wsUri = "wss://" + window.location.hostname + ":9080/websocket";
    wordWeb = new WebSocket(wsUri);
    wordWeb.binaryType = "arraybuffer";
    wordWeb.onopen = function (ev) {
        toastr.success('连接弹幕服务器成功！');
    };
    wordWeb.onmessage = function (ev) {
        var response = proto.response.deserializeBinary(ev.data);
        if (response.getCode() == 100) {
            if (on_moveword) creatMoveword(response.getMsg(), response.getColor(), response.getSize());
            showmsg(response.getNickname(), response.getMsg());
        } else if (response.getCode() == 301) {
            toastr.warning('请登录以发送弹幕！');
        } else if (response.getCode() == 302) {
            // 非法操作
            toastr.error('警告：存在非法操作！');
        } else
            toastr.error(response.getMsg());
    }
}


//让弹幕动起来
var moveObj = function (obj, Color) {
    var topMax = $("#showWords").height();

    var fontPosition = $("#fontposition option:selected").val();
    var _top = Math.floor(1 / 3 * topMax * (Math.random() + parseInt(fontPosition))); //设置top初始位置为面板高度内的随机数
    if (_top + obj.height() >= topMax) {
        _top -= obj.height();
    }

    var _left = $("#showWords").width(); //设置left初始位置位于显示面板最右侧

    var colorInt = parseInt(Color);
    var b = colorInt % 1000;
    colorInt = (colorInt - b) / 1000;
    var g = colorInt % 1000;
    colorInt = (colorInt - g) / 1000;
    var r = colorInt % 1000;


    //初始化消息位置
    obj.css({
        "top": _top,
        "left": _left,
        "color": "rgb(" + r + "," + g + "," + b + ")",
        "position": "absolute"
    });

    var time = 10000;
    //进行动画，动画结束后通过回调函数把消息从面板中删除
    obj.animate({
        left: -obj.width()
    }, time, function () {
        obj.remove();
    });
};

//发送弹幕
$("#addWords").click(function () {
    var word = $("#word").html(); //获取输入框中的值
    //当输入的值不为空时，执行以下代码
    if (word != "" && word.length <= 30) {
        $("#word").html(""); //清空输入框
        var c = document.getElementById("color").style.backgroundColor;
        var s = c.substring(4, c.indexOf(')')).split(',');
        var mess = new proto.request();
        mess.setSize($("#fontsize option:selected").val());
        mess.setMsg(word);
        mess.setColor(parseInt(s[0]) * 1000000 + parseInt(s[1]) * 1000 + parseInt(s[2]));
        var b = mess.serializeBinary();
        wordWeb.send(b);
    } else {
        if (word.length > 30)
            toastr.warning("最大字符长度为30，请重新输入");
    }
    $("#word").focus(); //将焦点置于输入框
});
document.onkeydown = keyListener;

function keyListener(e) {
    if (e.keyCode == 13) {
        $('#addWords').click();
    }
}

//生成弹幕
function creatMoveword(word, Color, Size) {
    var obj; //为word值生成对象
    switch (Size) {
        case 1:
            obj = $("<span style='font-size: 16px'>" + word + "</span>");
            break;
        case 2:
            obj = $("<span style='font-size: 20px'>" + word + "</span>");
            break;
        case 3:
            obj = $("<span style='font-size: 24px'>" + word + "</span>");
            break;
    }

    $("#showWords").append(obj); //将生成的对象附加到面板上
    moveObj(obj, Color); //调用 moveObj 函数使生成的对象动起来
}

//弹幕开关
$("#move_on").click(function () {
    if (on_moveword) {
        $("#showWords").empty();
        on_moveword = false;
        $("#move_on").val("弹幕:关");
    } else {
        on_moveword = true;
        $("#move_on").val("弹幕:开");
    }
});


function showmsg(name, content) {
    var msg = $("<span><strong>" + name + "：</strong>" + content + "</span>");
    showchat.append(msg);
    $("#textArea").scrollTop($("#textArea")[0].scrollHeight);
}

//emoji-menu
var _table = document.getElementById("_table");
var _row;
var _cell;
var emojilist1 = [];
var j = 0, t = 0;
for (var i = 0; i < 100; i++, j++) {
    emojilist1[i] = String.fromCodePoint(j + parseInt("1f601", 16));
    if (j == 52) j = 63;
    if (j == 69) j = 73;
    if (j == 78) j = 125;
}
for (var i = 0; i < 10; i++) {
    _row = document.createElement("tr");
    document.getElementById("_table").appendChild(_row);
    for (var j = 0; j < 10; j++) {
        _cell = document.createElement("td");
        _cell.id = 'e' + i.toString() + j.toString();
        _cell.innerHTML = emojilist1[t];
        t++;
        _cell.style.width = "30px";
        _cell.style.height = "30px";
        _cell.style.fontSize = "18px";
        _cell.style.cursor = "pointer";
        _cell.style.margin = "0";
        _cell.style.padding = "0";
        _cell.setAttribute("onmouseover", "cellover(this.id)");
        _cell.setAttribute("onmouseout", "cellout(this.id)");
        _row.appendChild(_cell);
        $('#e' + i.toString() + j.toString()).on('click', function () {
            $("#word").focus();
            insertAtCursor(document.getElementById("word"), $('#' + this.id).html());
            $("#word").focus();
            document.getElementById("word").scrollLeft += 30;
        });
    }
}

function cellover(id) {
    $('#' + id).css("font-size", "21px");
}

function cellout(id) {
    $('#' + id).css("font-size", "18px");
}

document.onclick = function (e) {
    $(".emoji-menu").hide();
};
$('.emoji-top').on("click", function (e) {
    if ($(".emoji-menu").css("display") == "none") {
        $(".emoji-menu").show();
    } else {
        $(".emoji-menu").hide();
    }
    e = e || event;
    stopFunc(e);
});

// $('.emoji-menu').on("click", function(e) {
//     e = e || event;
//     stopFunc(e);
// });
function stopFunc(e) {
    e.stopPropagation ? e.stopPropagation() : e.cancelBubble = true;
}

function insertAtCursor(dom, html) {
    if (dom != document.activeElement) { // 如果dom没有获取到焦点，追加
        dom.innerHTML = dom.innerHTML + html;
        return;
    }
    var sel, range;
    if (window.getSelection) {
        // IE9 或 非IE浏览器
        sel = window.getSelection();
        if (sel.getRangeAt && sel.rangeCount) {
            range = sel.getRangeAt(0);
            range.deleteContents();
            // Range.createContextualFragment() would be useful here but is
            // non-standard and not supported in all browsers (IE9, for one)
            var el = document.createElement("div");
            el.innerHTML = html;
            var frag = document.createDocumentFragment(),
                node, lastNode;
            while ((node = el.firstChild)) {
                lastNode = frag.appendChild(node);
            }
            range.insertNode(frag);
            // Preserve the selection
            if (lastNode) {
                range = range.cloneRange();
                range.setStartAfter(lastNode);
                range.collapse(true);
                sel.removeAllRanges();
                sel.addRange(range);
            }
        }
    } else if (document.selection && document.selection.type != "Control") {
        // IE < 9
        document.selection.createRange().pasteHTML(html);
    }
}

//时间计算
function check(val) {
    if (val < 10)
        val = '0' + val;
    return val;
}

var interval = setInterval(function () {
    var create_date = new Date(liveInfo.createdTime).getTime();
    var now_date = new Date().getTime();
    var played_time = now_date - create_date;
    var h = check(parseInt(played_time / 1000 / 60 / 60 % 24));
    if (parseInt(h) >= 3) {
        $('#played_time-title').remove();
        $('#played_time').html('已结束');
        clearInterval(interval)
    }
    var m = check(parseInt(played_time / 1000 / 60 % 60));
    var s = check(parseInt(played_time / 1000 % 60));
    $("#played_time").html(h + ':' + m + ':' + s);
}, 1000);


init();
