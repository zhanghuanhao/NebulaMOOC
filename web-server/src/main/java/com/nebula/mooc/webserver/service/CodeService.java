package com.nebula.mooc.webserver.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by 15722 on 2019/4/13.
 */
public interface CodeService {

    boolean verifyImgCode(String imgCode, HttpSession session);

    boolean verifyMailCode(String mailCode, HttpSession session);

    boolean sendMailCode(HttpServletRequest request, HttpSession session);

    boolean sendImgCode(HttpServletResponse response, HttpSession session);

    void clearImgCode(HttpSession session);

    void clearMailCode(HttpSession session);


}
