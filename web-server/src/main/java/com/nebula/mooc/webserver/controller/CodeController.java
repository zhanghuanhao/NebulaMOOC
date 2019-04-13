package com.nebula.mooc.webserver.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.nebula.mooc.core.util.MailUtil;
import com.nebula.mooc.webserver.service.CodeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

/*  请求发送邮件验证码 */
@Controller
@RequestMapping("/sysCode/")
public class CodeController {

    @Resource
    private CodeService codeService;

    @GetMapping("getMailCode")
    public void getMailCode(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String code = "";
        char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        for (int i = 0; i < 5; i++) {
            code += codeSequence[(int) (Math.random() * 35)];
        }
        request.setAttribute("EMAIL_CHECK_CODE", code);
        String sender = "nebulamooc@163.com";
        String receiver = request.getParameter("address");
        String title = "欢迎注册NebulaMooc!";
        String content = "验证码测试：验证码为：" + code + "，请在网页上输入验证码。";
        MailUtil.send(sender, receiver, title, content);
    }


    @GetMapping("/getImgCode")
    public void codeTest(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        response.setContentType("image/png");//以图片形式打出
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(90, 36);//设置长和宽
        lineCaptcha.createCode();//创建验证码，同时生产随机验证码字符串和验证码图片
        String code = lineCaptcha.getCode();//获取到验证码
        System.out.println("验证码为：" + code);
        session.setAttribute("IMG_CHECK_CODE", code);
        OutputStream os = response.getOutputStream();
        lineCaptcha.write(os);//输出
    }

}

