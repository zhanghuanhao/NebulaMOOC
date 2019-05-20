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
    $("body").append("<div id='write'>" + "<div id='write-title'><span>标题:</span><input id='input-title' placeholder='输入标题'>" +
        "<span>类型:</span>" +
        "<select id='input-kind'>" +
        "<option value='Java'>Java</option>" +
        "<option value='C/C++'>C/C++</option>" +
        "<option value='C#'>C#</option>" +
        "<option value='PHP'>PHP</option>" +
        "<option value='HTML/CSS/JS'>HTML/CSS/JS</option>" +
        "<option value='Python'>Python</option>" +
        "<option value='SQL'>SQL</option>" +
        "<option value='VB'>VB</option>" +
        "<option value='Pascal'>Pascal</option>" +
        "<option value='Other'>其他</option>" +
        "</select></div>"
        + "<div id='write-content'><textarea id='input-content' placeholder='输入正文'></textarea></div> "
        + "<div id='write-button'><input type='button' class='btn btn-primary' onclick=$('#dialog').remove();$('#write').remove(); value='取消'>" +
        "<input type='button' class='btn btn-primary' onclick=sendPost(); value='发布'></div></div>");

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
        newPost({kindName: kind, title: title, content: content}, function (data) {
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
var kind = null;
var new_or_hot = true;

function getPostList() {

    var js = {currentPage: 1, kindName: kind};
    showPostList(js, function (data) {
        if (data.code == 100) {
            postList = data.data.list;
            if (postList != null && postList.length > 0) {
                $(".pagediv").updatePage({
                    pageNum: Math.ceil(data.data.total / 10),
                    current: 1
                });
                createPostList();
            } else {
                $('.post-list-body').empty();
                $('.pagediv').empty();
            }
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
    new_or_hot = true;
    getPostList();
});
$('#2-1').on('click', function () {
    new_or_hot = false;
    getPostList();
});


function init() {
    var li1 = document.getElementsByName('li1');
    li1[0].onclick = function () {
        kind = null;
        changecolor(this.id);
        getPostList();
    };
    for (var i = 1; i < li1.length; i++) {
        li1[i].onclick = function () {
            kind = this.innerText;
            changecolor(this.id);
            getPostList();
        };
    }
    var js = {currentPage: 1, kindName: kind};
    showPostList(js, function (data) {
        if (data.code == 100) {
            postList = data.data.list;
            if (postList != null) {
                $(".pagediv").createPage({
                    pageNum: Math.ceil(data.data.total / 10),
                    current: 1,
                    backfun: function (e) {
                        console.log('aaa');
                        var json = {currentPage: e.current, kindName: kind};
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

}

init();

