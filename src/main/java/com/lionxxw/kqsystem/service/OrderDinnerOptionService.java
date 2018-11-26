package com.lionxxw.kqsystem.service;

import com.lionxxw.kqsystem.code.base.BaseService;
import com.lionxxw.kqsystem.dto.OrderDinnerOptionDto;

import java.util.List;


/**
 * The interface Order dinner option service.
 * Created by lionxxw on 2016-07-27 15:48:48
 */
public interface OrderDinnerOptionService extends BaseService<OrderDinnerOptionDto> {

    /**
     * 根据订餐id获取订餐选项
     * @param orderId 订餐id
     * @return
     * @throws Exception
     */
    List<OrderDinnerOptionDto> queryOptionByOrderId(Long orderId) throws Exception;
}