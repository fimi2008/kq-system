package com.lionxxw.kqsystem.service;

import com.lionxxw.kqsystem.code.base.BaseService;
import com.lionxxw.kqsystem.dto.OptionTemplateDto;
import com.lionxxw.kqsystem.dto.OvertimeDto;


/**
 * The interface Option template service.
 * Created by lionxxw on 2016-07-27 15:43:52
 */
public interface OptionTemplateService extends BaseService<OptionTemplateDto> {

    void batchDelTemplate(Long[] ids) throws Exception;
}