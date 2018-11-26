package com.lionxxw.kqsystem.service.impl;

import com.lionxxw.kqsystem.code.model.PageQuery;
import com.lionxxw.kqsystem.code.model.PageResult;
import com.lionxxw.kqsystem.code.utils.BeanUtils;
import com.lionxxw.kqsystem.code.utils.DateUtils;
import com.lionxxw.kqsystem.code.utils.ExceptionUtils;
import com.lionxxw.kqsystem.code.utils.ObjectUtils;
import com.lionxxw.kqsystem.dao.OrderDinnerDao;
import com.lionxxw.kqsystem.dao.OrderDinnerOptionDao;
import com.lionxxw.kqsystem.db.DataSource;
import com.lionxxw.kqsystem.dto.OrderDinnerDto;
import com.lionxxw.kqsystem.entity.OrderDinner;
import com.lionxxw.kqsystem.entity.OrderDinnerOption;
import com.lionxxw.kqsystem.service.OrderDinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderDinnerServiceImpl implements OrderDinnerService {

    @Autowired
    private OrderDinnerDao dao;
    @Autowired
    private OrderDinnerOptionDao optionDao;

    @Transactional
    public OrderDinnerDto save(OrderDinnerDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        obj.setCreateTime(new Date());
        OrderDinner data = BeanUtils.createBeanByTarget(obj, OrderDinner.class);
        dao.insertSelective(data);
        obj.setId(data.getId());
        Long[] tempIds = obj.getTempIds();
        if (ObjectUtils.notEmpty(tempIds)){
            OrderDinnerOption option;
            for (Long tempId : tempIds){
                option = new OrderDinnerOption();
                option.setOrderId(data.getId());
                option.setTempId(tempId);
                optionDao.insertSelective(option);
            }
        }
        return obj;
    }

    public boolean delById(Long id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, OrderDinner.class, "delById");
        int i = dao.deleteByPrimaryKey(id);
        if (i > 0){
            return true;
        }
        return false;
    }

    public void update(OrderDinnerDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        ExceptionUtils.checkIdIsNull(obj.getId(), OrderDinner.class, "update");
        OrderDinner data = BeanUtils.createBeanByTarget(obj, OrderDinner.class);
        data.setLastUpdateTime(new Date());
        dao.updateByPrimaryKeySelective(data);
    }

    @DataSource(name = DataSource.read)
    public OrderDinnerDto getById(Long id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, OrderDinner.class, "getById");
        OrderDinner data = dao.selectByPrimaryKey(id);
        OrderDinnerDto dto = BeanUtils.createBeanByTarget(data, OrderDinnerDto.class);
        return dto;
    }

    @DataSource(name = DataSource.read)
    public List<OrderDinnerDto> queryByParam(OrderDinnerDto obj) throws Exception {
        List<OrderDinner> datas = dao.queryByParam(obj, null);
        if (ObjectUtils.notEmpty(datas)){
            List<OrderDinnerDto> list = BeanUtils.createBeanListByTarget(datas, OrderDinnerDto.class);
            return list;
        }
        return null;
    }

    @DataSource(name = DataSource.read)
    public PageResult<OrderDinnerDto> queryByPage(OrderDinnerDto obj, PageQuery query) throws Exception {
        int total = dao.countByParam(obj);
        if (total > 0){
            query.setTotal(total);
            List<OrderDinner> datas = dao.queryByParam(obj, query);
            List<OrderDinnerDto> list = BeanUtils.createBeanListByTarget(datas, OrderDinnerDto.class);
            return new PageResult<OrderDinnerDto>(query, list);
        }
        return null;
    }

    @Override
    public OrderDinnerDto getOrderDinnerByNow() throws Exception {
        OrderDinnerDto dto = new OrderDinnerDto();
        dto.setOrderDate(DateUtils.getJustDate(System.currentTimeMillis()));
        List<OrderDinnerDto> orderDinnerDtos = queryByParam(dto);
        if (ObjectUtils.notEmpty(orderDinnerDtos)){
            return orderDinnerDtos.get(0);
        }
        return null;
    }
}