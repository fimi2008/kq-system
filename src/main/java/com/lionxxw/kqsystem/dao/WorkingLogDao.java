package com.lionxxw.kqsystem.dao;

import com.lionxxw.kqsystem.code.base.MyBatisBaseDao;
import com.lionxxw.kqsystem.code.model.PageQuery;
import com.lionxxw.kqsystem.code.utils.DateUtils;
import com.lionxxw.kqsystem.code.utils.ObjectUtils;
import com.lionxxw.kqsystem.code.utils.StringUtils;
import com.lionxxw.kqsystem.dto.WorkingLogDto;
import com.lionxxw.kqsystem.entity.Overtime;
import com.lionxxw.kqsystem.entity.OvertimeExample;
import com.lionxxw.kqsystem.entity.WorkingLog;
import com.lionxxw.kqsystem.entity.WorkingLogExample;
import com.lionxxw.kqsystem.mapper.WorkingLogMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;


/**
 * The type Working log dao.
 * Created by lionxxw on 2016-07-08 13:57:45
 */
@Repository
public class WorkingLogDao extends MyBatisBaseDao<WorkingLog> {

    @Autowired
    @Getter
    private WorkingLogMapper mapper;

    public List<WorkingLog> queryByParam(WorkingLogDto obj, PageQuery query) throws Exception{
        WorkingLogExample example = new WorkingLogExample();
        WorkingLogExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        if(null != query){
            example.setOrderByClause("work_date desc limit "+query.getStartNum() +"," + query.getPageSize());
        }else{
            example.setOrderByClause("work_date desc");
        }
        List<WorkingLog> results = mapper.selectByExample(example);
        return results;
    }

    public int countByParam(WorkingLogDto obj) throws Exception{
        WorkingLogExample example = new WorkingLogExample();
        WorkingLogExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        return mapper.countByExample(example);
    }

    private void assemblyParams(WorkingLogDto params, WorkingLogExample.Criteria criteria) {
        if (null != params) {
            if (ObjectUtils.notNull(params.getId())){
                criteria.andIdEqualTo(params.getId());
            }
            if (ObjectUtils.notNull(params.getUserId())){
                criteria.andUserIdEqualTo(params.getUserId());
            }
            if (StringUtils.notTrimEmpty(params.getWorkDateS())){
                criteria.andWorkDateGreaterThanOrEqualTo(DateUtils.getDate(params.getWorkDateS(), DateUtils.DATE_FROMAT_DAY));
            }
            if (StringUtils.notTrimEmpty(params.getWorkDateE())){
                criteria.andWorkDateLessThan(DateUtils.getDate(params.getWorkDateE(), DateUtils.DATE_FROMAT_DAY, 1));
            }
        }
        criteria.andIsDelEqualTo(false);
    }

    public void batchDelWorkingLog(Long[] ids) {
        WorkingLog record = new WorkingLog();
        record.setIsDel(true);
        WorkingLogExample example = new WorkingLogExample();
        WorkingLogExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        mapper.updateByExampleSelective(record, example);
    }

    public List<WorkingLog> queryFullWorkingLog(WorkingLogDto param) {
        WorkingLogExample example = new WorkingLogExample();
        WorkingLogExample.Criteria criteria = example.createCriteria();
        assemblyParams(param, criteria);
        example.setOrderByClause("work_date desc");
        List<WorkingLog> results = mapper.selectByExampleWithBLOBs(example);
        return results;
    }
}
