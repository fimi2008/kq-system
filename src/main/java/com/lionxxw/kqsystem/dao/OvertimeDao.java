package com.lionxxw.kqsystem.dao;

import com.lionxxw.kqsystem.code.base.MyBatisBaseDao;
import com.lionxxw.kqsystem.code.model.PageQuery;
import com.lionxxw.kqsystem.code.utils.DateUtils;
import com.lionxxw.kqsystem.code.utils.ObjectUtils;
import com.lionxxw.kqsystem.code.utils.StringUtils;
import com.lionxxw.kqsystem.dto.OvertimeDto;
import com.lionxxw.kqsystem.entity.Overtime;
import com.lionxxw.kqsystem.entity.OvertimeExample;
import com.lionxxw.kqsystem.mapper.OvertimeMapper;
import com.sun.prism.impl.Disposer;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;


/**
 * The type Overtime dao.
 * Created by lionxxw on 2016-07-08 13:49:32
 */
@Repository
public class OvertimeDao extends MyBatisBaseDao<Overtime> {

    @Autowired
    @Getter
    private OvertimeMapper mapper;

    public List<Overtime> queryByParam(OvertimeDto obj, PageQuery query) throws Exception{
        OvertimeExample example = new OvertimeExample();
        OvertimeExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        if(null != query){
            example.setOrderByClause("work_date desc limit "+query.getStartNum() +"," + query.getPageSize());
        }else{
            example.setOrderByClause("work_date desc");
        }
        List<Overtime> results = mapper.selectByExample(example);
        return results;
    }

    public int countByParam(OvertimeDto obj) throws Exception{
        OvertimeExample example = new OvertimeExample();
        OvertimeExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        return mapper.countByExample(example);
    }

    /**
     * 批量更新
     * @param ids
     */
    public void batchDelOvertime(Long[] ids){
        Overtime record = new Overtime();
        record.setIsDel(true);
        OvertimeExample example = new OvertimeExample();
        OvertimeExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        mapper.updateByExampleSelective(record, example);
    }

    private void assemblyParams(OvertimeDto params, OvertimeExample.Criteria criteria) {
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
                criteria.andWorkDateLessThanOrEqualTo(DateUtils.getDate(params.getWorkDateE(), DateUtils.DATE_FROMAT_DAY));
            }
        }
        criteria.andIsDelEqualTo(false);
    }
}
