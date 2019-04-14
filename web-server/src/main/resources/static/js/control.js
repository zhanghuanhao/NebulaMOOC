var ifsign = false;//根据此布尔值判断当前为注册状态还是登录状态
var ifgetmail = false;//是否发送邮件
var confirm = document.getElementsByClassName("confirm")[0];
var sendcode = document.getElementById("send_code");
var canvas = document.getElementById("img");
var status_title = document.getElementById("flag");
var forget_title = document.getElementById("forget");
var log_bt = document.getElementById("log_bt");
var sign_bt = document.getElementById("sign_bt");
var submit_bt = document.getElementById("submit_bt");
var cannel_bt = document.getElementById("cannel_bt");

//邮件地址正则
var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;

function refresh() {
    document.getElementById("img").src = "/sys/code/getImgCode?" + Math.random();
}

$(document).keypress(function (e) {
    // 回车键事件
    if (e.which == 13) {
        $('input[id="sign_bt"]').click();
    }
});
//粒子背景特效
$('body').particleground({
    dotColor: '#E8DFE8',
    lineColor: '#1b3273'
});
$('input[name="pwd"]').focus(function () {
    $(this).attr('type', 'password');
});
$('input[type="text"]').focus(function () {
    $(this).prev().animate({'opacity': '1'}, 200);
});
$('input[type="text"],input[type="password"]').blur(function () {
    $(this).prev().animate({'opacity': '.5'}, 200);
});
$('input[name="login"],input[name="pwd"]').keyup(function () {
    var Len = $(this).val().length;
    if (!$(this).val() == '' && Len >= 5) {
        $(this).next().animate({
            'opacity': '1',
            'right': '30'
        }, 200);
    } else {
        $(this).next().animate({
            'opacity': '0',
            'right': '20'
        }, 200);
    }
});
var open = 0;
layui.use('layer', function () {
    //登录按钮
    $('input[id="log_bt"]').click(function () {

        var login = $('.username').val();
        var password = $('.passwordNumder').val();
        var code = $('.ValidateNum').val();
        if (login == '') {
            ErroAlert('请输入您的账号');
            return false;
        } else if (login.search(reg) == -1) {
            ErroAlert('邮箱地址不正确');
            return false;
        } else if (password == '') {
            ErroAlert('请输入密码');
            return false;
        } else if (code == '' || code.length != 5) {
            ErroAlert('输入验证码');
            return false;
        } else {
            //认证中..
            $('.login').addClass('test'); //倾斜特效
            setTimeout(function () {
                $('.login').addClass('testtwo'); //平移特效
            }, 300);
            setTimeout(function () {
                $('.authent').show().animate({right: -320}, {
                    easing: 'easeOutQuint',
                    duration: 600,
                    queue: false
                });
                $('.authent').animate({opacity: 1}, {
                    duration: 200,
                    queue: false
                }).addClass('visible');
            }, 500);

            //登陆
            var JsonData = {username: login, password: password, imgCode: code};

            Login(JsonData, function (data) {
                //ajax返回
                //认证完成
                setTimeout(function () {
                    $('.authent').show().animate({right: 90}, {
                        easing: 'easeOutQuint',
                        duration: 600,
                        queue: false
                    });
                    $('.authent').animate({opacity: 0}, {
                        duration: 200,
                        queue: false
                    }).addClass('visible');
                    $('.login').removeClass('testtwo'); //平移特效
                }, 2000);
                setTimeout(function () {
                    $('.authent').hide();
                    $('.login').removeClass('test');
                    console.log(data.code + data.msg);
                    if (data.code == 100) {
                        //登录成功
                        $('.login div').fadeOut(100);
                        $('.success').fadeIn(1000);
                        $('.success').html(data.code);


                        // //跳转操作

                    } else {
                        AjaxErro(data.code);
                    }
                }, 2400);
            });
        }
    });

    //注册按钮
    $('input[id="sign_bt"]').click(function () {

        $('.username').val("");
        $('.passwordNumder').val("");
        $('.ValidateNum').val("");
        log_bt.style.visibility = "hidden";
        sign_bt.style.visibility = "hidden";
        submit_bt.style.visibility = "visible";
        cannel_bt.style.visibility = "visible";
        canvas.style.display = "none";
        sendcode.style.display = "block";
        confirm.style.display = "block";
        status_title.innerText = "注册";
        ifsign = true;
    });

    //确认按钮
    $('input[id="submit_bt"]').click(
        function () {
            var login = $('.username').val();
            var password = $('.passwordNumder').val();
            var cpwd = $('.confirmpasswordNumder').val();
            var code = $('.ValidateNum').val();

            if (login == '') {
                ErroAlert('请输入您的账号');
                return false;
            } else if (login.search(reg) == -1) {
                ErroAlert('邮箱地址不正确');
                return false;
            } else if (password == '' || cpwd == '') {
                ErroAlert('请输入密码');
                return false;
            } else if (password != cpwd) {
                ErroAlert('密码不一致');
                return false;
            } else if (code == '' || code.length != 5) {
                ErroAlert('输入验证码');
                return false;
            } else if (!ifgetmail) {
                ErroAlert('请获取邮件验证码');
                return false;
            } else {
                //认证中..
                $('.login').addClass('test'); //倾斜特效
                setTimeout(function () {
                    $('.login').addClass('testtwo'); //平移特效
                }, 300);
                setTimeout(function () {
                    $('.authent').show().animate({right: -320}, {
                        easing: 'easeOutQuint',
                        duration: 600,
                        queue: false
                    });
                    $('.authent').animate({opacity: 1}, {
                        duration: 200,
                        queue: false
                    }).addClass('visible');
                }, 500);

                //封装json
                var JsonData = {login: login, password: password, mailcode: code};


                if (ifsign) {
                    register(JsonData, function (data) {
                        //ajax返回
                        //认证完成
                        setTimeout(function () {
                            $('.authent').show().animate({right: 90}, {
                                easing: 'easeOutQuint',
                                duration: 600,
                                queue: false
                            });
                            $('.authent').animate({opacity: 0}, {
                                duration: 200,
                                queue: false
                            }).addClass('visible');
                            $('.login').removeClass('testtwo'); //平移特效
                        }, 2000);
                        setTimeout(function () {
                            $('.authent').hide();
                            $('.login').removeClass('test');
                            if (data.code == 100) {
                                //注册成功
                                $('.login div').fadeOut(100);
                                $('.success').fadeIn(1000);
                                $('.success').html("注册成功，请登录！");

                                setTimeout(window.location.reload(), 2000);
                            } else {
                                AjaxErro(data.code);
                            }
                        }, 2400);
                    })
                } else {
                    findPwd(JsonData, function (data) {
                        //ajax返回
                        //认证完成
                        setTimeout(function () {
                            $('.authent').show().animate({right: 90}, {
                                easing: 'easeOutQuint',
                                duration: 600,
                                queue: false
                            });
                            $('.authent').animate({opacity: 0}, {
                                duration: 200,
                                queue: false
                            }).addClass('visible');
                            $('.login').removeClass('testtwo'); //平移特效
                        }, 2000);
                        setTimeout(function () {
                            $('.authent').hide();
                            $('.login').removeClass('test');
                            if (data.code == 100) {
                                //注册成功
                                $('.login div').fadeOut(100);
                                $('.success').fadeIn(1000);
                                $('.success').html("重置密码成功，请重新登录！");

                                setTimeout(window.location.reload(), 2000);
                            } else {
                                AjaxErro(data.code);
                            }
                        }, 2400);
                    })
                }
            }
        }
    );

    //获取邮件验证码按钮
    $('input[id="send_code"]').click(
        function () {
            var login = $('.username').val();
            if (login == '') {
                ErroAlert('请输入您的账号');
                return false;
            } else if (login.search(reg) == -1) {
                ErroAlert('邮箱地址不正确');
                return false;
            } else {
                getemail(
                    function (data) {
                        ifgetmail = true;
                        msgAlert('验证码已发送');
                        cooltime(sendcode);
                    }
                );
            }
        }
    );

    //取消按钮
    $('input[id="cannel_bt"]').click(
        function () {
            status_title.innerText = "登录";
            confirm.style.display = "none";
            canvas.style.display = "block";
            sendcode.style.display = "none";
            $('.username').val("");
            $('.passwordNumder').val("");
            $('.ValidateNum').val("");
            forget_title.style.visibility = "visible";
            log_bt.style.visibility = "visible";
            sign_bt.style.visibility = "visible";
            submit_bt.style.visibility = "hidden";
            cannel_bt.style.visibility = "hidden";
            ifsign = false;
        }
    );


});

function findback() {
    status_title.innerText = "找回密码";
    confirm.style.display = "block";
    canvas.style.display = "none";
    sendcode.style.display = "block";
    $('.username').val("");
    $('.passwordNumder').val("");
    $('.confirmpasswordNumder').val("");
    $('.ValidateNum').val("");
    forget_title.style.visibility = "hidden";
    log_bt.style.visibility = "hidden";
    sign_bt.style.visibility = "hidden";
    submit_bt.style.visibility = "visible";
    cannel_bt.style.visibility = "visible";
    ifsign = false;
}




