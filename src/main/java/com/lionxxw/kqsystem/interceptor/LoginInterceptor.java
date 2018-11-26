package com.lionxxw.kqsystem.interceptor;

import com.lionxxw.kqsystem.code.constants.DataStatus;
import com.lionxxw.kqsystem.code.utils.ObjectUtils;
import com.lionxxw.kqsystem.code.web.SessionProvider;
import com.lionxxw.kqsystem.mode.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * <p>Description: 前台登录拦截器 </p>
 * 判断 用户是否登陆
 * 全站上下文
 * 全局信息
 * The type Login interceptor.
 * Created by lionxxw on 2016-07-07 16:04:43
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private SessionProvider sessionProvider;
    //拦截 包括/kqs  的请求路径
    public static final String INTERCETOR_URL = "/kqs/";
    //返回路径
    public static final String RETURNURL = "returnUrl";

    // 方法前
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        LoginUser user = (LoginUser) sessionProvider.getAttribute(request, DataStatus.SESSION_USER);
        if(ObjectUtils.notNull(user)){
            request.setAttribute("kqs_login_user", user);
        }
        String requestURI = request.getRequestURI();

        if(requestURI.startsWith(INTERCETOR_URL)){
            if(ObjectUtils.isNull(user)){
                response.sendRedirect("/login.do?" + RETURNURL + "=" + request.getRequestURL());
                return false;
            }
        }

        return true;
    }

    // 方法后
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    // 页面渲染后
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
