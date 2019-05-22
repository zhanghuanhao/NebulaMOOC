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
        if (obj.ifStar == null) {
            starimg = "<img id='T'src='res/star.png'class='star-btn'>";
        } else {
            starimg = "<img id='F'src='res/unstar.png'class='star-btn'>";
        }

        var el = `<div class='comment-info'>
                      <header>
                          <img src='https://nebula-head.oss-cn-shenzhen.aliyuncs.com/${obj.img}/head100'>
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
                var json = {id: postReplyList[idx].id};
                var img = $(this);
                var num = $(this).next();
                if (img.attr('id') == 'T') {
                    replyStar(json, function (data) {
                        if (data.code == 100) {
                            toastr.success('已点赞');
                            img.attr('src', 'res/unstar.png');
                            img.attr('id', 'F');
                            var s = parseInt(num.attr('id')) + 1;
                            num.html(s);
                            num.attr('id', s);
                        } else if (data.code == 105) {
                            toastr.warning(data.msg);
                            img.attr('src', 'res/unstar.png');
                            img.attr('id', 'F');
                        } else {
                            toastr.warning(data.msg);
                        }
                    });
                } else {
                    delReplyStar(json, function (data) {
                        if (data.code == 100) {
                            toastr.success('已取消点赞');
                            img.attr('src', 'res/star.png');
                            img.attr('id', 'T');
                            var s = parseInt(num.attr('id')) - 1;
                            num.html(s);
                            num.attr('id', s);
                        } else if (data.code == 106) {
                            toastr.warning(data.msg);
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
                    var json = {id: postReplyList[idx].id};
                    delComment(json, function (data) {
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
    console.log(courseId);
    showChapterInfo({courseId: courseId}, function (data) {
        console.log(data);
        if (data.code == 100) {

        } else {
            toastr.warning(data.msg);
        }
    });

    showChapterComment({courseId: courseId, pageIndex: 1}, function (data) {
        console.log(data);
        if (data.code == 100) {
            commentList = data.data;
            if (commentList != null && commentList.length > 0) {
                if (parseInt(data.msg) > 10) {
                    $(".pagediv").createPage({
                        pageNum: Math.ceil(parseInt(data.msg) / 10),
                        current: 1,
                        backfun: function (e) {
                            var json = {pageIndex: e.current};
                            showCourseList(json, function (data) {
                                commentList = data.data;
                                createCommentList();
                            });
                        }
                    });
                } else {
                    $('.comment-list').empty();
                    $('.pagediv').empty();
                }
                createCommentList();
            } else {
                $('.comment-list').empty();
                $('.pagediv').empty();
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
                    createCommentList();
                } else toastr.warning(data.msg);
            });
        }
    });

}

init();