(function ($) {
    function crateCommentInfo(obj) {
        var del = "";
        if (obj.fromId == userId) {
            del = "<img src='res/del.png' class='del-btn'>";
        }

        if (typeof (obj.time) == "undefined" || obj.time == "") {
            obj.time = getNowDateFormat();
        }

        var el = "<div class='comment-info'><header><img src='" + obj.img + "'></header><div class='comment-right'><h3>" + obj.replyName + "</h3>"
            + "<div class='comment-content-header'><span><i class='glyphicon glyphicon-time'></i>" + obj.time + "</span>";


        el = el + "</div><p  class='content'>" + obj.content + "</p><div class='comment-content-footer'><div class='row'><div class='col-md-10'>";


        el = el + "</div><div id='" + obj.Index.x + "," + obj.Index.y + "' class='col-md-2'><img id='T'src='res/star.png'class='star-btn'>" +
            "<span id='" + obj.star + "' >:" + obj.star + "</span>" + del + "<span class='reply-btn'>回复</span></div></div></div><div class='reply-list'>";
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
                obj.commitId = postReplyList[idx.x].id;
                obj.fromId = userId;
                if (fag == 1) { //一级回复
                    obj.toId = postReplyList[idx.x].fromId;
                    obj.beReplyName = postReplyList[idx.x].replyName;
                } else { //二级回复
                    obj.toId = postReplyList[idx.x].replyBody[idx.y].fromId;
                    obj.beReplyName = postReplyList[idx.x].replyBody[idx.y].replyName;
                }
                var json = {
                    commitId: obj.commitId,
                    toId: obj.toId,
                    content: obj.content
                };

                replyCommit(json, function (data) {
                    if (data.code == 100) {
                        toastr.success('回复成功');
                        obj.reply_id = data.msg;//新建回复返回的id
                        obj.Index = {x: idx.x, y: parseInt(idx.y) + 1};
                        postReplyList[idx.x].replyBody.push(obj);
                        var replyString = createReplyComment(obj);
                        parentEl.find(".reply-list").append(replyString);
                        parentEl.find(".reply-list").find(".reply").last()
                            .find(".reply-list-btn").click(function () {
                            if ($(this).parent().parent().find(".replybox").length > 0) {
                                $(".replybox").remove();
                            } else {
                                $(".replybox").remove();
                                replyClick($(this), 2);
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
            $(".star-btn").click(function () {
                var idxs = $(this).parent().attr('id').toString().split(",");
                var idx = {x: idxs[0], y: idxs[1]};
                var json = {id: postReplyList[idx.x].id};
                var img = $(this);
                var num = $(this).next();
                if (img.attr('id') == 'T') {
                    replyStar(json, function (data) {
                        if (data.code == 100) {
                            toastr.success('已点赞');
                            img.attr('src', 'res/unstar.png');
                            img.attr('id', 'F');
                            var s = parseInt(num.attr('id')) + 1;
                            num.html(':' + s);
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
                            num.html(':' + s);
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

        }

    }

})(jQuery);