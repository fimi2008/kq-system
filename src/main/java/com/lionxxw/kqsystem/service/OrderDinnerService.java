package com.lionxxw.kqsystem.service;

import com.lionxxw.kqsystem.code.base.BaseService;
import com.lionxxw.kqsystem.code.base.ServiceException;
import com.lionxxw.kqsystem.dto.OptionTemplateDto;
import com.lionxxw.kqsystem.dto.OrderDinnerDto;


/**
 * The interface Order dinner service.
 * Created by lionxxw on 2016-07-27 15:48:41
 */
public interface OrderDinnerService extends BaseService<OrderDinnerDto> {

    /**
     * 获取今日订餐
     * @return
     * @throws Exception
     */
    OrderDinnerDto getOrderDinnerByNow() throws Exception;
}