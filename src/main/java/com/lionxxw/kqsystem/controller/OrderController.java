package com.lionxxw.kqsystem.controller;

import com.lionxxw.kqsystem.code.constants.DataStatus;
import com.lionxxw.kqsystem.code.model.*;
import com.lionxxw.kqsystem.code.utils.DateUtils;
import com.lionxxw.kqsystem.code.utils.ObjectUtils;
import com.lionxxw.kqsystem.code.utils.ResponseUtils;
import com.lionxxw.kqsystem.dto.*;
import com.lionxxw.kqsystem.mode.LoginUser;
import com.lionxxw.kqsystem.service.OptionTemplateService;
import com.lionxxw.kqsystem.service.OrderDinnerOptionService;
import com.lionxxw.kqsystem.service.OrderDinnerService;
import com.lionxxw.kqsystem.service.UserOrderResultService;
import com.lionxxw.kqsystem.utils.ExportExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 订餐操作控制层
 *
 * 发布订餐 /kqs/order/publish.do
 * 订餐模板 /kqs/order/template.do
 * 今日订餐 /kqs/order/now.do
 * Created by lionxxw on 2016/7/27.
 */
@Controller
public class OrderController extends KqsController {
    @Autowired
    private OrderDinnerService orderDinnerService;
    @Autowired
    private OrderDinnerOptionService optionService;
    @Autowired
    private OptionTemplateService optionTemplateService;
    @Autowired
    private UserOrderResultService userOrderResultService;

    @RequestMapping(value = "/order/publish", method = RequestMethod.GET)
    public ModelAndView publish() throws Exception{
        ModelAndView mv = new ModelAndView();
        OrderDinnerDto order = getNowDateOrder();
        List<OptionTemplateDto> templates = getOtherOptions(order);
        mv.addObject("order", order);
        mv.addObject("templates", templates);
        mv.setViewName("/kqs/order/publish");
        return mv;
    }

    /**
     * 确认发布
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/order/confirmPublish")
    public void confirmPublish(HttpServletRequest request, HttpServletResponse response, Long id) throws Exception{
        Response<String> res = new Response<String>();
        OrderDinnerDto order = orderDinnerService.getById(id);
        order.setState(OrderDinnerDto.OrderState.PUBLISH.getState());
        LoginUser loginUser = getLoginUser(request);
        order.setPublishUserId(loginUser.getId());
        order.setPublishTime(new Date());
        try {
            orderDinnerService.update(order);
            res.setMessage("恭喜您发布成功!");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(DataStatus.HTTP_FAILE);
            res.setMessage(e.getMessage());
        }
        ResponseUtils.renderJson(response, res);
    }

    /**
     * 取消发布
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/order/cancelPublish")
    public void cancelPublish(HttpServletRequest request, HttpServletResponse response, Long id) throws Exception{
        Response<String> res = new Response<String>();
        OrderDinnerDto order = orderDinnerService.getById(id);
        order.setState(OrderDinnerDto.OrderState.CANCEL.getState());
        try {
            orderDinnerService.update(order);
            res.setMessage("恭喜您作废成功!");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(DataStatus.HTTP_FAILE);
            res.setMessage(e.getMessage());
        }
        ResponseUtils.renderJson(response, res);
    }

    @RequestMapping(value = "/order/publish", method = RequestMethod.POST)
    public void publish(HttpServletRequest request, HttpServletResponse response, OrderDinnerDto dto) throws Exception{
        Response<String> res = new Response<String>();
        LoginUser loginUser = getLoginUser(request);
        dto.setCreateUserId(loginUser.getId());
        try {
            orderDinnerService.save(dto);
            res.setMessage("创建今日订餐成功!");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(DataStatus.HTTP_FAILE);
            res.setMessage(e.getMessage());
        }
        ResponseUtils.renderJson(response, res);
    }

    @RequestMapping(value = "/order/template")
    public ModelAndView template() throws Exception{
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/kqs/order/template");
        return mv;
    }

    @RequestMapping(value = "/order/getTemplateList")
    public void getTemplateList(HttpServletRequest request, HttpServletResponse response, OptionTemplateDto param, EasyUiPageQuery query){
        LoginUser loginUser = getLoginUser(request);
        PageResult<OptionTemplateDto> datas = null;
        try {
            datas = optionTemplateService.queryByPage(param, new PageQuery(query));
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResponseUtils.renderJson(response, new EasyUIPage<OptionTemplateDto>(datas));
    }

    /**
     * 新增选项模板操作
     * @param dto
     * @return
     */
    @RequestMapping(value = "/order/addTemplate", method = RequestMethod.POST)
    public void addTemplate(HttpServletRequest request, HttpServletResponse response, OptionTemplateDto dto){
        Response<PageResult<WorkingLogDto>> res = new Response<PageResult<WorkingLogDto>>();
        LoginUser loginUser = getLoginUser(request);
        dto.setCreateUserId(loginUser.getId());
        try {
            optionTemplateService.save(dto);
            res.setMessage("新增选项模板成功!");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(DataStatus.HTTP_FAILE);
            res.setMessage(e.getMessage());
        }
        ResponseUtils.renderJson(response, res);
    }

