package com.nebula.mooc.webserver.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.nebula.mooc.core.util.MailUtil;
import com.nebula.mooc.webserver.service.CodeService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by 15722 on 2019/4/13.
 */

@Service("CodeService")
public class CodeServiceImpl implements CodeService {

    private static char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};


    @Override
    public boolean verifyImgCode(String imgCode, HttpSession session) {
        //提取服务器验证码
        String code = (String) session.getAttribute("IMG_CHECK_CODE");
        if (imgCode == null || code == null) return false;
        return imgCode.equals(code);
    }

    @Override
    public boolean verifyMailCode(String mailCode, HttpSession session) {
        //提取服务器验证码
        String code = (String) session.getAttribute("EMAIL_CHECK_CODE");
        if (mailCode == null || code == null) return false;
        return mailCode.equals(code);
    }

    @Override
    public void sendMailCode(HttpServletRequest request, HttpSession session) {
        StringBuilder code = new StringBuilder(5);
        for (int i = 0; i < 5; i++) {
            code.append(codeSequence[(int) (Math.random() * 35)]);
        }
        session.setAttribute("EMAIL_CHECK_CODE", code);
        String receiver = request.getParameter("address");
        if (receiver == null) return;
        String title = "欢迎注册NebulaMooc!";
        String content = "验证码测试：验证码为：" + code + "，请在网页上输入验证码。";
        MailUtil.send(receiver, title, content);
    }


    @Override
    public void sendImgCode(HttpServletResponse response, HttpSession session) throws IOException {
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
