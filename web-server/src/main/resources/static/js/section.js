var sectionId = window.location.href.split("=")[1];
var userId = localStorage["userId"];
var userName = localStorage["userName"];
var sectionReplyList;


function doReply(replyList) {
    var arr = [];
    for (var i in replyList) {
        var obj = {};
        obj.id = replyList[i].id;
        obj.fromId = replyList[i].userId;
        obj.replyName = replyList[i].userNickName;
        obj.content = replyList[i].content;
        obj.ifStar = replyList[i].ifStar;
        obj.img = replyList[i].userHeadUrl;
        obj.star = replyList[i].star;
        obj.replyBody = [];
        obj.Index = {x: arr.length, y: -1};
        var time = new Date(replyList[i].createdTime);
        obj.time = time.getFullYear() + "-" + filterNum(time.getMonth() + 1) + "-" + filterNum(time.getDate()) + " "
            + filterNum(time.getHours()) + ":" + filterNum(time.getMinutes());

        arr.push(obj);

        var reply = replyList[i].reply;
        if (reply != null && reply.length > 0) {
            for (var j in reply) {
                var robj = {};
                robj.id = reply[j].id;
                robj.Index = {x: parseInt(arr.length) - 1, y: arr[i].replyBody.length};
                robj.commentId = reply[j].commentId;
                robj.content = reply[j].content;
                robj.fromId = reply[j].fromId;
                robj.toId = reply[j].toId;
                robj.replyName = reply[j].fromUserNickName;
                robj.beReplyName = reply[j].toUserNickName;
                robj.headUrl = reply[j].fromUserHeadUrl;
                var time = new Date(reply[j].createdTime);
                robj.time = time.getFullYear() + "-" + filterNum(time.getMonth() + 1) + "-" + filterNum(time.getDate()) + " "
                    + filterNum(time.getHours()) + ":" + filterNum(time.getMinutes());

                arr[i].replyBody.push(robj);
            }
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
        if (obj.ifStar == false) {
            starimg = "<img id='T'src='res/star.png'class='star-btn'>";
        } else {
            starimg = "<img id='F'src='res/unstar.png'class='star-btn'>";
        }

        var el = "<div class='comment-info'><header><img src='" + resImgUrl + obj.img + "'></header><div class='comment-right'><h3>" + obj.replyName + "</h3>"
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
                obj.commentId = sectionReplyList[idx.x].id;
                obj.fromId = userId;
                if (fag == 1) { //一级回复
                    obj.toId = sectionReplyList[idx.x].fromId;
                    obj.beReplyName = sectionReplyList[idx.x].replyName;
                } else { //二级回复
                    console.log(idx);
                    console.log(sectionReplyList[idx.x]);
                    obj.toId = sectionReplyList[idx.x].replyBody[idx.y].fromId;
                    obj.beReplyName = sectionReplyList[idx.x].replyBody[idx.y].replyName;
                }
                var json = {
                    commentId: obj.commentId,
                    toId: obj.toId,
                    content: obj.content
                };

                sectionCommentReply(json, function (data) {
                    if (data.code == 100) {
                        toastr.success('回复成功');
                        obj.reply_id = data.msg;//新建回复返回的id
                        obj.Index = {x: parseInt(idx.x), y: sectionReplyList[idx.x].replyBody.length};
                        sectionReplyList[idx.x].replyBody.push(obj);
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
                                var json = {id: sectionReplyList[idxs[0]].replyBody[idxs[1]].id};
                                delSectionCommentReply(json, function (data) {
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
                var json = {id: sectionReplyList[idx.x].id};
                var img = $(this);
                var num = $(this).next();
                if (img.attr('id') == 'T') {
                    sectionCommentStar(json, function (data) {
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
                    delSectionCommentStar(json, function (data) {
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
                        var json = {id: sectionReplyList[idx.x].id};
                        delSectionComment(json, function (data) {
                            if (data.code == 100) {
                                toastr.success('删除评论成功');
                                index.parent().parent().parent().parent().remove();
                            } else {
                                toastr.warning('删除评论失败');
                            }
                        });
                    } else {
                        var json = {id: sectionReplyList[idx.x].replyBody[idx.y].id};
                        delSectionCommentReply(json, function (data) {
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
    var js = {sectionId: sectionId, pageIndex: 1};
    getCourseSectionCommentList(js, function (data) {
        if (data.code == 100) {

            new myPagination({
                id: 'page',
                curPage: 1, //初始页码
                pageTotal: Math.ceil(parseInt(data.msg) / 10), //总页数
                dataTotal: parseInt(data.msg), //总共多少条数据
                pageSize: 10, //可选,分页个数
                showPageTotalFlag: true, //是否显示数据统计
                showSkipInputFlag: true, //是否支持跳转
                getPage: function (page) {
                    var json = {sectionId: sectionId, pageIndex: page};
                    getCourseSectionCommentList(json, function (data) {
                        sectionReplyList = doReply(data.data);
                        showReplyList(sectionReplyList, sectionId);
                    });
                }
            });

            sectionReplyList = doReply(data.data);
            showReplyList(sectionReplyList, sectionId);
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
            var json = {sectionId: sectionId, content: content};
            sectionComment(json, function (data) {
                if (data.code == 100) {
                    toastr.success('评论成功');
                    setTimeout(function () {
                        window.location.reload();
                    }, 1000);
                } else toastr.warning(data.msg);
            });
        }
    });
}


function init() {
    getCourseSection({sectionId: sectionId}, function (data) {
        if (data.code == 100) {
            var section = data.data;
            $('.section-title').html(section.title);
            $('.section-content').html(section.introduction);
            if (section.videoUrl == null) {
                $('.section-body-bottom').append(`
              <h1>暂无课程资源</h1>
              `);
            } else {
                $('.section-body-bottom').append(`
              <video  src="${resVideoUrl + section.videoUrl}" controls="controls"></video>
              `);
            }
        } else {
            toastr('获取课程失败');
        }
    });

    getCommentList();
}

init();