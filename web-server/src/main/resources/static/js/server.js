var cooldown = 60;//获取邮件验证码倒计时
var loader = document.getElementById("loader");


/* 发送用户识别的图片验证码*/
function Login(JSONdata, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/sys/user/login",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: JSONdata,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.error('获取图片验证码失败');
        }
    });
}

/*请求邮件验证码*/
function getemail(SussessFun) {

    var JSONdata = {address: $('.username').val()};
    $.ajax({
        type: "POST",
        url: "/sys/code/getMailCode",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: JSONdata,
        dataType: 'json',
        success: SussessFun,
        error: function (e) {
            toastr.error('发送失败');
        }
    });
}

/*60秒倒计时*/
function cooltime(o) {
    if (cooldown == 0) {
        o.removeAttribute("disabled");
        o.value = "获取验证码";
    } else {
        o.setAttribute("disabled", true);
        o.value = "重新发送(" + cooldown + ")";
        cooldown--;
        setTimeout(function () {
            cooltime(o);
        }, 1000);
    }
}

/*注册*/
function register(JSONdata, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/sys/user/register",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: JSONdata,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('请获取验证码');
        }
    });
}

/*找回密码*/
function findPwd(JSONdata, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/sys/user/resetPassword",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: JSONdata,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('请获取验证码');
        }
    });

}


//讨论区JS
/* 新建帖子 */
function newPost(JSONdata, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/post/newPost",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: JSONdata,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('新建帖子失败');
        }
    });
}

/* 删除帖子 */
function delPost(JSONdata, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/post/delPost",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: JSONdata,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('删除帖子失败');
        }
    });
}

/* 获取帖子 */
function showPost(JSONdata, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/post/showPost",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: JSONdata,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('获取帖子失败');
        }
    });
}

/* 获取帖子列表 */
function showPostList(JSONdata, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/post/showPostList",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: JSONdata,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('获取帖子失败');
        }
    });
}

/* 收藏 */
function postLike(JSONdata, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/post/postLike",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: JSONdata,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('收藏失败');
        }
    });
}

/* 取消收藏 */
function delLike(JSONdata, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/post/delLike",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: JSONdata,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('取消收藏失败');
        }
    });
}

/* 回复 */
function postReply(JSONdata, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/post/postReply",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: JSONdata,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('回复失败');
        }
    });
}

/* 删除回复 */
function delReply(JSONdata, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/post/delReply",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: JSONdata,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('删除回复失败');
        }
    });
}

/* 获取回复 */
function showReply(JSONdata, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/post/showReply",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: JSONdata,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('获取回复失败');
        }
    });
}

/* 回复点赞 */
function replyStar(JSONdata, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/post/replyStar",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: JSONdata,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('点赞失败');
        }
    });
}

/* 取消点赞 */
function delReplyStar(JSONdata, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/post/delReplyStar",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: JSONdata,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('取消点赞失败');
        }
    });
}
