package com.lionxxw.kqsystem.service.impl;

import com.lionxxw.kqsystem.code.model.PageQuery;
import com.lionxxw.kqsystem.code.model.PageResult;
import com.lionxxw.kqsystem.code.utils.BeanUtils;
import com.lionxxw.kqsystem.code.utils.ExceptionUtils;
import com.lionxxw.kqsystem.code.utils.ObjectUtils;
import com.lionxxw.kqsystem.code.utils.StringUtils;
import com.lionxxw.kqsystem.dao.UserDao;
import com.lionxxw.kqsystem.db.DataSource;
import com.lionxxw.kqsystem.dto.UserDto;
import com.lionxxw.kqsystem.entity.User;
import com.lionxxw.kqsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public UserDto save(UserDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        obj.setCreateTime(new Date());
        User user = BeanUtils.createBeanByTarget(obj, User.class);
        userDao.insertSelective(user);
        obj.setId(user.getId());
        return obj;
    }

    public boolean delById(Long id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, User.class, "delById");
        int i = userDao.deleteByPrimaryKey(id);
        if (i > 0){
            return true;
        }
        return false;
    }

    public void update(UserDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        ExceptionUtils.checkIdIsNull(obj.getId(), User.class, "update");
        User user = BeanUtils.createBeanByTarget(obj, User.class);
        userDao.updateByPrimaryKeySelective(user);
    }

    @DataSource(name = DataSource.read)
    public UserDto getById(Long id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, User.class, "getById");
        User user = userDao.selectByPrimaryKey(id);
        UserDto dto = BeanUtils.createBeanByTarget(user, UserDto.class);
        return dto;
    }

    @DataSource(name = DataSource.read)
    public List<UserDto> queryByParam(UserDto obj) throws Exception {
        List<User> users = userDao.queryByParam(obj, null);
        if (ObjectUtils.notEmpty(users)){
            List<UserDto> list = BeanUtils.createBeanListByTarget(users, UserDto.class);
            return list;
        }
        return null;
    }

    @DataSource(name = DataSource.read)
    public PageResult<UserDto> queryByPage(UserDto obj, PageQuery query) throws Exception {
        int total = userDao.countByParam(obj);
        if (total > 0){
            query.setTotal(total);
            List<User> users = userDao.queryByParam(obj, query);
            List<UserDto> list = BeanUtils.createBeanListByTarget(users, UserDto.class);
            return new PageResult<UserDto>(query, list);
        }
        return null;
    }

    @DataSource(name = DataSource.read)
    public UserDto getUserByAccount(String account) throws Exception {
        if (StringUtils.notTrimEmpty(account)){
            UserDto user = new UserDto();
            user.setAccount(account.trim());
            List<UserDto> users = queryByParam(user);
            if (ObjectUtils.notEmpty(users)){
                return users.get(0);
            }
        }
        return null;
    }

    @DataSource(name = DataSource.read)
    public UserDto getUserByMobile(String mobile) throws Exception {
        if (StringUtils.notTrimEmpty(mobile)){
            UserDto user = new UserDto();
            user.setMobile(mobile.trim());
            List<UserDto> users = queryByParam(user);
            if (ObjectUtils.notEmpty(users)){
                return users.get(0);
            }
        }
        return null;
    }

    @DataSource(name = DataSource.read)
    public UserDto getUserByEmail(String email) throws Exception {
        if (StringUtils.notTrimEmpty(email)) {
            UserDto user = new UserDto();
            user.setEmail(email.trim());
            List<UserDto> users = queryByParam(user);
            if (ObjectUtils.notEmpty(users)) {
                return users.get(0);
            }
        }
        return null;
    }
}