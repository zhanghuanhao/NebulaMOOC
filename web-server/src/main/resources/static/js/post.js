var userName = localStorage["userName"];
var userId = localStorage["userId"];
var imgpath = "res/img.jpg";
var postId = window.location.href.split("=")[1];
var postReplyList;


function getPostList() {
    var js = {currentPage: 1};
    showPostList(js, function (data) {
        if (data.code == 100) {
            $(".pagediv").createPage({
                pageNum: data.data.total / 10,
                current: 1,
                backfun: function (e) {
                    var json = {currentPage: e.current};
                    showPostList(json, function (data) {
                        console.log(data.data.list);
                    });
                }
            });
            console.log(data.data.list);
        } else {
            toastr.error('获取失败');
        }
    })
}

function showReplyList(arr) {
    $(".comment-list").addCommentList({data: arr, add: ""});
    $("#comment").click(function () {
        var content = $("#content").val();
        $("#content").val("");
        if (content != "") {
            var json = {postId: postId, content: content};
            commit(json, function (data) {
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
        $(".nickName").html(p.nickName);
        $(".post-title").html(p.title);
        $(".post-content").html(p.content);
        var time = new Date(p.createdTime);
        $(".post-time").html(time.getFullYear() + "-" + filterNum(time.getMonth() + 1) + "-" +
            filterNum(time.getDate()) + " " + filterNum(time.getHours()) + ":" + filterNum(time.getMinutes()));
    });

    showReply(testJSON, function (data) {
            if (data.code == 100) {
                postReplyList = doReply(data.data);
                showReplyList(postReplyList, postId);
            } else {
                toastr.warning("获取回复失败");
            }
        }
    );
}

getPostList();
showPostAndReply();
