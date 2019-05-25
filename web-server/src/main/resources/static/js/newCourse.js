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
    e.find('.section-list').append(selecter);
}

function createSelecter() {
    var htmlstr = `<span>视频:</span>><select class="video-selecter">`;
    for (var i in videoList) {
        htmlstr += `<option value="${videoList[i].url}">${videoList[i].filename}</option>`;
    }
    htmlstr += `</select>`;
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

}

init();