var player_width = 640;
var play_height = 480;

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
        document.write("您没有安装flash");
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

/* 弹幕 */


//设置随机颜色值
var setColor = function () {
    var i = Math.floor(256 * (Math.random()));
    return i;
};


//让发射的消息动起来
var moveObj = function (obj) {
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
        var obj = $("<span class='moveWord'>" + word + "</span>"); //为word值生成对象
        $("#showWords").append(obj); //将生成的对象附加到面板上
        moveObj(obj); //调用 moveObj 函数使生成的对象动起来
    }
    $("#word").focus(); //将焦点置于输入框
});

//清屏
$("#removeAll").click(function () {
    $("#showWords").empty();
});


