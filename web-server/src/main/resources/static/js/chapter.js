var courseId = window.location.href.split("=")[1];
var userId = localStorage["userId"];
var commentList;
var doCommentList;

function doComment() {
    var arr = [];
    for (var i in commentList) {
        var obj = {};
        obj.id = commentList[i].id;
        obj.commentId = commentList[i].commentId;
        obj.fromId = commentList[i].userId;
        obj.replyName = commentList[i].userNickName;
        obj.content = commentList[i].content;
        obj.ifStar = commentList[i].ifStar;
        obj.img = commentList[i].userHeadUrl;
        obj.replyBody = [];
        obj.Index = {x: 0};
        var time = new Date(commentList[i].createdTime);
        obj.time = time.getFullYear() + "-" + filterNum(time.getMonth() + 1) + "-" + filterNum(time.getDate()) + " "
            + filterNum(time.getHours()) + ":" + filterNum(time.getMinutes());

        obj.star = commentList[i].star;
        obj.Index.x = arr.length;
        arr.push(obj);

    }
    return arr;
}

(function ($) {
    function crateCommentInfo(obj) {
        var del = "";
        if (obj.fromId == userId) {
            del = "<img src='res/del.png' class='del-btn'>";
        }

        var starimg = "";
        if (obj.ifStar == false) {
            starimg = "<img id='T'src='res/star.png'class='star-btn'>";
        } else {
            starimg = "<img id='F'src='res/unstar.png'class='star-btn'>";
        }

        var el = `<div class='comment-info'>
                      <header>
                          <img src='${resImgUrl + obj.img}' onerror='this.src="res/default.jpg"'>
                      </header>
                      <div class='comment-right'>
                          <h3>${obj.replyName}</h3>
                          <div class='comment-content-header'>
                              <span>
                                   <i class='glyphicon glyphicon-time'></i>${obj.time}
                              </span>
                          </div>
                          <p  class='content'>${obj.content}</p>
                          <div class='comment-content-footer'>
                              <div class='row'>
                                  <div class='col-md-10'></div>
                                  <div id='${obj.Index.x}' class='col-md-2'>${starimg}
                                      <span id='${obj.star}' >${obj.star}</span>${del}
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>`;
        return el;
    }


    $.fn.addCommentList = function (options) {
        var defaults = {
            data: [],
            add: ""
        };
        var option = $.extend(defaults, options);
        //加载数据
        if (option.data.length > 0) {
            var dataList = option.data;
            var totalString = "";
            for (var i = 0; i < dataList.length; i++) {
                var obj = dataList[i];
                var objString = crateCommentInfo(obj);
                totalString = totalString + objString;
            }

            $('.comment-list').append(totalString);

            //点赞
            $(".star-btn").click(function () {
                var idx = $(this).parent().attr('id');
                var json = {id: doCommentList[idx].id};
                var img = $(this);
                var num = $(this).next();
                if (img.attr('id') == 'T') {
                    courseCommentStar(json, function (data) {
                        if (data.code == 100) {
                            toastr.success('点赞成功');
                            img.attr('src', 'res/unstar.png');
                            img.attr('id', 'F');
                            var s = parseInt(num.attr('id')) + 1;
                            num.html(s);
                            num.attr('id', s);
                        } else if (data.code == 105) {
                            toastr.warning('您已点赞');
                            img.attr('src', 'res/unstar.png');
                            img.attr('id', 'F');
                        } else {
                            toastr.warning(data.msg);
                        }
                    });
                } else {
                    delCourseCommentStar(json, function (data) {
                        if (data.code == 100) {
                            toastr.success('取消点赞成功');
                            img.attr('src', 'res/star.png');
                            img.attr('id', 'T');
                            var s = parseInt(num.attr('id')) - 1;
                            num.html(s);
                            num.attr('id', s);
                        } else if (data.code == 106) {
                            toastr.warning('您已取消点赞');
                            img.attr('src', 'res/star.png');
                            img.attr('id', 'T');
                        } else {
                            toastr.warning(data.msg);
                        }
                    });
                }

            });


            //删除评论或回复
            $(".del-btn").click(function () {
                if (confirm("删除该回复？")) {
                    var index = $(this).parent();
                    var idx = index.attr('id');
                    var json = {courseId: doCommentList[idx].id};
                    delCourseComment(json, function (data) {
                        if (data.code == 100) {
                            toastr.success('删除评论成功');
                            index.parent().parent().parent().parent().remove();
                        } else {
                            toastr.warning('删除评论失败');
                        }
                    });
                }
            });

        }

    }

})(jQuery);


function createCommentList() {
    doCommentList = doComment();
    $('.comment-list').empty();
    $('.comment-list').addCommentList({data: doCommentList, add: ""});
}

