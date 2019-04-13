package com.nebula.mooc.webserver.service.impl;

import com.nebula.mooc.webserver.service.CodeService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by 15722 on 2019/4/13.
 */

@Service("CodeService")
public class CodeServiceImpl implements CodeService {

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


}
