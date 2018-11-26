package com.lionxxw.kqsystem.dao;

import com.lionxxw.kqsystem.code.base.MyBatisBaseDao;
import com.lionxxw.kqsystem.code.model.PageQuery;
import com.lionxxw.kqsystem.code.utils.ObjectUtils;
import com.lionxxw.kqsystem.dto.OrderDinnerOptionDto;
import com.lionxxw.kqsystem.entity.OrderDinnerOption;
import com.lionxxw.kqsystem.entity.OrderDinnerOptionExample;
import com.lionxxw.kqsystem.mapper.OrderDinnerOptionMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * The type Order dinner dao.
 * Created by lionxxw on 2016-07-27 14:48:24
 */
@Repository
public class OrderDinnerOptionDao extends MyBatisBaseDao<OrderDinnerOption> {

    @Autowired
    @Getter
    private OrderDinnerOptionMapper mapper;

    public List<OrderDinnerOption> queryByParam(OrderDinnerOptionDto obj, PageQuery query) throws Exception{
        OrderDinnerOptionExample example = new OrderDinnerOptionExample();
        OrderDinnerOptionExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        if(null != query){
            example.setOrderByClause("id asc limit "+query.getStartNum() +"," + query.getPageSize());
        }else{
            example.setOrderByClause("id asc");
        }
        List<OrderDinnerOption> results = mapper.selectByExample(example);
        return results;
    }

    public int countByParam(OrderDinnerOptionDto obj) throws Exception{
        OrderDinnerOptionExample example = new OrderDinnerOptionExample();
        OrderDinnerOptionExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        return mapper.countByExample(example);
    }

    private void assemblyParams(OrderDinnerOptionDto params, OrderDinnerOptionExample.Criteria criteria) {
        if (null != params) {
            if (ObjectUtils.notNull(params.getId())){
                criteria.andIdEqualTo(params.getId());
            }
            if (ObjectUtils.notNull(params.getOrderId())){
                criteria.andOrderIdEqualTo(params.getOrderId());
            }
            if (ObjectUtils.notNull(params.getTempId())){
                criteria.andTempIdEqualTo(params.getTempId());
            }
        }
    }
}
