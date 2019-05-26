var cooldown = 60;//获取邮件验证码倒计时
var loader = document.getElementById("loader");

$.ajaxSetup({
    complete: function (xhr, status) {
        //拦截器实现超时跳转到登录页面
        // 通过xhr取得响应头
        var REDIRECT = xhr.getResponseHeader("REDIRECT");
        //如果响应头中包含 REDIRECT 则说明是拦截器返回的
        if (REDIRECT == "REDIRECT") {
            var win = window;
            while (win != win.top) {
                win = win.top;
            }
            //重新跳转到 login.html
            win.location.href = xhr.getResponseHeader("CONTEXTPATH");
        }
    }
});

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
function getemail(SussessFun, o) {
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
            o.removeAttribute("disabled");
            o.value = '获取验证码';
        }
    });
}

/*检查账号是否存在*/
function checkUser(email, SuccessFun, o) {
    $.ajax({
        type: "POST",
        url: "/sys/user/checkUser",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: {email: email},
        dataType: 'json',
        success: SuccessFun,
        error: function (e) {
            o.setAttribute('src', 'res/wrong.png');
            toastr.error('服务错误');
            ifcheck = false;
        }
    });

}

/*60秒倒计时*/
function cooltime(o) {
    if (cooldown == 0) {
        o.removeAttribute("disabled");
        o.value = "获取验证码";
        cooldown = 60;
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

/* 获取帖子 */
function showPost(JSONdata, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/sys/post/showPost",
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
        url: "/sys/post/showPostList",
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

/* 评论 */
function comment(JSONdata, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/post/comment",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: JSONdata,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('评论失败');
        }
    });
}


/* 删除评论 */
function delComment(JSONdata, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/post/delComment",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: JSONdata,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('删除评论失败');
        }
    });
}

/* 回复 */
function replyComment(JSONdata, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/post/replyComment",
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
function delReplyComment(JSONdata, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/post/delReplyComment",
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
        url: "/sys/post/showReply",
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

/* 取消回复点赞 */
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

/* 帖子点赞 */
function postStar(JSONdata, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/post/postStar",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: JSONdata,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('点赞失败');
        }
    });
}

/* 取消帖子点赞 */
function delPostStar(JSONdata, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/post/delPostStar",
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
        update: function (obj, pageinit) {
            return (function () {
                zp.addhtml(obj, pageinit);
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
                    var cur = parseInt($("input.input").val());
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
            pageNum: 10,
            current: 1,
            backfun: function () {
            }
        }, options);
        zp.init(this, pageinit);
    };
    $.fn.updatePage = function (options) {
        var pageinit = $.extend({
            pageNum: 10,
            current: 1,
            backfun: function () {
            }
        }, options);
        zp.update(this, pageinit);
    }
}(jQuery));

function filterNum(num) {
    if (num < 10) {
        return "0" + num;
    } else {
        return num;
    }
}

//获取个人信息
function getInfo(json, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/user/getUserInfo",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: json,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('获取个人信息失败');
        }
    });
}

//保存个人信息
function saveInfo(json, file, ReturnFun, obj) {

    var formData = new FormData();
    formData.append('file', file);  //添加图片文件
    formData.append('nickName', json.nickName);
    formData.append('age', json.age);
    formData.append('major', json.major);
    formData.append('sex', json.sex);
    $.ajax({
        type: "POST",
        url: "/api/user/updateUser",
        data: formData,
        processData: false, // 告诉jQuery不要去处理发送的数据
        contentType: false, // 告诉jQuery不要去设置Content-Type请求头
        success: ReturnFun,
        error: function () {
            toastr.error('保存失败');
            obj.removeAttr('disabled');
            obj.val('保存');
        }
    });
}

//获取课程列表信息
function showCourseList(json, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/sys/course/getCourseList",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: json,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('获取课程失败');
        }
    });
}

//获取章节信息
function showChapterInfo(json, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/sys/course/getCourse",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: json,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('获取章节失败');
        }
    });
}

//点赞课程
function courseStar(json, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/course/courseStar",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: json,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('点赞失败');
        }
    });
}

//取消点赞课程
function delCourseStar(json, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/course/delCourseStar",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: json,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('取消点赞失败');
        }
    });
}

//收藏课程
function courseLike(json, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/course/courseLike",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: json,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('收藏失败');
        }
    });
}

//取消收藏课程
function delCourseLike(json, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/course/delCourseLike",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: json,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('取消收藏失败');
        }
    });
}


//获取章节评论
function showChapterComment(json, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/sys/course/getCourseCommentList",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: json,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('获取章节评论失败');
        }
    });
}

//评论章节
function commentOnChapter(json, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/course/courseComment",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: json,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('获取章节评论失败');
        }
    });
}

//删除章节评论
function delCourseComment(json, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/course/delCourseComment",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: json,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('删除失败');
        }
    });
}

//点赞章节评论
function courseCommentStar(json, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/course/courseCommentStar",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: json,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('点赞失败');
        }
    });
}

//取消点赞章节评论
function delCourseCommentStar(json, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/course/delCourseCommentStar",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: json,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('取消点赞失败');
        }
    });
}


//获取课程内容
function getCourseSection(json, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/sys/course/getCourseSection",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: json,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('获取课程内容失败');
        }
    });
}

//获取课程内容评论
function getCourseSectionCommentList(json, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/sys/course/getCourseSectionCommentList",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: json,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('获取课程内容评论失败');
        }
    });
}

//课程内容评论点赞
function sectionCommentStar(json, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/course/sectionCommentStar",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: json,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('点赞失败');
        }
    });
}

//取消课程内容评论点赞
function delSectionCommentStar(json, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/course/delSectionCommentStar",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: json,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('取消点赞失败');
        }
    });
}


//评论课程内容
function sectionComment(json, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/course/sectionComment",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: json,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('评论失败');
        }
    });
}


//删除课程内容评论
function delSectionComment(json, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/course/delSectionComment",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: json,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('删除评论失败');
        }
    });
}


//回复课程内容评论
function sectionCommentReply(json, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/course/sectionCommentReply",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: json,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('回复失败');
        }
    });
}


//删除课程内容回复
function delSectionCommentReply(json, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/api/course/delSectionCommentReply",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: json,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('删除回复失败');
        }
    });
}


//上传课程视频
function upLoadVideo(file, ReturnFun) {
    var formData = new FormData();
    formData.append('file', file);  //添加视频文件
    $.ajax({
        type: "POST",
        url: "/api/video/uploadVideo",
        data: formData,
        processData: false, // 告诉jQuery不要去处理发送的数据
        contentType: false, // 告诉jQuery不要去设置Content-Type请求头
        success: ReturnFun,
        error: function () {
            toastr.warning('上传视频失败');
        }
    });
}

//获取已上传视频列表
function getVideoList(ReturnFun) {
    $.ajax({
        type: "GET",
        url: "/api/video/getVideoList",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: {},
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('获取视频列表失败');
        }
    });
}





