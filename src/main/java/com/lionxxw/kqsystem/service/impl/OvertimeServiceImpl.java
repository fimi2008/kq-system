package com.lionxxw.kqsystem.service.impl;

import com.lionxxw.kqsystem.code.model.PageQuery;
import com.lionxxw.kqsystem.code.model.PageResult;
import com.lionxxw.kqsystem.code.utils.BeanUtils;
import com.lionxxw.kqsystem.code.utils.ExceptionUtils;
import com.lionxxw.kqsystem.code.utils.ObjectUtils;
import com.lionxxw.kqsystem.dao.OvertimeDao;
import com.lionxxw.kqsystem.db.DataSource;
import com.lionxxw.kqsystem.dto.OvertimeDto;
import com.lionxxw.kqsystem.entity.Overtime;
import com.lionxxw.kqsystem.service.OvertimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OvertimeServiceImpl implements OvertimeService {

    @Autowired
    private OvertimeDao overtimeDao;

    public OvertimeDto save(OvertimeDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        obj.setCreateTime(new Date());
        Overtime Overtime = BeanUtils.createBeanByTarget(obj, Overtime.class);
        overtimeDao.insertSelective(Overtime);
        obj.setId(Overtime.getId());
        return obj;
    }

    public boolean delById(Long id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, Overtime.class, "delById");
        int i = overtimeDao.deleteByPrimaryKey(id);
        if (i > 0){
            return true;
        }
        return false;
    }

    public void update(OvertimeDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        ExceptionUtils.checkIdIsNull(obj.getId(), Overtime.class, "update");
        Overtime overtime = BeanUtils.createBeanByTarget(obj, Overtime.class);
        overtime.setLastUpdateTime(new Date());
        overtimeDao.updateByPrimaryKeySelective(overtime);
    }

    @DataSource(name = DataSource.read)
    public OvertimeDto getById(Long id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, Overtime.class, "getById");
        Overtime overtime = overtimeDao.selectByPrimaryKey(id);
        OvertimeDto dto = BeanUtils.createBeanByTarget(overtime, OvertimeDto.class);
        return dto;
    }

    @DataSource(name = DataSource.read)
    public List<OvertimeDto> queryByParam(OvertimeDto obj) throws Exception {
        List<Overtime> overtimes = overtimeDao.queryByParam(obj, null);
        if (ObjectUtils.notEmpty(overtimes)){
            List<OvertimeDto> list = BeanUtils.createBeanListByTarget(overtimes, OvertimeDto.class);
            return list;
        }
        return null;
    }

    @DataSource(name = DataSource.read)
    public PageResult<OvertimeDto> queryByPage(OvertimeDto obj, PageQuery query) throws Exception {
        int total = overtimeDao.countByParam(obj);
        if (total > 0){
            query.setTotal(total);
            List<Overtime> overtimes = overtimeDao.queryByParam(obj, query);
            List<OvertimeDto> list = BeanUtils.createBeanListByTarget(overtimes, OvertimeDto.class);
            return new PageResult<OvertimeDto>(query, list);
        }
        return null;
    }

    public void batchDelOvertime(Long[] ids) throws Exception {
        overtimeDao.batchDelOvertime(ids);
    }
}