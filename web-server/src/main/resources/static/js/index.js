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
    //document.getElementsByTagName("body")[0].style.zoom = 1;
    $('.top-head').css("min-width", $('.top-head').width() + 'px');
    $('.top-head').css("width", $('.top-head').width() + 'px');

    var userName = localStorage["userName"];
    var headUrl = localStorage["headUrl"];
    if (userName == null || userName == "null" || headUrl == null || headUrl == "null") { //未登录
        $('#user-head').attr('src', 'res/default.jpg');
        $('#user-login').append(`<p class="user"id="unlogin" onclick="window.location.href='login.html'">点击登录</p>`);
    } else { //已登录
        $('#user-head').attr('src', `${resImgUrl + headUrl}`);
        $('#user-head').attr('onerror', 'this.src="res/default.jpg"');
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


    getIndexCourse(function (data) {
        if (data.code == 100) {
            var newCourse = data.data[0];
            var hotCourse = data.data[1];
            var htmlstr = '';
            if (newCourse != null && newCourse.length > 0) {
                for (var i = 0; i < newCourse.length; i++) {
                    htmlstr += `<div class="csli border_shadow" onclick="window.open('chapter.html?id=${newCourse[i].id}')">
                                    <div class="cspic">
                                        <img src="${resImgUrl + newCourse[i].courseHeadUrl}" onerror='this.src="res/defaultCourse.jpg"'>
                                    </div>
                                    <div class="donghua backpic">
                                        <span class="float_l cstitle">${newCourse[i].title}</span>
                                        <span class="float_l hidefont">${newCourse[i].introduction}</span>
                                    </div>
                                    <div class="csfoot">
                                        <img alt="头像" class="headpic"src="${resImgUrl + newCourse[i].userHeadUrl}" onerror='this.src="res/default.jpg"'>
                                        <span class="tcname">${newCourse[i].userNickName}</span>
                                        <div class="float_r csfoot_r">
                                            <img src="res/star.png">
                                            <span>${newCourse[i].star}</span>
                                            <img src="res/like.png">
                                            <span>${newCourse[i].like}</span>
                                        </div>
                                      </div>
                                    </div>`;
                }
                $('#show-list-1').append(htmlstr);
            }
            htmlstr = '';
            if (hotCourse != null && hotCourse.length > 0) {
                for (var i = 0; i < hotCourse.length; i++) {
                    htmlstr += `<div class="csli border_shadow" onclick="window.open('chapter.html?id=${hotCourse[i].id}')">
                                    <div class="cspic">
                                        <img src="${resImgUrl + hotCourse[i].courseHeadUrl}" onerror='this.src="res/defaultCourse.jpg"'>
                                    </div>
                                    <div class="donghua backpic">
                                        <span class="float_l cstitle">${hotCourse[i].title}</span>
                                        <span class="float_l hidefont">${hotCourse[i].introduction}</span>
                                    </div>
                                    <div class="csfoot">
                                        <img alt="头像" class="headpic" src="${resImgUrl + hotCourse[i].userHeadUrl}" onerror='this.src="res/default.jpg"'>
                                        <span class="tcname">${hotCourse[i].userNickName}</span>
                                        <div class="float_r csfoot_r">
                                            <img src="res/star.png">
                                            <span>${hotCourse[i].star}</span>
                                            <img src="res/like.png">
                                            <span>${hotCourse[i].like}</span>
                                        </div>
                                    </div>
                                </div>`;
                }
                $('#show-list-2').append(htmlstr);
            }

        } else {
            toastr.warning('获取失败');
        }
    });


    getIndexPost(function (data) {
        if (data.code == 100) {
            var newPost = data.data[0];
            var hotPost = data.data[1];
            var htmlstr = '';
            if (newPost != null && newPost.length > 0) {
                for (var i = 0; i < newPost.length; i++) {
                    var time = new Date(newPost[i].createdTime);
                    var createdtime = time.getFullYear() + "-" + filterNum(time.getMonth() + 1) + "-" + filterNum(time.getDate()) + " "
                        + filterNum(time.getHours()) + ":" + filterNum(time.getMinutes());

                    htmlstr += ` <div class="fli border_shadow ftop" onclick="window.open('post.html?id=${newPost[i].id}')">
                                     <div class="backpic dh">
                                         <span class="float_l cstitle">${newPost[i].title}</span>
                                         <span class="float_l hidefont">${newPost[i].content}</span>
                                     </div>
                                     <div class="ffoot">
                                         <div class="ffoot_l">
                                             <img alt="头像" class="hpic" src="${resImgUrl + newPost[i].headimg}" onerror='this.src="res/default.jpg"'>
                                         <span class="tcname">${newPost[i].nickName}</span>
                                         </div>
                                         <div class="ffoot_m">
                                             <span>发布时间：</span>
                                             <span id="release_time1">${createdtime}</span>
                                         </div>
                                         <div class="float_r ffoot_r">
                                             <img src="res/star.png">
                                             <span>${newPost[i].star}</span>
                                             <img src="res/like.png">
                                             <span>${newPost[i].like}</span>
                                         </div>
                                     </div>
                                  </div>`;
                }
                $('#show-list-3').append(htmlstr);
            }

            htmlstr = '';
            if (hotPost != null && hotPost.length > 0) {
                for (var i = 0; i < hotPost.length; i++) {
                    var time = new Date(hotPost[i].createdTime);
                    var createdtime = time.getFullYear() + "-" + filterNum(time.getMonth() + 1) + "-" + filterNum(time.getDate()) + " "
                        + filterNum(time.getHours()) + ":" + filterNum(time.getMinutes());

                    htmlstr += ` <div class="fli border_shadow ftop" onclick="window.open('post.html?id=${hotPost[i].id}')">
                                     <div class="backpic dh">
                                         <span class="float_l cstitle">${hotPost[i].title}</span>
                                         <span class="float_l hidefont">${hotPost[i].content}</span>
                                     </div>
                                     <div class="ffoot">
                                         <div class="ffoot_l">
                                             <img alt="头像" class="hpic" src="${resImgUrl + hotPost[i].headimg}" onerror='this.src="res/default.jpg"'>
                                         <span class="tcname">${hotPost[i].nickName}</span>
                                         </div>
                                         <div class="ffoot_m">
                                             <span>发布时间：</span>
                                             <span id="release_time1">${createdtime}</span>
                                         </div>
                                         <div class="float_r ffoot_r">
                                             <img src="res/star.png">
                                             <span>${hotPost[i].star}</span>
                                             <img src="res/like.png">
                                             <span>${hotPost[i].like}</span>
                                         </div>
                                     </div>
                                  </div>`;
                }
                $('#show-list-4').append(htmlstr);
            }

        } else {
            toastr.warning('获取失败');
        }

    });


}

init();