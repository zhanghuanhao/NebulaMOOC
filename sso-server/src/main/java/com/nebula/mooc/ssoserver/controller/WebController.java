package com.nebula.mooc.ssoserver.controller;

import com.nebula.mooc.core.entity.Constant;
import com.nebula.mooc.core.entity.Return;
import com.nebula.mooc.core.entity.User;
import com.nebula.mooc.core.login.SsoWebLoginHelper;
import com.nebula.mooc.core.store.SsoLoginStore;
import com.nebula.mooc.core.store.SsoSessionIdHelper;
import com.nebula.mooc.ssoserver.core.model.UserInfo;
import com.nebula.mooc.ssoserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * sso server (for web)
 *
 * @author xuxueli 2017-08-01 21:39:47
 */
@Controller
public class WebController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) {

        // login check
        User xxlUser = SsoWebLoginHelper.loginCheck(request, response);

        if (xxlUser == null) {
            return "redirect:/login";
        } else {
            model.addAttribute("xxlUser", xxlUser);
            return "index";
        }
    }

    /**
     * Login page
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(Constant.SSO_LOGIN)
    public String login(Model model, HttpServletRequest request, HttpServletResponse response) {

        // login check
        User xxlUser = SsoWebLoginHelper.loginCheck(request, response);

        if (xxlUser != null) {

            // success redirect
            String redirectUrl = request.getParameter(Constant.REDIRECT_URL);
            if (redirectUrl != null && redirectUrl.trim().length() > 0) {

                String sessionId = SsoWebLoginHelper.getSessionIdByCookie(request);
                String redirectUrlFinal = redirectUrl + "?" + Constant.SESSION_ID + "=" + sessionId;

                return "redirect:" + redirectUrlFinal;
            } else {
                return "redirect:/";
            }
        }

        model.addAttribute("errorMsg", request.getParameter("errorMsg"));
        model.addAttribute(Constant.REDIRECT_URL, request.getParameter(Constant.REDIRECT_URL));
        return "login";
    }

    /**
     * Login
     *
     * @param request
     * @param redirectAttributes
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/doLogin")
    public String doLogin(HttpServletRequest request,
                          HttpServletResponse response,
                          RedirectAttributes redirectAttributes,
                          String username,
                          String password,
                          String ifRemember) {

        boolean ifRem = ifRemember != null && "on".equals(ifRemember);

        // valid login
        Return<UserInfo> result = userService.findUser(username, password);
        if (result.getCode() != Return.SUCCESS_CODE) {
            redirectAttributes.addAttribute("errorMsg", result.getMsg());

            redirectAttributes.addAttribute(Constant.REDIRECT_URL, request.getParameter(Constant.REDIRECT_URL));
            return "redirect:/login";
        }

        // 1、make xxl-sso user
        User xxlUser = new User();
        xxlUser.setUserid(String.valueOf(result.getData().getUserid()));
        xxlUser.setUsername(result.getData().getUsername());
        xxlUser.setVersion(UUID.randomUUID().toString().replaceAll("-", ""));
        xxlUser.setExpireMinite(SsoLoginStore.getRedisExpireMinite());
        xxlUser.setExpireFreshTime(System.currentTimeMillis());


        // 2、make session id
        String sessionId = SsoSessionIdHelper.makeSessionId(xxlUser);

        // 3、login, store storeKey + cookie sessionId
        SsoWebLoginHelper.login(response, sessionId, xxlUser, ifRem);

        // 4、return, redirect sessionId
        String redirectUrl = request.getParameter(Constant.REDIRECT_URL);
        if (redirectUrl != null && redirectUrl.trim().length() > 0) {
            String redirectUrlFinal = redirectUrl + "?" + Constant.SESSION_ID + "=" + sessionId;
            return "redirect:" + redirectUrlFinal;
        } else {
            return "redirect:/";
        }

    }

    /**
     * Logout
     *
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(Constant.SSO_LOGOUT)
    public String logout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {

        // logout
        SsoWebLoginHelper.logout(request, response);

        redirectAttributes.addAttribute(Constant.REDIRECT_URL, request.getParameter(Constant.REDIRECT_URL));
        return "redirect:/login";
    }


}