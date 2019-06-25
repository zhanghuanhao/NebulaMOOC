var liveList = [];

function showLiveList() {
    var htmlstr = '';
    for (var i = 0; i < liveList.length; i++) {
        htmlstr += `<div class="one-live" onclick="window.open('video.html?id=${liveList[i].userInfo.id}')">
                        <img src="${resImgUrl + liveList[i].userInfo.headUrl}" onerror='this.src="res/default.jpg"'>
                        <div class="userName">${liveList[i].userInfo.nickName}</div>
                        <div class="live-body">
                            <p class="live-title">${liveList[i].title}</p>
                            <p class="live-introduction">${liveList[i].introduction}</p>
                        </div>
                    </div>`;
    }
    $('.live-list').append(htmlstr);
}


function init() {

    document.getElementsByTagName("body")[0].style.zoom = 1;
    $('.top-head').css("min-width", $('.top-head').width() + 'px');
    $('.top-head').css("width", $('.top-head').width() + 'px');

    var userName = getCookie("userName");
    var headUrl = getCookie("headUrl");
    if (userName == null || userName == "null" || headUrl == null || headUrl == "null") {
        $('#user-head').attr('src', 'res/default.jpg');
        $('#user-head').attr('onclick', "window.location.href='login.html'");
        $('#user-login').append(`<p class="user" id="unlogin" onclick="window.location.href='login.html'">点击登录</p>`);
    } else {
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


    getLiveList(function (data) {
        if (data.code == 100) {
            if (data.data != null && data.data.length > 0) {
                liveList = data.data;
                showLiveList();
                $('.status').css('display', 'none');
            }
        } else {
            toastr.warning('获取直播列表失败');
            $('.status').css('display', 'block');
        }
    });


}

init();