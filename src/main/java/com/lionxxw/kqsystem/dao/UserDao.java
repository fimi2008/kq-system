package com.lionxxw.kqsystem.dao;

import com.lionxxw.kqsystem.code.base.MyBatisBaseDao;
import com.lionxxw.kqsystem.code.model.PageQuery;
import com.lionxxw.kqsystem.code.utils.ObjectUtils;
import com.lionxxw.kqsystem.code.utils.StringUtils;
import com.lionxxw.kqsystem.dto.UserDto;
import com.lionxxw.kqsystem.entity.User;
import com.lionxxw.kqsystem.entity.UserExample;
import com.lionxxw.kqsystem.mapper.UserMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * The type User dao.
 * Created by lionxxw on 2016-07-07 15:58:33
 */
@Repository
public class UserDao extends MyBatisBaseDao<User> {

    @Autowired
    @Getter
    private UserMapper mapper;

    public List<User> queryByParam(UserDto obj, PageQuery query) throws Exception{
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        if(null != query){
            example.setOrderByClause("create_time desc limit "+query.getStartNum() +"," + query.getPageSize());
        }else{
            example.setOrderByClause("create_time desc");
        }
        List<User> results = mapper.selectByExample(example);
        return results;
    }

    public int countByParam(UserDto obj) throws Exception{
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        return mapper.countByExample(example);
    }

    private void assemblyParams(UserDto params, UserExample.Criteria criteria) {
        if (null != params) {
            if (ObjectUtils.notNull(params.getId())){
                criteria.andIdEqualTo(params.getId());
            }
            if (StringUtils.notTrimEmpty(params.getCname())){
                criteria.andCnameEqualTo(params.getCname().trim());
            }
            if (StringUtils.notTrimEmpty(params.getEname())){
                criteria.andEnameEqualTo(params.getEname().trim());
            }
            if (StringUtils.notTrimEmpty(params.getMobile())){
                criteria.andMobileEqualTo(params.getMobile().trim());
            }
            if (StringUtils.notTrimEmpty(params.getEmail())){
                criteria.andEmailEqualTo(params.getEmail().trim());
            }
            if (StringUtils.notTrimEmpty(params.getAccount())){
                criteria.andAccountEqualTo(params.getAccount().trim());
            }
        }
    }
}
