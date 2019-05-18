var userName = localStorage["userName"];
var userId = localStorage["userId"];
var postId = window.location.href.split("=")[1];
var postReplyList;


function getCommentList() {
    var js = {currentPage: 1, id: postId};
    showReply(js, function (data) {
        console.log(data);
        if (data.code == 100) {
            $(".pagediv").createPage({
                pageNum: Math.ceil(data.data.total / 10),
                current: 1,
                backfun: function (e) {
                    var json = {currentPage: e.current, id: postId};
                    showReply(json, function (data) {
                        postReplyList = doReply(data.data.list);
                        showReplyList(postReplyList, postId);
                    });
                }
            });
            postReplyList = doReply(data.data.list);
            showReplyList(postReplyList, postId);
        } else {
            toastr.error('获取失败');
        }
    });
}

function showReplyList(arr) {
    $(".comment-list").empty();
    $(".comment-list").addCommentList({data: arr, add: ""});
    $("#comment").click(function () {
        var content = $("#content").val();
        $("#content").val("");
        if (content != "") {
            var json = {postId: postId, content: content};
            comment(json, function (data) {
                if (data.code == 100) {
                    toastr.success('评论成功');
                    setTimeout(function () {
                        window.location.reload();
                    }, 2000);
                } else toastr.warning(data.msg);
            });
        }
    });
}


function showPostAndReply() {
    var testJSON = {id: postId};
    showPost(testJSON, function (data) {
        var p = data.data;
        $("#headimg").attr('src', 'https://nebula-head.oss-cn-shenzhen.aliyuncs.com/' + p.headimg + '/head100');
        $(".nickName").html(p.nickName);
        $(".post-title").html(p.title);
        $(".post-content").html(p.content);
        var time = new Date(p.createdTime);
        $(".post-time").html(time.getFullYear() + "-" + filterNum(time.getMonth() + 1) + "-" +
            filterNum(time.getDate()) + " " + filterNum(time.getHours()) + ":" + filterNum(time.getMinutes()));
        $("#likenum").html(p.like);
        $("#starnum").html(p.star);
        if (p.ifLike == null) {
            $("#like").attr('src', 'res/like.png');
            $("#like").attr('name', 'T');
        } else {
            $("#like").attr('src', 'res/unlike.png');
            $("#like").attr('name', 'F');
        }
        $("#like").click(function () {
            var f = $("#like").attr('name');
            var json = {id: postId};
            if (f == 'T') {
                postLike(json, function (data) {
                    if (data.code == 100) {
                        toastr.success('收藏成功');
                        $("#like").attr('src', 'res/unlike.png');
                        $("#like").attr('name', 'F');
                        $("#likenum").html(parseInt($("#likenum").html()) + 1);
                    } else if (data.code == 105) {
                        toastr.warning(data.msg);
                        $("#like").attr('src', 'res/unlike.png');
                        $("#like").attr('name', 'F');
                    } else {
                        toastr.warning(data.msg);
                    }
                })
            } else {
                delLike(json, function (data) {
                    if (data.code == 100) {
                        toastr.success('取消收藏成功');
                        $("#like").attr('src', 'res/like.png');
                        $("#like").attr('name', 'T');
                        $("#likenum").html(parseInt($("#likenum").html()) - 1);
                    } else if (data.code == 106) {
                        toastr.warning(data.msg);
                        $("#like").attr('src', 'res/like.png');
                        $("#like").attr('name', 'T');
                    } else {
                        toastr.warning(data.msg);
                    }
                })
            }
        });

        if (p.ifStar == null) {
            $("#post-star").attr('src', 'res/star.png');
            $("#post-star").attr('name', 'T');
        } else {
            $("#post-star").attr('src', 'res/unstar.png');
            $("#post-star").attr('name', 'F');
        }

        $("#post-star").click(function () {
            var f = $("#post-star").attr('name');
            var json = {id: postId};
            if (f == 'T') {
                postStar(json, function (data) {
                    if (data.code == 100) {
                        toastr.success('点赞成功');
                        $("#post-star").attr('src', 'res/unstar.png');
                        $("#post-star").attr('name', 'F');
                        $("#starnum").html(parseInt($("#starnum").html()) + 1);
                    } else if (data.code == 105) {
                        toastr.warning(data.msg);
                        $("#post-star").attr('src', 'res/unstar.png');
                        $("#post-star").attr('name', 'F');
                    } else {
                        toastr.warning(data.msg);
                    }
                })
            } else {
                delPostStar(json, function (data) {
                    if (data.code == 100) {
                        toastr.success('取消点赞成功');
                        $("#post-star").attr('src', 'res/star.png');
                        $("#post-star").attr('name', 'T');
                        $("#starnum").html(parseInt($("#starnum").html()) - 1);
                    } else if (data.code == 106) {
                        toastr.warning(data.msg);
                        $("#post-star").attr('src', 'res/star.png');
                        $("#post-star").attr('name', 'T');
                    } else {
                        toastr.warning(data.msg);
                    }
                })
            }
        });


    });

    getCommentList();

}

showPostAndReply();
