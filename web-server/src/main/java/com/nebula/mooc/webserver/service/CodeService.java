package com.nebula.mooc.webserver.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by 15722 on 2019/4/13.
 */
public interface CodeService {

    boolean verifyImgCode(String imgCode, HttpSession session) throws IOException;

    boolean verifyMailCode(String mailCode, HttpSession session) throws IOException;

    void sendMailCode(HttpServletRequest request, HttpSession session);

    void sendImgCode(HttpServletResponse response, HttpSession session) throws IOException;

}
