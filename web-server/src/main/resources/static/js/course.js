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

var kind = null;
var courseList;


function createCourseList() {

}

function getCourseList() {
    var js = {pageIndex: 1, kindName: kind};
    showCourseList(js, function (data) {
        console.log(data);
        if (data.code == 100) {
            courseList = data.data;
            if (courseList != null && courseList.length > 0) {
                $(".pagediv").updatePage({
                    pageNum: Math.ceil(data.msg / 10),
                    current: 1
                });
                createCourseList();
            } else {
                //$('.post-list-body').empty();
                $('.pagediv').empty();
            }
        } else {
            toastr.error('获取失败');
        }
    });
}

function init() {
    var li1 = document.getElementsByName('li1');
    li1[0].onclick = function () {
        changecolor(this.id);
        kind = null;
        getCourseList();
    };
    for (var i = 1; i < li1.length; i++) {
        li1[i].onclick = function () {
            changecolor(this.id);
            kind = this.innerText;
            getCourseList();
        };
    }
    var js = {pageIndex: 1, kindName: kind};
    showCourseList(js, function (data) {
        console.log(data);
        if (data.code == 100) {
            courseList = data.data;
            if (courseList != null && courseList.length > 0) {
                $(".pagediv").createPage({
                    pageNum: Math.ceil(data.msg / 10),
                    current: 1,
                    backfun: function (e) {
                        var json = {pageIndex: e.current, kindName: kind};
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
