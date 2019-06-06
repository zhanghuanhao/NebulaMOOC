var player_width;
var play_height;
var on_moveword = true;//是否开启弹幕
var showchat = $("#textArea");
var wordWeb;
var minwidth;

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
        //file: 'rtmp://127.0.0.1/live/hello',
        file: 'res/1.flv',
        autostart: true,
        bufferlength: 1,
        width: player_width,
        height: play_height
    });
}

//获取token
function getCookie(c_name) {
    if (document.cookie.length > 0) {
        console.log('cookie:' + document.cookie);
        c_start = document.cookie.indexOf(c_name + "=");
        console.log('c_start:' + c_start);
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1;
            c_end = document.cookie.indexOf(";", c_start);
            if (c_end == -1) c_end = document.cookie.length;
            return unescape(document.cookie.substring(c_start, c_end));
        }
    }
    return "";
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
            console.log(response.getMsg());
            if (on_moveword) creatMoveword(response.getMsg(), response.getColor(), response.getSize());
            showmsg(response.getNickname(), response.getMsg(), response.getSize());
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
    var _top = Math.floor(topMax * (Math.random())); //设置top初始位置为面板高度内的随机数
    if (_top + obj.height() >= topMax) {
        _top -= obj.height();
    }

    var _left = $("#showWords").width() - obj.width(); //设置left初始位置位于显示面板最右侧

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
        "color": "rgb(" + r + "," + g + "," + b + ")"
    });

    var time = 10000;
    //进行动画，动画结束后通过回调函数把消息从面板中删除
    obj.animate({
        left: '0px'
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
        mess.setSize(1);
        mess.setMsg(word);
        mess.setColor(parseInt(s[0]) * 1000000 + parseInt(s[1]) * 1000 + parseInt(s[2]));
        var b = mess.serializeBinary();
        wordWeb.send(b);
    } else if (word != "" && word.length > 30) {
        toastr.warning("最大字符长度为30，请重新输入");
    }
    $("#word").focus(); //将焦点置于输入框
});

//生成弹幕
function creatMoveword(word, Color, Size) {
    console.log(Size);
    var obj = $("<span class='moveWord'>" + word + "</span>"); //为word值生成对象
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


function showmsg(name, content, Size) {
    console.log(Size);
    var msg = $("<span><font color='blue'>" + name + ":</font>" + content + "</span>");
    showchat.append(msg);
    showchat.animate({scrollTop: msg.offset().top + "px"}, 100);//始终在底部
}

//emoji-menu
for (var i = 0; i < 10; i++)
    for (var j = 0; j < 10; j++) {
        $('#e' + i.toString() + j.toString()).html(String.fromCodePoint(parseInt("1f601", 16) + i * 10 + j));
        $('#e' + i.toString() + j.toString()).on('click', function () {
            $("#word").html($("#word").html() + $('#' + this.id).html());
        });
    }
$(".emoji-top").click(function () {
    if ($(".emoji-menu")[0].style.display == "none")
        $(".emoji-menu")[0].style.display = "block";
    else $(".emoji-menu")[0].style.display = "none";
});

init();
