package com.lionxxw.kqsystem.service;

import com.lionxxw.kqsystem.code.base.BaseService;
import com.lionxxw.kqsystem.dto.OptionTemplateDto;
import com.lionxxw.kqsystem.dto.UserOrderResultDto;


/**
 * The interface User order result service.
 * Created by lionxxw on 2016-07-27 15:48:25
 */
public interface UserOrderResultService extends BaseService<UserOrderResultDto> {

    /**
     * 根据订单id查询用户点餐记录
     * @param orderId
     * @return
     * @throws Exception
     */
    UserOrderResultDto getResultByOrderId(Long orderId) throws Exception;
}