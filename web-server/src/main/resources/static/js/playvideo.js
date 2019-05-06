var player_width = 1440;
var play_height = 810;
var on_moveword = true;//是否开启弹幕
var showchat = $("#textArea");
var wordWeb;


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
    var wsUri = "ws://127.0.0.1:9080/websocket";
    //var wsUri = "ws://125.216.246.62:9080";
    wordWeb = new WebSocket(wsUri);
    wordWeb.binaryType = "arraybuffer";
    wordWeb.onopen = function (ev) {
        toastr.success('连接弹幕服务器成功！');
        var req = new proto.request();
        req.setCode(2);
        req.setColor(0);
        var token = getCookie('TOKEN');
        if (token != "") {
            req.setMsg(token);
            wordWeb.send(req.serializeBinary());
        }
    };
    wordWeb.onmessage = function (ev) {
        var response = proto.response.deserializeBinary(ev.data);
        if (response.getCode() == 100) {
            if (on_moveword) creatMoveword(response.getMsg(), response.getColor());
            showmsg(response.getNickname(), response.getMsg());
        } else if (response.getCode() == 301) {
            toastr.warning('请登录以发送弹幕！');
        } else if (response.getCode() == 302) {
            toastr.warning('登录已过期，请重新登录！');
        } else {
            toastr.error(response.getMsg());
        }
    }
}





//设置随机颜色值
var setColor = function () {
    var i = Math.floor(256 * (Math.random()));
    return i;
};


//让弹幕动起来
var moveObj = function (obj, Color) {
    var topMax = $("#showWords").height();
    var _top = Math.floor(topMax * (Math.random())); //设置top初始位置为面板高度内的随机数
    if (_top + obj.height() >= topMax) {
        _top -= obj.height();
    }

    var _left = $("#showWords").width() - obj.width(); //设置left初始位置位于显示面板最右侧

    //初始化消息位置
    obj.css({
        "top": _top,
        "left": _left,
        "color": "rgb(" + setColor() + "," + setColor() + "," + setColor() + ")" //调用 setColor 函数设置随机颜色
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
    var word = $("#word").val(); //获取输入框中的值

    //当输入的值不为空时，执行以下代码
    if (word != "") {
        $("#word").val(""); //清空输入框

        var mess = new proto.request();
        mess.setCode(1);
        mess.setMsg(word);
        mess.setColor(123);
        var b = mess.serializeBinary();
        wordWeb.send(b);
    }
    $("#word").focus(); //将焦点置于输入框
});

//生成弹幕
function creatMoveword(word, Color) {
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


function showmsg(name, content) {
    var msg = $("<span><font color='blue'>" + name + ":</font>" + content + "</span>");
    showchat.append(msg);
    showchat.animate({scrollTop: msg.offset().top + "px"}, 100);//始终在底部
}


