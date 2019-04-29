(function ($) {
    function crateCommentInfo(obj) {

        if (typeof (obj.time) == "undefined" || obj.time == "") {
            obj.time = getNowDateFormat();
        }

        var el = "<div class='comment-info'><header><img src='" + obj.img + "'></header><div class='comment-right'><h3>" + obj.replyName + "</h3>"
            + "<div class='comment-content-header'><span><i class='glyphicon glyphicon-time'></i>" + obj.time + "</span>";


        el = el + "</div><p  class='content'>" + obj.content + "</p><div class='comment-content-footer'><div class='row'><div class='col-md-10'>";


        el = el + "</div><div id='" + obj.reply_id + "'  class='col-md-2'><img id='T'src='res/star.png'class='star-btn'>" +
            "<span id='" + obj.star + "' >:" + obj.star + "</span><span class='reply-btn'>回复</span></div></div></div><div class='reply-list'>";
        if (obj.replyBody != "" && obj.replyBody.length > 0) {
            var arr = obj.replyBody;
            for (var j = 0; j < arr.length; j++) {
                var replyObj = arr[j];
                el = el + createReplyComment(replyObj, obj.reply_id);
            }
        }
        el = el + "</div></div></div>";
        return el;
    }

    //返回每个回复体内容
    function createReplyComment(reply, fid) {
        var replyEl = "<div class='reply'><div><a href='javascript:void(0)' class='replyname'>" + reply.replyName + "</a>:<a href='javascript:void(0)'>@" + reply.beReplyName + "</a><span>" + reply.content + "</span></div>"
            + "<p id='" + fid + "' name='" + reply.reply_id + "'><span>" + reply.time + "</span> <span class='reply-list-btn'>回复</span></p></div>";
        return replyEl;
    }

    function getNowDateFormat() {
        var nowDate = new Date();
        var year = nowDate.getFullYear();
        var month = filterNum(nowDate.getMonth() + 1);
        var day = filterNum(nowDate.getDate());
        var hours = filterNum(nowDate.getHours());
        var min = filterNum(nowDate.getMinutes());
        var seconds = filterNum(nowDate.getSeconds());
        return year + "-" + month + "-" + day + " " + hours + ":" + min + ":" + seconds;
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
                var obj = {};
                obj.replyName = userName;
                if (fag == 1) { //一级回复
                    obj.beReplyName = parentEl.find("h3").text();
                    obj.fatherReplyId = el.parent().attr('id');
                    obj.fatherId = obj.fatherReplyId;
                } else { //二级回复
                    obj.beReplyName = el.parent().parent().find("a:first").text();
                    obj.fatherReplyId = el.parent().attr('id');
                    obj.fatherId = el.parent().attr('name');
                }
                obj.content = content;
                obj.time = getNowDateFormat();

                $(".replybox").remove();
                var json = {
                    postId: postId,
                    fatherReplyId: obj.fatherReplyId,
                    fatherId: obj.fatherId,
                    content: obj.content
                };
                postReply(json, function (data) {
                    if (data.code == 100) {
                        toastr.success('回复成功');
                        obj.reply_id = data.msg;//新建回复返回的id
                        var replyString = createReplyComment(obj, obj.fatherReplyId);
                        parentEl.find(".reply-list").append(replyString);
                        $(".reply-list-btn").click(function () {
                            if ($(this).parent().parent().find(".replybox").length > 0) {
                                $(".replybox").remove();
                            } else {
                                $(".replybox").remove();
                                replyClick($(this), 2);
                            }
                        });
                    } else toastr.warning(data.msg);
                });
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
                var json = {id: $(this).parent().attr('id')};
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