function init() {
    showChapterInfo({courseId: courseId}, function (data) {
        console.log(data);
        if (data.code == 100) {
            courseInfo = data.data;
            $('.chapter-img').attr('src', resImgUrl + courseInfo.courseHeadUrl);
            $('.chapter-img').attr('onerror', 'this.src="res/default.jpg"');
            $('.course-title').html(courseInfo.title);
            $('.course-content').html(courseInfo.introduction);
            $('.course-head').attr('src', resImgUrl + courseInfo.userHeadUrl);
            $('.course-head').attr('onerror', 'this.src="res/default.jpg"');
            $('.course-username').html(courseInfo.userNickName);
            var time = new Date(courseInfo.createdTime);
            var coursetime = time.getFullYear() + "-" + filterNum(time.getMonth() + 1) + "-" + filterNum(time.getDate()) + " "
                + filterNum(time.getHours()) + ":" + filterNum(time.getMinutes());

            $('.course-time').html(coursetime);
            $('.course-starnum').html(courseInfo.star);
            $('.course-likenum').html(courseInfo.like);
            var star = $('.course-star');
            var like = $('.course-like');

            if (courseInfo.ifStar == true) {
                star.attr('src', 'res/unstar.png');
                star.attr('id', 'F');
            } else {
                star.attr('src', 'res/star.png');
                star.attr('id', 'T');
            }

            if (courseInfo.ifLike == true) {
                like.attr('src', 'res/unlike.png');
                like.attr('id', 'F');
            } else {
                like.attr('src', 'res/like.png');
                like.attr('id', 'T');
            }

            star.on('click', function () {
                var starnum = $('.course-starnum');
                if (star.attr('id') == 'T') {
                    courseStar({id: courseId, kindName: courseInfo.kindName}, function (data) {
                        if (data.code == 100) {
                            toastr.success('点赞成功');
                            star.attr('src', 'res/unstar.png');
                            star.attr('id', 'F');
                            var s = parseInt(starnum.text()) + 1;
                            starnum.html(s);
                        } else if (data.code == 105) {
                            toastr.warning('您已点赞');
                            star.attr('src', 'res/unstar.png');
                            star.attr('id', 'F');
                        } else {
                            toastr.warning(data.msg);
                        }
                    });
                } else {
                    delCourseStar({id: courseId, kindName: courseInfo.kindName}, function (data) {
                        if (data.code == 100) {
                            toastr.success('取消点赞成功');
                            star.attr('src', 'res/star.png');
                            star.attr('id', 'T');
                            var s = parseInt(starnum.text()) - 1;
                            starnum.html(s);
                        } else if (data.code == 106) {
                            toastr.warning('您已取消点赞');
                            star.attr('src', 'res/star.png');
                            star.attr('id', 'T');
                        } else {
                            toastr.warning(data.msg);
                        }
                    });
                }

            });

            like.on('click', function () {
                var likenum = $('.course-likenum');
                if (like.attr('id') == 'T') {
                    courseLike({id: courseId, kindName: courseInfo.kindName}, function (data) {
                        if (data.code == 100) {
                            toastr.success('收藏成功');
                            like.attr('src', 'res/unlike.png');
                            like.attr('id', 'F');
                            var s = parseInt(likenum.text()) + 1;
                            likenum.html(s);
                        } else if (data.code == 105) {
                            toastr.warning('您已收藏');
                            like.attr('src', 'res/unlike.png');
                            like.attr('id', 'F');
                        } else {
                            toastr.warning(data.msg);
                        }
                    });
                } else {
                    delCourseLike({id: courseId, kindName: courseInfo.kindName}, function (data) {
                        if (data.code == 100) {
                            toastr.success('取消收藏成功');
                            like.attr('src', 'res/like.png');
                            like.attr('id', 'T');
                            var s = parseInt(likenum.text()) - 1;
                            likenum.html(s);
                        } else if (data.code == 106) {
                            toastr.warning('您已取消收藏');
                            like.attr('src', 'res/like.png');
                            like.attr('id', 'T');
                        } else {
                            toastr.warning(data.msg);
                        }
                    });
                }

            });

            var chapterList = courseInfo.chapterList;
            console.log(chapterList);
            if (chapterList == null || chapterList.length == 0) {
                $('.course-chapter').append('<div style="text-align: center">暂无章节信息</div>');
            } else {
                var htmlstr = '';
                var temp;
                for (var i in chapterList) {
                    var index = parseInt(i) + 1;
                    temp = `<h1>Chapter ${index}:${chapterList[i].title}</h1>
                          <hr>
                          <div class="course-section">`;
                    var sectionList = chapterList[i].sectionList;
                    for (var t in sectionList) {
                        var s = parseInt(t) + 1;
                        temp += `<div onclick="window.location.href='section.html?id=${sectionList[t].id}'" class="section">Section ${s}: ${sectionList[t].title}</div>`;
                    }
                    temp += `</div><hr>`;
                    htmlstr += temp;
                }
                $('.course-chapter').append(htmlstr);
            }

        } else {
            toastr.warning(data.msg);
        }
    });

    showChapterComment({courseId: courseId, pageIndex: 1}, function (data) {
        console.log(data);
        if (data.code == 100) {
            commentList = data.data;
            if (commentList != null && commentList.length > 0) {

                new myPagination({
                    id: 'page',
                    curPage: 1, //初始页码
                    pageTotal: Math.ceil(parseInt(data.msg) / 10), //总页数
                    dataTotal: parseInt(data.msg), //总共多少条数据
                    pageSize: 10, //可选,分页个数
                    showPageTotalFlag: true, //是否显示数据统计
                    showSkipInputFlag: true, //是否支持跳转
                    getPage: function (page) {
                        var json = {courseId: courseId, pageIndex: page};
                        showChapterComment(json, function (data) {
                            commentList = data.data;
                            createCommentList();
                        });
                    }
                });

                createCommentList();
            }
        } else {
            toastr.warning(data.msg);
        }
    });

    $('#comment').on('click', function () {
        var content = $("#content").val();
        $("#content").val("");
        if (content != "") {
            var json = {courseId: courseId, content: content};
            commentOnChapter(json, function (data) {
                if (data.code == 100) {
                    toastr.success('评论成功');
                    setTimeout(function () {
                        window.location.reload()
                    }, 1000);
                } else toastr.warning(data.msg);
            });
        }
    });

}

init();


