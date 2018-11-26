package com.lionxxw.kqsystem.dao;

import com.lionxxw.kqsystem.code.base.MyBatisBaseDao;
import com.lionxxw.kqsystem.code.model.PageQuery;
import com.lionxxw.kqsystem.code.utils.DateUtils;
import com.lionxxw.kqsystem.code.utils.ObjectUtils;
import com.lionxxw.kqsystem.code.utils.StringUtils;
import com.lionxxw.kqsystem.dto.OrderDinnerDto;
import com.lionxxw.kqsystem.entity.OrderDinner;
import com.lionxxw.kqsystem.entity.OrderDinnerExample;
import com.lionxxw.kqsystem.mapper.OrderDinnerMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;


/**
 * The type Order dinner dao.
 * Created by lionxxw on 2016-07-27 14:48:24
 */
@Repository
public class OrderDinnerDao extends MyBatisBaseDao<OrderDinner> {

    @Autowired
    @Getter
    private OrderDinnerMapper mapper;

    public List<OrderDinner> queryByParam(OrderDinnerDto obj, PageQuery query) throws Exception{
        OrderDinnerExample example = new OrderDinnerExample();
        OrderDinnerExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        if(null != query){
            example.setOrderByClause("order_date desc limit "+query.getStartNum() +"," + query.getPageSize());
        }else{
            example.setOrderByClause("order_date desc");
        }
        List<OrderDinner> results = mapper.selectByExample(example);
        return results;
    }

    public int countByParam(OrderDinnerDto obj) throws Exception{
        OrderDinnerExample example = new OrderDinnerExample();
        OrderDinnerExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        return mapper.countByExample(example);
    }

    private void assemblyParams(OrderDinnerDto params, OrderDinnerExample.Criteria criteria) {
        if (null != params) {
            if (ObjectUtils.notNull(params.getId())){
                criteria.andIdEqualTo(params.getId());
            }
            if (ObjectUtils.notNull(params.getOrderDate())){
                criteria.andOrderDateEqualTo(params.getOrderDate());
            }
            if (ObjectUtils.notNull(params.getPublishUserId())){
                criteria.andPublishUserIdEqualTo(params.getPublishUserId());
            }
            if (null != params.getState()){
                criteria.andStateEqualTo(params.getState());
            }
        }
    }
}
