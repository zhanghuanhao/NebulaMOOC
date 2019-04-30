var userName = localStorage["userName"];
var userId = localStorage["userId"];
var imgpath = "res/img.jpg";
var postId = window.location.href.split("=")[1];
var postReplyList;

//分页插件
$(".pagediv").createPage({
    pageNum: 20,
    current: 1,
    backfun: function (e) {
        console.log(e);//回调
        console.log('current:' + e.current);
    }
});


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
    showPost(testJSON, function () {

    });

    showReply(testJSON, function (data) {
            var p = data.data;
            if (data.code == 100) {
                console.log(p);
                postReplyList = doReply(p);
                console.log(postReplyList);
                showReplyList(postReplyList, postId);
            } else {
                toastr.warning("获取回复失败");
            }
        }
    );
}

showPostAndReply();
