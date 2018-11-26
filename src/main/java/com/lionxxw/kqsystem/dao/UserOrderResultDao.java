package com.lionxxw.kqsystem.dao;

import com.lionxxw.kqsystem.code.base.MyBatisBaseDao;
import com.lionxxw.kqsystem.code.model.PageQuery;
import com.lionxxw.kqsystem.code.utils.DateUtils;
import com.lionxxw.kqsystem.code.utils.ObjectUtils;
import com.lionxxw.kqsystem.code.utils.StringUtils;
import com.lionxxw.kqsystem.dto.UserOrderResultDto;
import com.lionxxw.kqsystem.entity.UserOrderResult;
import com.lionxxw.kqsystem.entity.UserOrderResultExample;
import com.lionxxw.kqsystem.mapper.UserOrderResultMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;


/**
 * The type UserOrderResult dao.
 * Created by lionxxw on 2016-07-08 13:49:32
 */
@Repository
public class UserOrderResultDao extends MyBatisBaseDao<UserOrderResult> {

    @Autowired
    @Getter
    private UserOrderResultMapper mapper;

    public List<UserOrderResult> queryByParam(UserOrderResultDto obj, PageQuery query) throws Exception{
        UserOrderResultExample example = new UserOrderResultExample();
        UserOrderResultExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        if(null != query){
            example.setOrderByClause("create_time asc limit "+query.getStartNum() +"," + query.getPageSize());
        }else{
            example.setOrderByClause("create_time asc");
        }
        List<UserOrderResult> results = mapper.selectByExample(example);
        return results;
    }

    public int countByParam(UserOrderResultDto obj) throws Exception{
        UserOrderResultExample example = new UserOrderResultExample();
        UserOrderResultExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        return mapper.countByExample(example);
    }

    private void assemblyParams(UserOrderResultDto params, UserOrderResultExample.Criteria criteria) {
        if (null != params) {
            if (ObjectUtils.notNull(params.getId())){
                criteria.andIdEqualTo(params.getId());
            }
            if (ObjectUtils.notNull(params.getUserId())){
                criteria.andUserIdEqualTo(params.getUserId());
            }
            if (ObjectUtils.notNull(params.getOrderId())){
                criteria.andOrderIdEqualTo(params.getOrderId());
            }
            if (ObjectUtils.notNull(params.getOptionId())){
                criteria.andOptionIdEqualTo(params.getOptionId());
            }
            if (ObjectUtils.notNull(params.getIsAppend())){
                criteria.andIsAppendEqualTo(params.getIsAppend());
            }
        }
    }
}
