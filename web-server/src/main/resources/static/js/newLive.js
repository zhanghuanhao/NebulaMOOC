var myLiveAddress = null;

function toMyLive() {
    if (myLiveAddress != null) {
        window.location.href = 'video.html?token=' + myLiveAddress;
    }
}


function showMyLive() {
    getMyLive(function (data) {
        if (data.code == 100) {
            if (data.data == null) {
                $('.live-status').html('未申请直播');
                $('.showLive').css('display', 'none');
                $('#newLive').removeAttr('disabled');
            } else {
                $('.live-status').html('已申请直播');
                $('.showLive').css('display', 'block');
                $('#newLive').attr('disabled', true);
                var time = new Date(data.data.createdTime);
                var createdtime = time.getFullYear() + "-" + filterNum(time.getMonth() + 1) + "-" + filterNum(time.getDate()) + " "
                    + filterNum(time.getHours()) + ":" + filterNum(time.getMinutes());
                var timestamp = Date.parse(time);
                timestamp += 3 * 3600 * 1000;
                time = new Date(timestamp);
                var endtime = time.getFullYear() + "-" + filterNum(time.getMonth() + 1) + "-" + filterNum(time.getDate()) + " "
                    + filterNum(time.getHours()) + ":" + filterNum(time.getMinutes());
                $('.live-time').html('创建时间：' + createdtime + '</br>结束时间：' + endtime);
                $('.live-address').html('rtmp://' + window.location.host + '/live');
                $('.live-pass').html(data.msg);

                $('.head').attr('src', resImgUrl + data.data.userInfo.headUrl);
                $('.head').attr('onerror', 'this.src="res/default.jpg"');
                $('.userName').html(data.data.userInfo.nickName);
                $('.live-title').html(data.data.title);
                $('.live-introduction').html(data.data.introduction);

                myLiveAddress = data.msg.split("?")[0];

            }
        } else {
            toastr.warning('获取直播状态失败');
        }
    });
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


    $('#newLive').on('click', function () {
        var title = $('.title-input').val();
        if (title == null || title == '') {
            toastr.warning('请输入标题');
            return;
        }
        var introduction = $('.introduction-input').val();
        if (introduction == null || introduction == '') {
            toastr.warning('请输入简介');
            return;
        }

        var json = {title: title, introduction: introduction};
        newLive(json, function (data) {
            if (data.code == 100) {
                toastr.success('新建直播成功!');
                showMyLive();
            } else if (data.code == 304) {
                toastr.warning('直播已创建!');
            } else {
                toastr.error('新建直播失败!');
            }
        });
    });


    $('#getMyLive').on('click', function () {
        showMyLive();
    });

    showMyLive();

}


init();