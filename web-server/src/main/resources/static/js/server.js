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


//分页
(function ($) {
    var zp = {
        init: function (obj, pageinit) {
            return (function () {
                zp.addhtml(obj, pageinit);
                zp.bindEvent(obj, pageinit);
            }());
        },
        addhtml: function (obj, pageinit) {
            return (function () {
                obj.empty();
                /*上一页*/
                if (pageinit.current > 1) {
                    obj.append('<a href="javascript:;" class="prebtn">上一页</a>');
                } else {
                    obj.remove('.prevPage');
                    obj.append('<span class="disabled">上一页</span>');
                }
                /*中间页*/
                if (pageinit.current > 4 && pageinit.pageNum > 4) {
                    obj.append('<a href="javascript:;" class="Pagenum">' + 1 + '</a>');
                    obj.append('<a href="javascript:;" class="Pagenum">' + 2 + '</a>');
                    obj.append('<span>...</span>');
                }
                if (pageinit.current > 4 && pageinit.current <= pageinit.pageNum - 5) {
                    var start = pageinit.current - 2, end = pageinit.current + 2;
                } else if (pageinit.current > 4 && pageinit.current > pageinit.pageNum - 5) {
                    var start = pageinit.pageNum - 4, end = pageinit.pageNum;
                } else {
                    var start = 1, end = 9;
                }
                for (; start <= end; start++) {
                    if (start <= pageinit.pageNum && start >= 1) {
                        if (start == pageinit.current) {
                            obj.append('<span class="current">' + start + '</span>');
                        } else if (start == pageinit.current + 1) {
                            obj.append('<a href="javascript:;" class="Pagenum nextpage">' + start + '</a>');
                        } else {
                            obj.append('<a href="javascript:;" class="Pagenum">' + start + '</a>');
                        }
                    }
                }
                if (end < pageinit.pageNum) {
                    obj.append('<span>...</span>');
                }
                /*下一页*/
                if (pageinit.current >= pageinit.pageNum) {
                    obj.remove('.nextbtn');
                    obj.append('<span class="disabled">下一页</span>');
                } else {
                    obj.append('<a href="javascript:;" class="nextbtn">下一页</a>');
                }
                /*尾部*/
                obj.append('<span>' + '共' + '<b>' + pageinit.pageNum + '</b>' + '页，' + '</span>');
                obj.append('<span>' + '到第' + '<input type="number" class="input" value="1"/>' + '页' + '</span>');
                obj.append('<span class="okbtn">' + '确定' + '</span>');
            }());
        },
        bindEvent: function (obj, pageinit) {
            return (function () {
                obj.on("click", "a.prebtn", function () {
                    var cur = parseInt(obj.children("span.current").text());
                    var current = $.extend(pageinit, {"current": cur - 1});
                    zp.addhtml(obj, current);
                    if (typeof (pageinit.backfun) == "function") {
                        pageinit.backfun(current);
                    }
                });
                obj.on("click", "a.Pagenum", function () {
                    var cur = parseInt($(this).text());
                    var current = $.extend(pageinit, {"current": cur});
                    zp.addhtml(obj, current);
                    if (typeof (pageinit.backfun) == "function") {
                        pageinit.backfun(current);
                    }
                });
                obj.on("click", "a.nextbtn", function () {
                    var cur = parseInt(obj.children("span.current").text());
                    var current = $.extend(pageinit, {"current": cur + 1});
                    zp.addhtml(obj, current);
                    if (typeof (pageinit.backfun) == "function") {
                        pageinit.backfun(current);
                    }
                });
                obj.on("click", "span.okbtn", function () {
                    var cur = parseInt($("input.zxfinput").val());
                    var current = $.extend(pageinit, {"current": cur});
                    zp.addhtml(obj, {"current": cur, "pageNum": pageinit.pageNum});
                    if (typeof (pageinit.backfun) == "function") {
                        pageinit.backfun(current);
                    }
                });
            }());
        }
    };
    $.fn.createPage = function (options) {
        var pageinit = $.extend({
            pageNum: 15,
            current: 1,
            backfun: function () {
            }
        }, options);
        zp.init(this, pageinit);
    }
}(jQuery));

function filterNum(num) {
    if (num < 10) {
        return "0" + num;
    } else {
        return num;
    }
}

function doReply(replyList) {
    var arr = [];
    for (var i in replyList) {
        var obj = {};
        obj.id = i;
        obj.reply_id = replyList[i].id;
        obj.fatherReplyId = replyList[i].fatherReplyId;
        obj.replyName = replyList[i].nickname;
        obj.beReplyName = "";
        obj.content = replyList[i].content;
        obj.replyBody = [];
        var time = new Date(replyList[i].createdTime);
        obj.time = time.getFullYear() + "-" + filterNum(time.getMonth() + 1) + "-" + filterNum(time.getDate()) + " "
            + filterNum(time.getHours()) + ":" + filterNum(time.getMinutes()) + ":" + filterNum(time.getSeconds());
        if (replyList[i].fatherReplyId != -1) {
            for (var j = 0; j < arr.length; j++) {
                if (arr[j].reply_id == replyList[i].fatherReplyId) {
                    if (replyList[i].fatherId == replyList[i].fatherReplyId) {
                        obj.beReplyName = arr[j].replyName;
                    } else {
                        var rl = arr[j].replyBody.length;
                        for (var t = 0; t < rl; t++) {
                            if (arr[j].replyBody[t].reply_id == replyList[i].fatherId) {
                                obj.beReplyName = arr[j].replyBody[t].replyName;
                                break;
                            }
                        }
                    }
                    arr[j].replyBody.push(obj);
                    break;
                }
            }
        } else {
            obj.img = "res/img.jpg";
            obj.star = replyList[i].star;
            arr.push(obj);
        }
    }
    return arr;
}