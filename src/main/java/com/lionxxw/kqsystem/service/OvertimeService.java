package com.lionxxw.kqsystem.service;

import com.lionxxw.kqsystem.code.base.BaseService;
import com.lionxxw.kqsystem.dto.OvertimeDto;
import com.lionxxw.kqsystem.dto.UserDto;


/**
 * The interface Overtime service.
 * Created by lionxxw on 2016-07-08 14:00:00
 */
public interface OvertimeService extends BaseService<OvertimeDto> {

    /**
     * 批量删除加班记录
     * @param ids
     * @throws Exception
     */
    void batchDelOvertime(Long[] ids) throws Exception;
}