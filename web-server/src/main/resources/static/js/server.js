var cooldown = 60;//获取邮件验证码倒计时

//Ajax提交
function AjaxPost(Url, JsonData, LodingFun, ReturnFun) {
    $.ajax({
        type: "post",
        url: Url,
        data: JsonData,
        dataType: 'json',
        async: 'false',
        beforeSend: LodingFun,
        error: function () {
            AjaxErro({"Status": "Erro", "Erro": "500"});
        },
        success: ReturnFun
    });
}

//弹出提示框
function ErroAlert(e) {
    layer.msg(e, {
        icon: 5,
        time: 2000,
        skin: 'my-skin'
    });
}

//弹出确认框
function msgAlert(e) {
    layer.msg(e, {
        icon: 1,
        time: 2000,
        skin: 'my-skin'
    });
}

//Ajax 错误返回处理
function AjaxErro(e) {
    if (e.Status == "Erro") {
        switch (e.Erro) {
            case "500":
                top.location.href = '/Erro/Erro500';
                break;
            case "100001":
                ErroAlert("错误 : 错误代码 '10001'");
                break;
            default:
                ErroAlert(e.Erro);
        }
    } else {
        layer.msg("未知错误！");
    }
}


/* 发送用户识别的图片验证码*/
function Login(JSONdata, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/sys/user/login",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: JSONdata,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            ErroAlert('获取图片验证码失败');
        }
    });
}

/*请求邮件验证码*/
function getemail(SussessFun) {

    var JSONdata = {address: $('.username').val()};
    $.ajax({
        type: "POST",
        url: "/sys/code/getMailCode",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: JSONdata,
        dataType: 'json',
        success: SussessFun,
        error: function (e) {
            ErroAlert('发送失败');
            alert(e);
        }
    });
}

/*60秒倒计时*/
function cooltime(o) {
    if (cooldown == 0) {
        o.removeAttribute("disabled");
        o.value = "获取验证码";
    } else {
        o.setAttribute("disabled", true);
        o.value = "重新发送(" + cooldown + ")";
        cooldown--;
        setTimeout(function () {
            cooltime(o);
        }, 1000);
    }
}

/*发送用户收到的邮箱验证码*/
function register(JSONdata, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: JSONdata,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            ErroAlert('请获取验证码');
        }
    });
}

/*找回密码*/
function findPwd(JSONdata, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: JSONdata,
        dataType: 'json',
        success: ReturnFun,
        error: function () {
            ErroAlert('请获取验证码');
        }
    });

}

