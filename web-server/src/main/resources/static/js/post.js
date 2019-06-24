var userName = sessionStorage["userName"];
var userId = sessionStorage["userId"];
var postId = window.location.href.split("=")[1];
var postReplyList;


function doReply(replyList) {
    var arr = [];
    for (var i in replyList) {
        var obj = {};
        obj.id = replyList[i].id;
        obj.commentId = replyList[i].commentId;
        obj.fromId = replyList[i].fromId;
        obj.toId = replyList[i].toId;
        obj.replyName = replyList[i].fromName;
        obj.beReplyName = replyList[i].toName;
        obj.content = replyList[i].content;
        obj.ifStar = replyList[i].ifStar;
        obj.img = replyList[i].headimg;
        obj.replyBody = [];
        obj.Index = {x: 0, y: -1};
        var time = new Date(replyList[i].createdTime);
        obj.time = time.getFullYear() + "-" + filterNum(time.getMonth() + 1) + "-" + filterNum(time.getDate()) + " "
            + filterNum(time.getHours()) + ":" + filterNum(time.getMinutes());
        if (replyList[i].commentId != 0) {
            for (var j = 0; j < arr.length; j++) {
                if (arr[j].id == replyList[i].commentId) {
                    obj.Index.x = j;
                    obj.Index.y = arr[j].replyBody.length;
                    arr[j].replyBody.push(obj);
                    break;
                }
            }
        } else {
            obj.star = replyList[i].star;
            obj.Index.x = arr.length;
            arr.push(obj);
        }
    }
    return arr;
}

