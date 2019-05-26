var videoList = [];
var course_img = null;
var chapterList = null;

function showVideoList() {
    $('.pre-video-list').empty();
    getVideoList(function (data) {
        if (data.code == 100) {
            console.log(data);
            videoList = data.data;
            if (videoList != null & videoList.length > 0) {
                var htmlstr = '';
                var temp;
                for (var i in videoList) {
                    var s;
                    if (videoList[i].isupload) {
                        s = '已审核';
                    } else {
                        s = '审核中';
                    }
                    temp = `<div class="one-video">
                             <div class="one-video-left">${videoList[i].filename}</div>
                             <div class="one-video-right">
                                 <div class="status">${s}</div>
                             </div>
                         </div>`;
                    htmlstr += temp;
                }
                $('.pre-video-list').append(htmlstr);
            }
        } else {
            toastr.warning(data.msg);
        }
    });
}


function new_secion(e) {
    var selecter = createSelecter();
    var htmlstr = `<div class="one-section"><span>节:</span><div class="section-title"><span>标题:</span><input class="section-title-input" placeholder="25字以内" maxlength="25"></div>
                 <div class="section-introduction"><div>简介:</div><textarea class="section-introduction-input" placeholder="100字以内" maxlength="100"></textarea></div>`;
    e.find('.section-list').append(htmlstr + selecter);
}

function createSelecter() {
    var htmlstr = `<div><span>视频:</span><select class="video-selecter">`;
    for (var i in videoList) {
        htmlstr += `<option value="${videoList[i].videoUrl}">${videoList[i].filename}</option>`;
    }
    htmlstr += `</select></div></div><hr>`;
    return htmlstr;
}


function init() {
    $('.choice-video').on('click', function () {
        $('.videoChoice').click();
    });

    $('.videoChoice').on('change', function (e) {
        var filemaxsize = 1024 * 100;//100M
        var target = $(e.target);
        var Size = target[0].files[0].size / 1024;
        if (Size > filemaxsize) {
            alert('视频文件过大，请重新选择!');
            return;
        }
        if (!this.files[0].type.match(/mp4.*/)) {
            alert('请选择正确的视频格式!');

        } else {
            console.log('aaaa');
            upLoadVideo(target[0].files[0], function (data) {
                if (data.code == 100) {
                    toastr.success('上传成功');
                    showVideoList();
                } else {
                    toastr.warning(data.msg);
                }
            });
        }
    });

    showVideoList();


    $('.choice-img').on('click', function () {
        $('.imgChoice').click();
    });

    $('.imgChoice').on('change', function (e) {
        var filemaxsize = 1024 * 5;//5M
        var target = $(e.target);
        var Size = target[0].files[0].size / 1024;
        if (Size > filemaxsize) {
            alert('图片过大，请重新选择!');
            return;
        }
        if (!this.files[0].type.match(/image.*/)) {
            alert('请选择正确的图片!');

        } else {
            course_img = target[0].files[0];
            var read = new FileReader();
            read.readAsDataURL(course_img);
            read.onload = function (e) {
                $('.course-img').attr('src', e.target.result);
            };
        }

    });


    $('.new-chapter').on('click', function () {
        var htmlstr = `<div class="one-chapter">
                        <span>章:</span>
                        <input class="chapter-input" placeholder="20字以内" maxlength="20">
                        <hr>
                        <div class="section-list">
                        </div>
                        <input class="new-section btn btn-primary" type="button" value="新建一节" onclick="new_secion($(this).parent());">
                    </div>`;

        $('.chapter-body').append(htmlstr);
    });


    $('.ensureNewCourse').on('click', function () {
        if (course_img == null) {
            toastr.warning('请上传封面图！');
            return;
        }
        var chapterListBuilder = $('.chapter-body').find('.one-chapter');
        if (chapterListBuilder == null || chapterListBuilder.length == 0) {
            toastr.warning('请构建章节信息！');
            return;
        }

        var courseTitle = $('.course-title').val();
        if (courseTitle == null || courseTitle == '') {
            toastr.warning('请输入课程标题');
            return;
        }

        var courseIntroduction = $('.course-introduction').val();
        if (courseIntroduction == null || courseIntroduction == '') {
            toastr.warning('请输入课程介绍');
            return;
        }

        var courseJson = {title: courseTitle, introduction: courseIntroduction, chapterList: []};

        for (var i = 0; i < chapterListBuilder.length; i++) {
            var chapterTitle = chapterListBuilder.eq(i).find('.chapter-input').eq(0).val();
            if (chapterTitle == null || chapterTitle == '') {
                toastr.warning('请输入章节标题！');
                return;
            }

            var sectionListBuilder = chapterListBuilder.eq(i).find('.one-section');
            if (sectionListBuilder == null || sectionListBuilder.length == 0) {
                toastr.warning('请创建章节信息！');
                return;
            }

            var sectionList = [];

            for (var j = 0; j < sectionListBuilder.length; j++) {
                var sectionTitle = sectionListBuilder.eq(j).find('.section-title-input').eq(0).val();
                if (sectionTitle == null || sectionTitle == '') {
                    toastr.warning('请输入节标题！');
                    return;
                }

                var sectionIntroduction = sectionListBuilder.eq(j).find('.section-introduction-input').eq(0).val();
                if (sectionIntroduction == null || sectionIntroduction == '') {
                    toastr.warning('请输入节介绍！');
                    return;
                }

                var videoUrl = sectionListBuilder.eq(j).find('.video-selecter option:selected').eq(0).val();
                if (videoUrl == null || videoUrl == '') {
                    toastr.warning('请选择视频！');
                    return;
                }

                sectionList.push({title: sectionTitle, introduction: sectionIntroduction, videoUrl: videoUrl});
            }

            courseJson.chapterList.push({title: chapterTitle, sectionList: sectionList});
        }


        $('.ensureNewCourse').attr('disabled', true);
        $('.ensureNewCourse').val('创建中。。。');

        console.log(courseJson);

        newCourse(courseJson, $('.course-kind option:selected').val(), course_img, function (data) {
            if (data.code == 100) {
                toastr.success('创建课程成功！');
            } else {
                toastr.warning('创建课程失败！');
            }
            $('.ensureNewCourse').removeAttr('disabled');
            $('.ensureNewCourse').val('创建课程');
        }, $('.ensureNewCourse'));

    });

}

init();