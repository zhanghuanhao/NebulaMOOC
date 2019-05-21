function changecolor(d) {
    var list = document.getElementsByName("li1");
    for (var i = 0; i < list.length; i++) {
        if (list[i].id != d) {
            list[i].style.backgroundColor = "transparent";
            list[i].className = "unselected";
        } else {
            list[i].style.backgroundColor = "#1E9FFF";
            list[i].className = "selected";
        }
    }
}

function mouseover(d) {
    var se = document.getElementById(d);
    if (se.className != "selected") {
        se.style.backgroundColor = "rgba(0,0,0,0.2)";
    }
}

function mouseout(d) {
    var se = document.getElementById(d);
    if (se.className != "selected") se.style.backgroundColor = "transparent";
}

var kind = 0;
var courseList;


function createCourseList() {
    $('.course-list-body').children().remove();
    var htmlstr = "";
    var temp;
    for (var i in courseList) {
        var time = new Date(courseList[i].createdTime);
        var coursetime = time.getFullYear() + "-" + filterNum(time.getMonth() + 1) + "-" + filterNum(time.getDate()) + " "
            + filterNum(time.getHours()) + ":" + filterNum(time.getMinutes());

        temp = ` <div class="one-course" onclick='window.open(&#39;chapter.html?id=${postList[i].id}&#39;)'>
            <div class="course-info-left">
                <img class="course-img" src="res/img.jpg">
            </div>
            <div class="course-info-center">
                <div>
                <span class="course-title">${courseList[i].title}</span>
                <span class="course-kind">${courseList[i].kindName}</span>
                </div>
                <div class="course-introduce">${courseList[i].introduction}</div>
            </div>
            <div class="course-info-right">
                <img class="course-head-img" src="res/img.jpg">
                <div class="course-userName">${courseList[i].userNickName}</div>
                <div class="course-time">${coursetime}</div>
                <div class="course-info-right-bottom">
                    <img class="course-like" src="res/like.png">
                    <span class="course-like-num">${courseList[i].like}</span>
                    <img src="res/star.png">
                    <span class="course-star-num">${courseList[i].star}</span>
                </div>
            </div>
        </div>`;

        htmlstr += temp;
    }
    $('.course-list-body').append(htmlstr);
}

function getCourseList() {
    var js = {pageIndex: 1, kind: kind};
    showCourseList(js, function (data) {
        console.log(data);
        if (data.code == 100) {
            courseList = data.data;
            if (courseList != null && courseList.length > 0) {
                $(".pagediv").updatePage({
                    pageNum: Math.ceil(parseInt(data.msg) / 10),
                    current: 1
                });
                createCourseList();
            } else {
                $('.course-list-body').empty();
                $('.pagediv').empty();
            }
        } else {
            toastr.error('获取失败');
        }
    });
}

function init() {
    var li1 = document.getElementsByName('li1');
    for (var i = 0; i < li1.length; i++) {
        li1[i].onclick = function () {
            changecolor(this.id);
            kind = parseInt(this.id);
            getCourseList();
        };
    }
    var js = {pageIndex: 1, kind: kind};
    showCourseList(js, function (data) {
        console.log(data);
        if (data.code == 100) {
            courseList = data.data;
            if (courseList != null && courseList.length > 0) {
                $(".pagediv").createPage({
                    pageNum: Math.ceil(parseInt(data.msg) / 10),
                    current: 1,
                    backfun: function (e) {
                        var json = {pageIndex: e.current, kind: kind};
                        showCourseList(json, function (data) {
                            courseList = data.data;
                            createCourseList();
                        });
                    }
                });
                createCourseList();
            }
        } else {
            toastr.error('获取失败');
        }
    });
}

init();
