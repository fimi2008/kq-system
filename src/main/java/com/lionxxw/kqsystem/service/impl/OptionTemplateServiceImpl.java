package com.lionxxw.kqsystem.service.impl;

import com.lionxxw.kqsystem.code.model.PageQuery;
import com.lionxxw.kqsystem.code.model.PageResult;
import com.lionxxw.kqsystem.code.utils.BeanUtils;
import com.lionxxw.kqsystem.code.utils.ExceptionUtils;
import com.lionxxw.kqsystem.code.utils.ObjectUtils;
import com.lionxxw.kqsystem.dao.OptionTemplateDao;
import com.lionxxw.kqsystem.db.DataSource;
import com.lionxxw.kqsystem.dto.OptionTemplateDto;
import com.lionxxw.kqsystem.entity.OptionTemplate;
import com.lionxxw.kqsystem.service.OptionTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OptionTemplateServiceImpl implements OptionTemplateService {

    @Autowired
    private OptionTemplateDao dao;

    public OptionTemplateDto save(OptionTemplateDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        obj.setCreateTime(new Date());
        OptionTemplate data = BeanUtils.createBeanByTarget(obj, OptionTemplate.class);
        dao.insertSelective(data);
        obj.setId(data.getId());
        return obj;
    }

    public boolean delById(Long id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, OptionTemplate.class, "delById");
        int i = dao.deleteByPrimaryKey(id);
        if (i > 0){
            return true;
        }
        return false;
    }

    public void update(OptionTemplateDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        ExceptionUtils.checkIdIsNull(obj.getId(), OptionTemplate.class, "update");
        obj.setLastUpdateTime(new Date());
        OptionTemplate data = BeanUtils.createBeanByTarget(obj, OptionTemplate.class);
        dao.updateByPrimaryKeySelective(data);
    }

    @DataSource(name = DataSource.read)
    public OptionTemplateDto getById(Long id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, OptionTemplate.class, "getById");
        OptionTemplate data = dao.selectByPrimaryKey(id);
        OptionTemplateDto dto = BeanUtils.createBeanByTarget(data, OptionTemplateDto.class);
        return dto;
    }

    @DataSource(name = DataSource.read)
    public List<OptionTemplateDto> queryByParam(OptionTemplateDto obj) throws Exception {
        List<OptionTemplate> datas = dao.queryByParam(obj, null);
        if (ObjectUtils.notEmpty(datas)){
            List<OptionTemplateDto> list = BeanUtils.createBeanListByTarget(datas, OptionTemplateDto.class);
            return list;
        }
        return null;
    }

    @DataSource(name = DataSource.read)
    public PageResult<OptionTemplateDto> queryByPage(OptionTemplateDto obj, PageQuery query) throws Exception {
        int total = dao.countByParam(obj);
        if (total > 0){
            query.setTotal(total);
            List<OptionTemplate> datas = dao.queryByParam(obj, query);
            List<OptionTemplateDto> list = BeanUtils.createBeanListByTarget(datas, OptionTemplateDto.class);
            return new PageResult<OptionTemplateDto>(query, list);
        }
        return null;
    }

    @Override
    public void batchDelTemplate(Long[] ids) throws Exception {
        dao.batchDelTemplate(ids);
    }
}