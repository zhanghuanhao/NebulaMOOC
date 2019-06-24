function changecolor2(d) {
    var list = document.getElementsByName("li2");
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


var courseList;
var postList;


function createCourseList() {
    $('.course-list-body').children().remove();
    var htmlstr = "";
    var temp;
    for (var i in courseList) {
        var time = new Date(courseList[i].createdTime);
        var coursetime = time.getFullYear() + "-" + filterNum(time.getMonth() + 1) + "-" + filterNum(time.getDate()) + " "
            + filterNum(time.getHours()) + ":" + filterNum(time.getMinutes());

        temp = ` <div class="one-course" onclick='window.open(&#39;chapter.html?id=${courseList[i].id}&#39;)'>
            <div class="course-info-left">
                <img class="course-img" src='${resImgUrl + courseList[i].courseHeadUrl}' onerror='this.src="res/defaultCourse.jpg"'>
            </div>
            <div class="course-info-center">
                <div>
                <span class="course-title">${courseList[i].title}</span>
                <span class="course-kind">${courseList[i].kindName}</span>
                </div>
                <div class="course-introduce">${courseList[i].introduction}</div>
            </div>
            <div class="course-info-right">
                <img class="course-head-img" src='${resImgUrl + courseList[i].userHeadUrl}' onerror='this.src="res/default.jpg"'>
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

function createPostList() {
    $('.post-list-body').children().remove();
    var htmlstr = "";
    var temp;
    for (var i in postList) {
        var time = new Date(postList[i].createdTime);
        var posttime = time.getFullYear() + "-" + filterNum(time.getMonth() + 1) + "-" + filterNum(time.getDate()) + " "
            + filterNum(time.getHours()) + ":" + filterNum(time.getMinutes());


        temp = `<div class='one-post' onclick='window.open(&#39;post.html?id=${postList[i].id}&#39;)'>
                  <div class='post-top-info'> 
                    <div class='post-top-info-left'>
                      <img class='post-head-img' src='${resImgUrl + postList[i].headimg}' onerror='this.src="res/default.jpg"'>
                    </div>
                    <div class='post-top-info-right'>
                      <div class='post-title'>${postList[i].title}</div>
                      <span class='post-content'>${postList[i].content}</span>
                    </div>
                 </div>
                 <div class='post-bottom-info'>
                   <div class='post-bottom-info-left'>
                     <span class='post-name'>${postList[i].nickName}</span>
                     <div class='post-time'>${posttime}</div>
                   </div>
                   <div class='post-bottom-info-right'>
                     <img src='res/star.png'>
                     <span class='starnum'>${postList[i].star}</span>
                     <img class='likeimg' src='res/like.png'>
                     <span class='likenum'>${postList[i].like}</span>
                   </div>
                 </div>
                </div>`;
        htmlstr += temp;
    }
    $('.post-list-body').append(htmlstr);
}


function getLikeCourseList() {
    $('.myLike-post-list').css('display', 'none');
    $('.myLike-course-list').css('display', 'block');

    var js = {pageIndex: 1};
    getLikeCourse(js, function (data) {
        if (data.code == 100) {
            courseList = data.data;
            if (courseList != null && courseList.length > 0) {
                new myPagination({
                    id: 'page1',
                    curPage: 1, //初始页码
                    pageTotal: Math.ceil(parseInt(data.msg) / 10), //总页数
                    dataTotal: parseInt(data.msg), //总共多少条数据
                    pageSize: 10, //可选,分页个数
                    showPageTotalFlag: true, //是否显示数据统计
                    showSkipInputFlag: true, //是否支持跳转
                    getPage: function (page) {
                        var json = {pageIndex: page};
                        getLikeCourse(json, function (data) {
                            courseList = data.data;
                            createCourseList();
                        });
                    }
                });
                createCourseList();
            } else {
                $('.course-list-body').empty();
                $('#page1').empty();
                $('.course-list-body').append(`<h1>暂无收藏</h1>`);
            }
        } else {
            toastr.error('获取失败');
        }
    });

}

function getLikePostList() {
    $('.myLike-post-list').css('display', 'block');
    $('.myLike-course-list').css('display', 'none');

    var js = {pageIndex: 1};
    getLikePost(js, function (data) {
        if (data.code == 100) {
            postList = data.data.list;
            if (postList != null && postList.length > 0) {
                new myPagination({
                    id: 'page2',
                    curPage: 1, //初始页码
                    pageTotal: Math.ceil(data.data.total / 10), //总页数
                    dataTotal: data.data.total, //总共多少条数据
                    pageSize: 10, //可选,分页个数
                    showPageTotalFlag: true, //是否显示数据统计
                    showSkipInputFlag: true, //是否支持跳转
                    getPage: function (page) {
                        var json = {pageIndex: page};
                        getLikePost(json, function (data) {
                            postList = data.data.list;
                            createPostList();
                        });
                    }
                });
                createPostList();
            } else {
                $('.post-list-body').empty();
                $('#page2').empty();
                $('.post-list-body').append(`<h1>暂无收藏</h1>`);
            }
        } else {
            toastr.error('获取失败');
        }
    });


}


function init() {
    $('#2-0').on('click', function () {
        getLikeCourseList();
    });
    $('#2-1').on('click', function () {
        getLikePostList();
    });


    var js = {pageIndex: 1};
    getLikeCourse(js, function (data) {
        if (data.code == 100) {
            courseList = data.data;
            if (courseList != null && courseList.length > 0) {

                new myPagination({
                    id: 'page1',
                    curPage: 1, //初始页码
                    pageTotal: Math.ceil(parseInt(data.msg) / 10), //总页数
                    dataTotal: parseInt(data.msg), //总共多少条数据
                    pageSize: 10, //可选,分页个数
                    showPageTotalFlag: true, //是否显示数据统计
                    showSkipInputFlag: true, //是否支持跳转
                    getPage: function (page) {
                        var json = {pageIndex: page};
                        getLikeCourse(json, function (data) {
                            courseList = data.data;
                            createCourseList();
                        });
                    }
                });


                createCourseList();
            } else {
                $('.course-list-body').append(`<h1>暂无收藏</h1>`);
            }
        } else {
            toastr.error('获取失败');
        }
    });

    getLikePost(js, function (data) {
        if (data.code == 100) {
            postList = data.data.list;
            if (postList != null && postList.length > 0) {

                new myPagination({
                    id: 'page2',
                    curPage: 1, //初始页码
                    pageTotal: Math.ceil(data.data.total / 10), //总页数
                    dataTotal: data.data.total, //总共多少条数据
                    pageSize: 10, //可选,分页个数
                    showPageTotalFlag: true, //是否显示数据统计
                    showSkipInputFlag: true, //是否支持跳转
                    getPage: function (page) {
                        var json = {pageIndex: page};
                        getLikePost(json, function (data) {
                            postList = data.data.list;
                            createPostList();
                        });
                    }
                });

                createPostList();
            } else {
                $('.post-list-body').append(`<h1>暂无收藏</h1>`);
            }
        } else {
            toastr.error('获取失败');
        }
    });


    document.getElementsByTagName("body")[0].style.zoom = 1;
    $('.top-head').css("min-width", $('.top-head').width() + 'px');
    $('.top-head').css("width", $('.top-head').width() + 'px');

    var userName = sessionStorage["userName"];
    var headUrl = sessionStorage["headUrl"];
    if (userName == null || userName == "null" || headUrl == null || headUrl == "null") {
        $('#user-head').attr('src', 'res/default.jpg');
        $('#user-login').append(`<p class="user"id="unlogin" onclick="window.location.href='login.html'">点击登录</p>`);
    } else {
        $('#user-head').attr('src', `${resImgUrl + headUrl}`);
        $('#user-head').attr('onerror', 'this.src="res/default.jpg"');
        $('#user-login').append(`<p class="user">${userName}</p>`);

        var usermenu = $('#user-menu');
        $('#user').hover(function () {
            usermenu.slideDown();
        }, function () {
            usermenu.hide();
        });
        usermenu.hover(function () {
            usermenu.show();
        }, function () {
            usermenu.slideUp();
        });
    }


}

init();