    /**
     * 跳转选项模板编辑页面
     *
     * @param response the response
     * @param id       the id
     * @author lionxxw
     * @date 2016 -07-11 10:25:23
     */
    @RequestMapping(value = "/order/editTemplate", method = RequestMethod.GET)
    public void editTemplate(HttpServletResponse response, Long id){
        OptionTemplateDto dto = null;
        try {
            dto = optionTemplateService.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResponseUtils.renderJson(response, dto);
    }

    /**
     * 修改选项模板操作
     * @param dto
     * @return
     */
    @RequestMapping(value = "/order/editTemplate", method = RequestMethod.POST)
    public void editTemplate(HttpServletRequest request, HttpServletResponse response, OptionTemplateDto dto){
        Response<Object> res = new Response<Object>();
        LoginUser loginUser = getLoginUser(request);
        dto.setLastUpdateUserId(loginUser.getId());
        dto.setLastUpdateTime(new Date());
        try {
            dto.setLastUpdateTime(new Date());
            optionTemplateService.update(dto);
            res.setMessage("修改选项模板成功!");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(DataStatus.HTTP_FAILE);
            res.setMessage(e.getMessage());
        }
        ResponseUtils.renderJson(response, res);
    }

    /**
     * 单个删除/批量删除选项模板操作
     * @param request
     * @param response
     * @param ids
     */
    @RequestMapping(value = "/order/delTemplate")
    public void delTemplate(HttpServletRequest request, HttpServletResponse response, Long[] ids){
        Response<Object> res = new Response<Object>();
        try {
            optionTemplateService.batchDelTemplate(ids);
            res.setMessage("删除工作日志成功!");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(DataStatus.HTTP_FAILE);
            res.setMessage(e.getMessage());
        }
        ResponseUtils.renderJson(response, res);
    }

    /**
     * 今日订餐
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/order/now", method = RequestMethod.GET)
    public ModelAndView now() throws Exception{
        ModelAndView mv = new ModelAndView();
        OrderDinnerDto nowDateOrder = getNowDateOrder();
        if (ObjectUtils.notNull(nowDateOrder) && nowDateOrder.getState() == OrderDinnerDto.OrderState.PUBLISH.getState()){
            getOtherOptions(nowDateOrder);
            mv.addObject("order", nowDateOrder);
            UserOrderResultDto result =  userOrderResultService.getResultByOrderId(nowDateOrder.getId());
            if (ObjectUtils.notNull(result) && result.getState() == 1){
                mv.addObject("result", result);
            }
        }
        mv.setViewName("/kqs/order/now");
        return mv;
    }

    /**
     * 今日订餐报名
     * @param request
     * @param response
     * @param dto
     * @throws Exception
     */
    @RequestMapping(value = "/order/now", method = RequestMethod.POST)
    public void now(HttpServletRequest request, HttpServletResponse response, UserOrderResultDto dto) throws Exception{
        Response<Object> res = new Response<Object>();
        LoginUser loginUser = getLoginUser(request);
        dto.setUserId(loginUser.getId());
        dto.setUserCname(loginUser.getCname());
        dto.setUserEname(loginUser.getEname());
        try {
            UserOrderResultDto result =  userOrderResultService.getResultByOrderId(dto.getOrderId());
            String msg = "报名";
            if (ObjectUtils.notNull(result) && result.getState() == 1){
                result.setOptionId(dto.getOptionId());
                result.setState(result.getState()*-1);
                if (result.getState() == -1){
                    msg = "退订";
                }
                userOrderResultService.update(result);
            }else{
                userOrderResultService.save(dto);
            }
            res.setMessage("恭喜您"+msg+"成功!");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(DataStatus.HTTP_FAILE);
            res.setMessage(e.getMessage());
        }
        ResponseUtils.renderJson(response, res);
    }

    private OrderDinnerDto getNowDateOrder() throws Exception {
        OrderDinnerDto nowOrder = orderDinnerService.getOrderDinnerByNow();
        if (null != nowOrder){
            List<OrderDinnerOptionDto> options = optionService.queryOptionByOrderId(nowOrder.getId());
            nowOrder.setOptions(options);
        }
        return nowOrder;
    }

    /**
     * 过滤已经选择的选项
     * @param order
     * @return
     * @throws Exception
     */
    private List<OptionTemplateDto> getOtherOptions(OrderDinnerDto order) throws Exception {
        List<OptionTemplateDto> templates = optionTemplateService.queryByParam(null);
        if (ObjectUtils.notNull(order) && ObjectUtils.notEmpty(templates) && ObjectUtils.notEmpty(order.getOptions())){
            Iterator<OptionTemplateDto> iterator = templates.iterator();
            while (iterator.hasNext()){
                OptionTemplateDto next = iterator.next();
                if (order.getOptions().contains(next)){
                    iterator.remove();
                }
            }
        }
        return templates;
    }
}
