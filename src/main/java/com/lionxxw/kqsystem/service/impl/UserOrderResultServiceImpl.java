package com.lionxxw.kqsystem.service.impl;

import com.lionxxw.kqsystem.code.model.PageQuery;
import com.lionxxw.kqsystem.code.model.PageResult;
import com.lionxxw.kqsystem.code.utils.BeanUtils;
import com.lionxxw.kqsystem.code.utils.ExceptionUtils;
import com.lionxxw.kqsystem.code.utils.ObjectUtils;
import com.lionxxw.kqsystem.dao.OptionTemplateDao;
import com.lionxxw.kqsystem.dao.UserOrderResultDao;
import com.lionxxw.kqsystem.db.DataSource;
import com.lionxxw.kqsystem.dto.UserOrderResultDto;
import com.lionxxw.kqsystem.entity.UserOrderResult;
import com.lionxxw.kqsystem.service.UserOrderResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserOrderResultServiceImpl implements UserOrderResultService {

    @Autowired
    private UserOrderResultDao dao;
    @Autowired
    private OptionTemplateDao templateDao;

    public UserOrderResultDto save(UserOrderResultDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        obj.setCreateTime(new Date());
        UserOrderResult data = BeanUtils.createBeanByTarget(obj, UserOrderResult.class);
        dao.insertSelective(data);
        obj.setId(data.getId());
        templateDao.addCountByOptionId(data.getOptionId());
        return obj;
    }

    public boolean delById(Long id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, UserOrderResult.class, "delById");
        int i = dao.deleteByPrimaryKey(id);
        if (i > 0){
            return true;
        }
        return false;
    }

    public void update(UserOrderResultDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        ExceptionUtils.checkIdIsNull(obj.getId(), UserOrderResult.class, "update");
        UserOrderResult data = BeanUtils.createBeanByTarget(obj, UserOrderResult.class);
        data.setLastUpdateTime(new Date());
        dao.updateByPrimaryKeySelective(data);
    }

    @DataSource(name = DataSource.read)
    public UserOrderResultDto getById(Long id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, UserOrderResult.class, "getById");
        UserOrderResult data = dao.selectByPrimaryKey(id);
        UserOrderResultDto dto = BeanUtils.createBeanByTarget(data, UserOrderResultDto.class);
        return dto;
    }

    @DataSource(name = DataSource.read)
    public List<UserOrderResultDto> queryByParam(UserOrderResultDto obj) throws Exception {
        List<UserOrderResult> datas = dao.queryByParam(obj, null);
        if (ObjectUtils.notEmpty(datas)){
            List<UserOrderResultDto> list = BeanUtils.createBeanListByTarget(datas, UserOrderResultDto.class);
            return list;
        }
        return null;
    }

    @DataSource(name = DataSource.read)
    public PageResult<UserOrderResultDto> queryByPage(UserOrderResultDto obj, PageQuery query) throws Exception {
        int total = dao.countByParam(obj);
        if (total > 0){
            query.setTotal(total);
            List<UserOrderResult> datas = dao.queryByParam(obj, query);
            List<UserOrderResultDto> list = BeanUtils.createBeanListByTarget(datas, UserOrderResultDto.class);
            return new PageResult<UserOrderResultDto>(query, list);
        }
        return null;
    }

    @Override
    public UserOrderResultDto getResultByOrderId(Long orderId) throws Exception {
        ExceptionUtils.checkIdIsNull(orderId, UserOrderResult.class, "getResultByOrderId");
        UserOrderResultDto dto = new UserOrderResultDto();
        dto.setOrderId(orderId);
        List<UserOrderResultDto> results = queryByParam(dto);
        if (ObjectUtils.notEmpty(results)){
            return results.get(0);
        }
        return null;
    }
}