(function ($) {
    function crateCommentInfo(obj) {
        var del = "";
        if (obj.fromId == userId) {
            del = "<img src='res/del.png' class='del-btn'>";
        }

        if (typeof (obj.time) == "undefined" || obj.time == "") {
            obj.time = getNowDateFormat();
        }

        var starimg = "";
        if (obj.ifStar == null) {
            starimg = "<img id='T'src='res/star.png'class='star-btn'>";
        } else {
            starimg = "<img id='F'src='res/unstar.png'class='star-btn'>";
        }

        var el = "<div class='comment-info'><header><img src='\"resImgUrl + obj.img\"' onerror='this.src=\"res/default.jpg\"'></header><div class='comment-right'><h3>" + obj.replyName + "</h3>"
            + "<div class='comment-content-header'><span><i class='glyphicon glyphicon-time'></i>" + obj.time + "</span>";


        el = el + "</div><p  class='content'>" + obj.content + "</p><div class='comment-content-footer'><div class='row'><div class='col-md-10'>";


        el = el + "</div><div id='" + obj.Index.x + "," + obj.Index.y + "' class='col-md-2'>" + starimg +
            "<span id='" + obj.star + "' >" + obj.star + "</span>" + del + "<span class='reply-btn'>回复</span></div></div></div><div class='reply-list'>";
        if (obj.replyBody != "" && obj.replyBody.length > 0) {
            var arr = obj.replyBody;
            for (var j = 0; j < arr.length; j++) {
                var replyObj = arr[j];
                el = el + createReplyComment(replyObj);
            }
        }
        el = el + "</div></div></div>";
        return el;
    }

    //返回每个回复体内容
    function createReplyComment(reply) {
        var del = "";
        if (reply.fromId == userId) {
            del = "<img src='res/del.png' class='del-btn'>";
        }
        var replyEl = "<div class='reply'><div><a href='javascript:void(0)' class='replyname'>" + reply.replyName + "</a>:<a href='javascript:void(0)'>@" + reply.beReplyName + "</a><span>" + reply.content + "</span></div>"
            + "<p id='" + reply.Index.x + "," + reply.Index.y + "'><span>" + reply.time + "</span>" + del + " <span class='reply-list-btn'>回复</span></p></div>";
        return replyEl;
    }

    function getNowDateFormat() {
        var nowDate = new Date();
        var year = nowDate.getFullYear();
        var month = filterNum(nowDate.getMonth() + 1);
        var day = filterNum(nowDate.getDate());
        var hours = filterNum(nowDate.getHours());
        var min = filterNum(nowDate.getMinutes());
        return year + "-" + month + "-" + day + " " + hours + ":" + min;
    }

    function filterNum(num) {
        if (num < 10) {
            return "0" + num;
        } else {
            return num;
        }
    }

    function replyClick(el, fag) {
        el.parent().parent().append("<div class='replybox'><textarea cols='80' rows='50' placeholder='来说几句吧......' class='mytextarea' ></textarea><span class='send'>发送</span></div>")
            .find(".send").click(function () {
            var content = $(this).prev().val();
            if (content != "") {
                var parentEl = $(this).parent().parent().parent().parent();
                var idxs = $(el).parent().attr('id').toString().split(",");
                var idx = {x: idxs[0], y: idxs[1]};
                var obj = {};
                obj.replyName = userName;
                obj.content = content;
                obj.time = getNowDateFormat();
                obj.commentId = postReplyList[idx.x].id;
                obj.fromId = userId;
                if (fag == 1) { //一级回复
                    obj.toId = postReplyList[idx.x].fromId;
                    obj.beReplyName = postReplyList[idx.x].replyName;
                } else { //二级回复
                    obj.toId = postReplyList[idx.x].replyBody[idx.y].fromId;
                    obj.beReplyName = postReplyList[idx.x].replyBody[idx.y].replyName;
                }
                var json = {
                    commentId: obj.commentId,
                    toId: obj.toId,
                    content: obj.content
                };

                replyComment(json, function (data) {
                    if (data.code == 100) {
                        toastr.success('回复成功');
                        obj.reply_id = data.msg;//新建回复返回的id
                        obj.Index = {x: parseInt(idx.x), y: postReplyList[idx.x].replyBody.length};
                        postReplyList[idx.x].replyBody.push(obj);
                        var replyString = createReplyComment(obj);
                        parentEl.find(".reply-list").append(replyString);
                        var newReply = parentEl.find(".reply-list").find(".reply").last();
                        newReply.find(".reply-list-btn").click(function () {
                            if ($(this).parent().parent().find(".replybox").length > 0) {
                                $(".replybox").remove();
                            } else {
                                $(".replybox").remove();
                                replyClick($(this), 2);
                            }
                        });
                        newReply.find(".del-btn").click(function () {
                            if (confirm("删除回复？")) {
                                var index = $(this).parent();
                                var idxs = index.attr('id').toString().split(",");
                                var json = {id: postReplyList[idxs[0]].replyBody[idxs[1]].reply_id};
                                delReplyComment(json, function (data) {
                                    if (data.code == 100) {
                                        toastr.success('删除回复成功');
                                        index.parent().remove();
                                    } else {
                                        toastr.warning('删除回复失败');
                                    }
                                });
                            }
                        });
                    } else toastr.warning(data.msg);
                });
                $(".replybox").remove();
            } else {
                toastr.warning("回复内容为空");
            }
        });
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
            $(this).append(totalString).find(".reply-btn").click(function () {
                if ($(this).parent().parent().find(".replybox").length > 0) {
                    $(".replybox").remove();
                } else {
                    $(".replybox").remove();
                    replyClick($(this), 1);
                }
            });
            $(".reply-list-btn").click(function () {
                if ($(this).parent().parent().find(".replybox").length > 0) {
                    $(".replybox").remove();
                } else {
                    $(".replybox").remove();
                    replyClick($(this), 2);
                }
            });
            //点赞
            $(".star-btn").click(function () {
                var idxs = $(this).parent().attr('id').toString().split(",");
                var idx = {x: idxs[0], y: idxs[1]};
                var json = {id: postReplyList[idx.x].id};
                var img = $(this);
                var num = $(this).next();
                if (img.attr('id') == 'T') {
                    replyStar(json, function (data) {
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
                    delReplyStar(json, function (data) {
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
                    var idxs = index.attr('id').toString().split(",");
                    var idx = {x: idxs[0], y: idxs[1]};
                    if (idx.y == -1) {
                        var json = {id: postReplyList[idx.x].id};
                        delComment(json, function (data) {
                            if (data.code == 100) {
                                toastr.success('删除评论成功');
                                index.parent().parent().parent().parent().remove();
                            } else {
                                toastr.warning('删除评论失败');
                            }
                        });
                    } else {
                        var json = {id: postReplyList[idx.x].replyBody[idx.y].id};
                        delReplyComment(json, function (data) {
                            if (data.code == 100) {
                                toastr.success('删除回复成功');
                                index.parent().remove();
                            } else {
                                toastr.warning('删除回复失败');
                            }
                        });
                    }
                }
            });

        }

    }

})(jQuery);


function getCommentList() {
    var js = {currentPage: 1, id: postId};
    showReply(js, function (data) {
        if (data.code == 100) {
            new myPagination({
                id: 'page',
                curPage: 1, //初始页码
                pageTotal: Math.ceil(data.data.total / 10), //总页数
                dataTotal: parseInt(data.data.total), //总共多少条数据
                pageSize: 10, //可选,分页个数
                showPageTotalFlag: true, //是否显示数据统计
                showSkipInputFlag: true, //是否支持跳转
                getPage: function (page) {
                    var json = {currentPage: page, id: postId};
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
        $("#headimg").attr('src', resImgUrl + p.headimg);
        $('#headimg').attr('onerror', 'this.src="res/default.jpg"');
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
            var json = {id: postId, kindName: p.kindName};
            if (f == 'T') {
                postLike(json, function (data) {
                    if (data.code == 100) {
                        toastr.success('收藏成功');
                        $("#like").attr('src', 'res/unlike.png');
                        $("#like").attr('name', 'F');
                        $("#likenum").html(parseInt($("#likenum").html()) + 1);
                    } else if (data.code == 105) {
                        toastr.warning('您已收藏');
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
                        toastr.warning('您已取消收藏');
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
            var json = {id: postId, kindName: p.kindName};
            if (f == 'T') {
                postStar(json, function (data) {
                    if (data.code == 100) {
                        toastr.success('点赞成功');
                        $("#post-star").attr('src', 'res/unstar.png');
                        $("#post-star").attr('name', 'F');
                        $("#starnum").html(parseInt($("#starnum").html()) + 1);
                    } else if (data.code == 105) {
                        toastr.warning('您已点赞');
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
                        toastr.warning('您已取消点赞');
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
