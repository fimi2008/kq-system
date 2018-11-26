package com.lionxxw.kqsystem.service.impl;

import com.lionxxw.kqsystem.code.model.PageQuery;
import com.lionxxw.kqsystem.code.model.PageResult;
import com.lionxxw.kqsystem.code.utils.BeanUtils;
import com.lionxxw.kqsystem.code.utils.ExceptionUtils;
import com.lionxxw.kqsystem.code.utils.ObjectUtils;
import com.lionxxw.kqsystem.dao.OrderDinnerOptionDao;
import com.lionxxw.kqsystem.db.DataSource;
import com.lionxxw.kqsystem.dto.OrderDinnerOptionDto;
import com.lionxxw.kqsystem.entity.OrderDinnerOption;
import com.lionxxw.kqsystem.service.OrderDinnerOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDinnerOptionServiceImpl implements OrderDinnerOptionService {

    @Autowired
    private OrderDinnerOptionDao dao;

    public OrderDinnerOptionDto save(OrderDinnerOptionDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        OrderDinnerOption data = BeanUtils.createBeanByTarget(obj, OrderDinnerOption.class);
        dao.insertSelective(data);
        obj.setId(data.getId());
        return obj;
    }

    public boolean delById(Long id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, OrderDinnerOption.class, "delById");
        int i = dao.deleteByPrimaryKey(id);
        if (i > 0){
            return true;
        }
        return false;
    }

    public void update(OrderDinnerOptionDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        ExceptionUtils.checkIdIsNull(obj.getId(), OrderDinnerOption.class, "update");
        OrderDinnerOption data = BeanUtils.createBeanByTarget(obj, OrderDinnerOption.class);
        dao.updateByPrimaryKeySelective(data);
    }

    @DataSource(name = DataSource.read)
    public OrderDinnerOptionDto getById(Long id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, OrderDinnerOption.class, "getById");
        OrderDinnerOption data = dao.selectByPrimaryKey(id);
        OrderDinnerOptionDto dto = BeanUtils.createBeanByTarget(data, OrderDinnerOptionDto.class);
        return dto;
    }

    @DataSource(name = DataSource.read)
    public List<OrderDinnerOptionDto> queryByParam(OrderDinnerOptionDto obj) throws Exception {
        List<OrderDinnerOption> datas = dao.queryByParam(obj, null);
        if (ObjectUtils.notEmpty(datas)){
            List<OrderDinnerOptionDto> list = BeanUtils.createBeanListByTarget(datas, OrderDinnerOptionDto.class);
            return list;
        }
        return null;
    }

    @DataSource(name = DataSource.read)
    public PageResult<OrderDinnerOptionDto> queryByPage(OrderDinnerOptionDto obj, PageQuery query) throws Exception {
        int total = dao.countByParam(obj);
        if (total > 0){
            query.setTotal(total);
            List<OrderDinnerOption> datas = dao.queryByParam(obj, query);
            List<OrderDinnerOptionDto> list = BeanUtils.createBeanListByTarget(datas, OrderDinnerOptionDto.class);
            return new PageResult<OrderDinnerOptionDto>(query, list);
        }
        return null;
    }

    @Override
    public List<OrderDinnerOptionDto> queryOptionByOrderId(Long orderId) throws Exception {
        OrderDinnerOptionDto dto = new OrderDinnerOptionDto();
        dto.setOrderId(orderId);
        return queryByParam(dto);
    }
}