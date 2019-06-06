//鼠标移动选择效果
function changecolor(d) {
    var list = document.getElementsByName("li1");
    for (var i = 0; i < list.length; i++) {
        if (list[i].id != d) {
            list[i].style.backgroundColor = "transparent";
            list[i].className = "unselected";
        } else {
            list[i].style.backgroundColor = "#1E9FFF";
            list[i].className = "selected";
        }
    }
}

function changecolor2(d) {
    var list = document.getElementsByName("li2");
    for (var i = 0; i < list.length; i++) {
        if (list[i].id != d) {
            list[i].style.backgroundColor = "transparent";
            list[i].className = "unselected";
        } else {
            list[i].style.backgroundColor = "#1E9FFF";
            list[i].className = "selected";
        }
    }
}

function mouseover(d) {
    var se = document.getElementById(d);
    if (se.className != "selected") {
        se.style.backgroundColor = "rgba(0,0,0,0.2)";
    }
}

function mouseout(d) {
    var se = document.getElementById(d);
    if (se.className != "selected") se.style.backgroundColor = "transparent";
}



//轮播图
var picwidth = $('#sw').parent().width();
$('#sw').children('img').attr("width", picwidth);
$(function () {
    //每个固定的时间移动图片
    var timer = setInterval(picLoop, 4000);
    var index = 0;
    var num = document.getElementsByName("pic").length;

    function picLoop() {
        document.getElementsByName("pic")[index].style.backgroundColor = "rgba(100,100,100,0.3)";
        index++;
        if (index == num) {
            index = 0;
        }
        $(".content").animate({"left": -picwidth * index}, 300);
        document.getElementsByName("pic")[index].style.backgroundColor = "red";
    }

    //定时器的控制
    $(".pic").hover(function () {
        clearInterval(timer);
        $(".left").show();
        $(".right").show();

    }, function () {
        timer = setInterval(picLoop, 4000);
        $(".left").hide();
        $(".right").hide();
    });

    window.onload = function () {
        var lis = document.getElementsByName("pic");
        for (var i = 0; i < lis.length; i++) {
            lis[i].index = i;
            lis[i].onmouseover = function () {
                lis[index].style.backgroundColor = "rgba(100,100,100,0.3)";
                index = this.index;
                $(".content").animate({"left": -picwidth * index}, 300);
                lis[index].style.backgroundColor = "red";
            }
        }
    };

    $(".left").click(function () {
        document.getElementsByName("pic")[index].style.backgroundColor = "rgba(100,100,100,0.3)";
        index--;
        if (index == -1) {
            index = num - 1;
        }
        $(".content").animate({"left": -picwidth * index}, 300);
        document.getElementsByName("pic")[index].style.backgroundColor = "red";
    });
    $(".right").click(function () {
        document.getElementsByName("pic")[index].style.backgroundColor = "rgba(100,100,100,0.3)";
        index++;
        if (index == num) {
            index = 0
        }
        $(".content").animate({"left": -picwidth * index}, 300);
        document.getElementsByName("pic")[index].style.backgroundColor = "red";
    })


});

function init() {
    document.getElementsByTagName("body")[0].style.zoom = 1;
    $('.top-head').css("min-width", $('.top-head').width() + 'px');
    $('.top-head').css("width", $('.top-head').width() + 'px');

    var userName = localStorage["userName"];
    var headUrl = localStorage["headUrl"];
    if (userName == null || userName == "null" || headUrl == null || headUrl == "null") { //未登录
        $('#user-head').attr('src', 'res/default.jpg');
        $('#user-login').append(`<p class="user"id="unlogin" onclick="window.location.href='login.html'">点击登录</p>`);
    } else { //已登录
        $('#user-head').attr('src', `https://nebula-head.oss-cn-shenzhen.aliyuncs.com/${headUrl}/head100`);
        $('#user-login').append(`<p class="user">${userName}</p>`);
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
    }
    $(".cspic").height($(".cspic").width() * 0.57);
}
init();