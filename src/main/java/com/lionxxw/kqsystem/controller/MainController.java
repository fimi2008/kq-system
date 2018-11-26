package com.lionxxw.kqsystem.controller;

import com.lionxxw.kqsystem.code.constants.DataStatus;
import com.lionxxw.kqsystem.code.model.Response;
import com.lionxxw.kqsystem.code.utils.BeanUtils;
import com.lionxxw.kqsystem.code.utils.ResponseUtils;
import com.lionxxw.kqsystem.dto.UserDto;
import com.lionxxw.kqsystem.mode.LoginUser;
import com.lionxxw.kqsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 主面板控制层
 *
 * Created by lionxxw on 2016/7/8.
 */
@Controller
public class MainController extends KqsController {

    @Autowired
    private UserService userService;

    /**
     * 主页
     * @return
     */
    @RequestMapping(value = "main")
    public String index(){
        return "kqs/main";
    }

    /**
     * 获取当前登录人信息
     * @param request
     * @param response
     */
    @RequestMapping(value = "getUser")
    public void getUser(HttpServletRequest request, HttpServletResponse response){
        LoginUser loginUser = getLoginUser(request);
        ResponseUtils.renderJson(response, loginUser);
    }

    /**
     * 更新当前用户信息
     * @param request
     * @param response
     * @param user
     */
    @RequestMapping(value = "updateUser")
    public void updateUser(HttpServletRequest request, HttpServletResponse response, UserDto user){
        Response<String> res = new Response<String>();
        try {
            userService.update(user);
            LoginUser loginUser = BeanUtils.createBeanByTarget(user, LoginUser.class);
            LoginUser loginUser2 = getLoginUser(request);
            loginUser.setLastLoginTime(loginUser2.getLastLoginTime());
            loginUser.setLastLoginIp(loginUser2.getLastLoginIp());
            sessionProvider.setAttribute(request, DataStatus.SESSION_USER, loginUser);
            res.setMessage("个人信息修改成功!");
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(e.getMessage());
            res.setStatus(DataStatus.HTTP_FAILE);
        }
        ResponseUtils.renderJson(response, res);
    }
}