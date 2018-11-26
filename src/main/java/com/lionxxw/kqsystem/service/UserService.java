package com.lionxxw.kqsystem.service;

import com.lionxxw.kqsystem.code.base.BaseService;
import com.lionxxw.kqsystem.dto.UserDto;


/**
 * The interface User service.
 * Created by lionxxw on 2016-07-07 15:57:04
 */
public interface UserService extends BaseService<UserDto> {

    /**
     * Gets user by account.
     *
     * @param account the account
     * @return the user by account
     * @throws Exception the exception
     * @author lionxxw
     * @date 2016 -07-07 16:34:39
     */
    UserDto getUserByAccount(String account) throws Exception;

    /**
     * Gets user by mobile.
     *
     * @param mobile the mobile
     * @return the user by mobile
     * @throws Exception the exception
     * @author lionxxw
     * @date 2016 -07-27 10:43:39
     */
    UserDto getUserByMobile(String mobile) throws Exception;

    /**
     * Gets user by email.
     *
     * @param email the email
     * @return the user by email
     * @throws Exception the exception
     * @author lionxxw
     * @date 2016 -07-27 10:43:39
     */
    UserDto getUserByEmail(String email) throws Exception;
}