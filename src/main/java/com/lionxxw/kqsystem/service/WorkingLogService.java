package com.lionxxw.kqsystem.service;

import com.lionxxw.kqsystem.code.base.BaseService;
import com.lionxxw.kqsystem.dto.WorkingLogDto;

import java.util.List;


/**
 * The interface Working log service.
 * Created by lionxxw on 2016-07-08 14:00:30
 */
public interface WorkingLogService extends BaseService<WorkingLogDto> {

    /**
     * 批量删除工作日志
     * @param ids
     * @throws Exception
     */
    void batchDelWorkingLog(Long[] ids) throws Exception;

    /**
     * 查询全数据工作日志不忽略长字段的
     * @param param
     * @return
     * @throws Exception
     */
    List<WorkingLogDto> queryFullWorkingLog(WorkingLogDto param) throws Exception;
}