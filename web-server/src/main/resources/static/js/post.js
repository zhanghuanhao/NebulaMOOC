var userName = "test-user";
var imgpath = "res/img.jpg";
var postId = localStorage["postId"];


//分页插件
$(".pagediv").createPage({
    pageNum: 20,
    current: 1,
    backfun: function (e) {
        console.log(e);//回调
        console.log('current:' + e.current);
    }
});


function showreplylist(arr) {
    $(".comment-list").addCommentList({data: arr, add: ""});
    $("#comment").click(function () {
        var content = $("#content").val();
        $("#content").val("");
        if (content != "") {
            var json = {postId: postId, fatherReplyId: -1, fatherId: -1, content: content};
            postReply(json, function (data) {
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


function getReply() {
    var testJSON = {id: postId};
    showReply(testJSON, function (data) {
            var p = data.data;
            if (data.code == 100) {
                console.log(p);
                showreplylist(doReply(p), postId);
            } else {
                toastr.warning("获取回复失败");
            }
        }
    );
}

getReply();