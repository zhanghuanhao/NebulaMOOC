package com.nebula.mooc.webserver.service.impl;

import com.nebula.mooc.webserver.service.CodeService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by 15722 on 2019/4/13.
 */

@Service("CodeService")
public class CodeServiceImpl implements CodeService {

    @Override
    public boolean verifyImgCode(String imgCode, HttpSession session) throws IOException {
        //提取服务器验证码
        String truecode = session.getAttribute("IMG_CHECK_CODE").toString();
        if (imgCode == null || truecode == null) return false;
        return imgCode.equals(truecode);
    }

    @Override
    public boolean verifyMailCode(String mailCode, HttpSession session) throws IOException {
        //提取服务器验证码
        String truecode = (String) session.getAttribute("EMAIL_CHECK_CODE");
        if (mailCode == null || truecode == null) return false;
        return mailCode.equals(truecode);
    }


}
