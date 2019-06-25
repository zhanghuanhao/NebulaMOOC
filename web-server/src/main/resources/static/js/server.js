var cooldown = 60;//获取邮件验证码倒计时

var resUrl = 'https://' + window.location.host + ':10443/'; //  pro环境
// var resUrl = 'http://119.23.63.134:10080/'; // dev
var resImgUrl = resUrl + 'image/';
var resVideoUrl = resUrl + 'video/';

function getCookie(name) {
    var cookies = document.cookie;
    var cookie_array = cookies.split(';');
    for (var i in cookie_array) {
        var key_value = cookie_array[i].trim().split('=');
        if (key_value[0] === name)
            return key_value[1];
    }
    return null;
}

$.ajaxSetup({
    complete: function (xhr, status) {
        // 如果响应码是401，则需登陆
        if (xhr.status == 401) {
            var win = window;
            while (win != win.top) {
                win = win.top;
            }
            toastr.warning("用户未登录，正在跳转...");
            setTimeout(function () {
                //重新跳转到登陆界面
                win.location.href = "/login.html";
            }, 2000);
        }
        // 如果响应码是403，则用户可能在进行xss
        else if (xhr.status == 403) {
            toastr.error("警告：存在非法操作！");
        }
    },

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


function logout() {

    $.ajax({
        type: "GET",
        url: "/sys/user/logout",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: {},
        dataType: 'json',
        success: function (data) {
            if (data.code == 100) {
                toastr.success('服务器注销成功');
            } else {
                toastr.warning('服务器注销失败');
            }
        },
        error: function () {
            toastr.warning('服务器注销失败');
        }
    });


    document.cookie = "userId=";
    document.cookie = "userName=";
    document.cookie = "headUrl=";
}


function filterNum(num) {
    if (num < 10) {
        return "0" + num;
    } else {
        return num;
    }
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

/* 获取最热10条帖子列表 */
function showHotPostList(ReturnFun) {
    $.ajax({
        type: "GET",
        url: "/sys/post/showHotPostList",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: {},
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

    formData.append('file', file, json.filename);  //添加图片文件
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

//获取课程列表信息
function showHotCourseList(ReturnFun) {
    $.ajax({
        type: "GET",
        url: "/sys/course/getHotCourseList",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: {},
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
    formData.append('file', file, file.name);  //添加视频文件
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


//新建课程
function newCourse(course, kind, file, ReturnFun, obj) {
    var formData = new FormData();
    //formData.append('course',course);//添加课程信息
    formData.append('title', course.title);
    formData.append('introduction', course.introduction);
    formData.append('kind', kind);   //添加分类
    formData.append('file', file, file.name);  //添加封面图片
    for (var i = 0; i < course.chapterList.length; i++) {
        formData.append('chapterList[' + i + '].title', course.chapterList[i].title);
        for (var j = 0; j < course.chapterList[i].sectionList.length; j++) {
            formData.append('chapterList[' + i + '].sectionList[' + j + '].title', course.chapterList[i].sectionList[j].title);
            formData.append('chapterList[' + i + '].sectionList[' + j + '].introduction', course.chapterList[i].sectionList[j].introduction);
            formData.append('chapterList[' + i + '].sectionList[' + j + '].videoUrl', course.chapterList[i].sectionList[j].videoUrl);
        }
    }

    $.ajax({
        type: "POST",
        url: "/api/course/newCourse",
        data: formData,
        processData: false, // 告诉jQuery不要去处理发送的数据
        contentType: false, // 告诉jQuery不要去设置Content-Type请求头
        success: ReturnFun,
        error: function () {
            toastr.error('创建课程失败！');
            obj.removeAttr('disabled');
            obj.val('创建课程');
        }
    });
}


//获取收藏的课程
function getLikeCourse(json, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/sys/course/getLikeCourse",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: json,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('获取课程失败');
        }
    });
}


//获取推荐的课程
function showRecommendCourseList(ReturnFun) {
    $.ajax({
        type: "GET",
        url: "/sys/course/getRecommendCourseList",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: {},
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('获取课程失败');
        }
    });
}


//获取推荐的帖子
function showRecommendPostList(ReturnFun) {
    $.ajax({
        type: "GET",
        url: "/sys/post/showRecommendPostList",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: {},
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('获取帖子失败');
        }
    });
}



//获取收藏的帖子
function getLikePost(json, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/sys/post/getLikePost",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: json,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('获取帖子失败');
        }
    });
}


//新建直播
function newLive(json, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "https://" + window.location.host + ":8443/live/newLive",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: json,
        dataType: 'json',
        xhrFields: {
            withCredentials: true
        },
        success: ReturnFun,
        error: function () {
            toastr.warning('新建直播失败');
        }
    });
}


//获取自己的直播地址
function getMyLive(ReturnFun) {
    $.ajax({
        type: "GET",
        url: "https://" + window.location.host + ":8443/live/getMyLive",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: {},
        dataType: 'json',
        xhrFields: {
            withCredentials: true
        },
        success: ReturnFun,
        error: function () {
            toastr.warning('获取失败');
        }
    });
}


//获取直播列表
function getLiveList(ReturnFun) {
    $.ajax({
        type: "GET",
        url: "https://" + window.location.host + ":8443/live/getLiveList",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: {},
        dataType: 'json',
        xhrFields: {
            withCredentials: true
        },
        success: ReturnFun,
        error: function () {
            toastr.warning('获取失败');
        }
    });
}


//进入直播间
function getLive(json, ReturnFun) {
    $.ajax({
        type: "GET",
        url: "https://" + window.location.host + ":8443/live/getLive",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: json,
        dataType: 'json',
        xhrFields: {
            withCredentials: true
        },
        success: ReturnFun,
        error: function () {
            toastr.warning('获取失败');
        }
    });
}


//获取主页最新最热课程
function getIndexCourse(ReturnFun) {
    $.ajax({
        type: "GET",
        url: "/sys/course/getHomeCourseList",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: {},
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('获取失败');
        }
    });
}


//获取主页最新最热帖子
function getIndexPost(ReturnFun) {
    $.ajax({
        type: "GET",
        url: "/sys/post/showHomePostList",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: {},
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            toastr.warning('获取失败');
        }
    });
}



