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


function new_a_post() {
    $("body").append("<div id='dialog'></div>");
    $("body").append(
        `<div id='write'>
             <div id='write-title'><span>标题:</span><input id='input-title' placeholder='输入标题'>
                 <span>类型:</span>
                 <select id='input-kind'>
                    <option value='1'>Java</option>
                    <option value='2'>C</option>
                    <option value='3'>C++</option>
                    <option value='4'>PHP</option>
                    <option value='5'>C#</option>
                    <option value='6'>Python</option>
                    <option value='7'>SQL</option>
                    <option value='8'>VB</option>
                    <option value='9'>GO</option>
                    <option value='10'>Shell</option>
                 </select>
             </div>
             <div id='write-content'>
                 <textarea id='input-content' placeholder='输入正文'></textarea>
             </div> 
             <div id='write-button'>
                 <input type='button' class='btn btn-primary' onclick=$('#dialog').remove();$('#write').remove(); value='取消'>
                 <input type='button' class='btn btn-primary' onclick=sendPost(); value='发布'>
             </div>
        </div>`);

}

$('#new-post').on('click', function () {
    new_a_post();
});

function sendPost() {
    var kind = $('#input-kind option:selected').val();
    var title = $('#input-title').val();
    var content = $('#input-content').val();
    if (kind == null) {
        toastr.warning('请选择种类');
    } else if (title == null) {
        toastr.warning('请输入标题');
    } else if (content == null) {
        toastr.warning('请输入内容');
    } else {
        newPost({kind: kind, title: title, content: content}, function (data) {
            if (data.code == 100) {
                toastr.success('发布成功');
                setTimeout(function () {
                    getPostList();
                }, 1000);
            } else {
                toastr.error('发布失败');
            }
        });
        $('#dialog').remove();
        $('#write').remove();
    }
}


var postList;
var kind = 0;

function getPostList() {

    var js = {pageIndex: 1, kind: kind};
    showPostList(js, function (data) {
        if (data.code == 100) {
            postList = data.data.list;
            if (postList != null && postList.length > 0) {

                new myPagination({
                    id: 'page',
                    curPage: 1, //初始页码
                    pageTotal: Math.ceil(data.data.total / 10), //总页数
                    dataTotal: data.data.total, //总共多少条数据
                    pageSize: 10, //可选,分页个数
                    showPageTotalFlag: true, //是否显示数据统计
                    showSkipInputFlag: true, //是否支持跳转
                    getPage: function (page) {
                        //获取当前页数
                        var json = {pageIndex: page, kind: kind};
                        showPostList(json, function (data) {
                            postList = data.data.list;
                            createPostList();
                        });
                    }
                });


                createPostList();
            } else {
                $('.post-list-body').empty();
                $('#page').empty();
            }
        } else {
            toastr.error('获取失败');
        }
    });
}


function getHotPostList() {
    showHotPostList(function (data) {
        if (data.code == 100) {
            postList = data.data;
            if (postList != null && postList.length > 0) {
                createPostList();
            } else {
                $('.post-list-body').empty();
            }
            $('#page').empty();
        } else {
            toastr.error('获取失败');
        }
    });
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
                      <img class='post-head-img' src='https://nebula-head.oss-cn-shenzhen.aliyuncs.com/${postList[i].headimg}/head100'>
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

$('#2-0').on('click', function () {
    getPostList();
});
$('#2-1').on('click', function () {
    getHotPostList();
});


function init() {
    var li1 = document.getElementsByName('li1');
    li1[0].onclick = function () {
        kind = parseInt(this.id);
        changecolor(this.id);
        $('.menu-pipe').css('display', 'block');
        $('#2-1').css('display', 'block');
        getPostList();
    };
    for (var i = 1; i < li1.length; i++) {
        li1[i].onclick = function () {
            kind = parseInt(this.id);
            changecolor(this.id);
            $('.menu-pipe').css('display', 'none');
            $('#2-1').css('display', 'none');
            $('#2-0').click();
        };
    }
    var js = {pageIndex: 1, kind: kind};
    showPostList(js, function (data) {
        if (data.code == 100) {
            postList = data.data.list;
            if (postList != null && postList.length > 0) {

                new myPagination({
                    id: 'page',
                    curPage: 1, //初始页码
                    pageTotal: Math.ceil(data.data.total / 10), //总页数
                    dataTotal: data.data.total, //总共多少条数据
                    pageSize: 10, //可选,分页个数
                    showPageTotalFlag: true, //是否显示数据统计
                    showSkipInputFlag: true, //是否支持跳转
                    getPage: function (page) {
                        //获取当前页数
                        var json = {pageIndex: page, kind: kind};
                        showPostList(json, function (data) {
                            postList = data.data.list;
                            createPostList();
                        });
                    }
                });


                createPostList();
            }
        } else {
            toastr.error('获取失败');
        }
    });


    document.getElementsByTagName("body")[0].style.zoom = 1;
    $('.top-head').css("min-width", $('.top-head').width() + 'px');
    $('.top-head').css("width", $('.top-head').width() + 'px');
    $('.tab-wrap1').css("width", $('.top-head').width() + 'px');
    $('.tab-wrap1').css("min-width", $('.top-head').width() + 'px');
    $('.tab-wrap2').css("width", $('.top-head').width() + 'px');
    $('.tab-wrap2').css("min-width", $('.top-head').width() + 'px');

    var userName = localStorage["userName"];
    var headUrl = localStorage["headUrl"];
    if (userName == null || userName == "null" || headUrl == null || headUrl == "null") {
        $('#user-head').attr('src', 'res/default.jpg');
        $('#user-login').append(`<p class="user"id="unlogin" onclick="window.location.href='login.html'">点击登录</p>`);
    } else {
        $('#user-head').attr('src', `https://nebula-head.oss-cn-shenzhen.aliyuncs.com/${headUrl}/head100`);
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

