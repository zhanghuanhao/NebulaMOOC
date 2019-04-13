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

//弹出
function ErroAlert(e) {
    var index = layer.alert(e, {
        icon: 5,
        time: 2000,
        offset: 't',
        closeBtn: 0,
        title: '错误信息',
        btn: [],
        anim: 2,
        shade: 0
    });
    layer.msg(index, {
        color: '#777'
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
        url: "/sysUser/login",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: JSONdata,
        dataType: 'json',
        success: ReturnFun,
        error: function (jqXHR, textStatus, errorThrown) {
            alert(errorThrown);
        }

    });
}

/*请求邮件验证码*/
function getemail() {
    $.ajax({
        type: "POST",
        url: "/Email_Get_Code",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: {"address": $('.username').val()},
        dataType: "text",
        success: function (data) {
            alert("已发送");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(errorThrown);
        }
    });
}

/*发送用户收到的邮箱验证码*/
function register(JSONdata, ReturnFun) {
    $.ajax({
        type: "POST",
        url: "/Email_Check_Code",
        contentType: 'application/x-www-form-urlencoded;charset=utf-8',
        data: JSONdata,
        dataType: 'json',
        success: ReturnFun,
        error: function (jqXHR, textStatus, errorThrown) {
            alert("请获取验证码")
        }
    });
}

