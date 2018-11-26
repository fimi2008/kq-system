package com.lionxxw.kqsystem.controller;

import com.lionxxw.kqsystem.code.constants.DataStatus;
import com.lionxxw.kqsystem.code.model.*;
import com.lionxxw.kqsystem.code.utils.DateUtils;
import com.lionxxw.kqsystem.code.utils.ResponseUtils;
import com.lionxxw.kqsystem.dto.OvertimeDto;
import com.lionxxw.kqsystem.entity.Overtime;
import com.lionxxw.kqsystem.mode.LoginUser;
import com.lionxxw.kqsystem.service.OvertimeService;
import com.lionxxw.kqsystem.utils.ExportExcelUtils;
import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;
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
import java.util.List;

/**
 * 个人加班控制层
 * 1.分页列表查询
 * 2.新增/编辑操作
 * 3.单个删除/批量删除操作
 *
 * Created by lionxxw on 2016/7/8.
 */
@Controller
public class OvertimeController extends KqsController {

    @Autowired
    private OvertimeService overtimeService;

    /**
     * 列表查询
     * @return
     */
    @RequestMapping(value = "/overtime/list")
    public String overtimeList(){
        return "kqs/overtime/list";
    }

    /**
     * ajax 获取加班记录数据(分页)
     * @param request
     * @param response
     * @param param
     * @param query
     * @throws Exception
     */
    @RequestMapping(value = "/overtime/getOvertimeDatas")
    public void getOvertimeDatas(HttpServletRequest request, HttpServletResponse response, OvertimeDto param, EasyUiPageQuery query){
        LoginUser loginUser = getLoginUser(request);
        param.setUserId(loginUser.getId());
        PageResult<OvertimeDto> datas = null;
        try {
            datas = overtimeService.queryByPage(param, new PageQuery(query));
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResponseUtils.renderJson(response, new EasyUIPage<OvertimeDto>(datas));
    }

    /**
     * 新增加班记录操作
     * @param dto
     * @return
     */
    @RequestMapping(value = "/overtime/add", method = RequestMethod.POST)
    public void add(HttpServletRequest request, HttpServletResponse response, OvertimeDto dto){
        Response<PageResult<OvertimeDto>> res = new Response<PageResult<OvertimeDto>>();
        LoginUser loginUser = getLoginUser(request);
        dto.setUserId(loginUser.getId());
        try {
            overtimeService.save(dto);
            res.setMessage("新增加班记录成功!");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(DataStatus.HTTP_FAILE);
            res.setMessage(e.getMessage());
        }
        ResponseUtils.renderJson(response, res);
    }

    /**
     * 跳转加班记录编辑页面
     *
     * @param response the response
     * @param id       the id
     * @author lionxxw
     * @date 2016 -07-11 10:25:23
     */
    @RequestMapping(value = "/overtime/edit", method = RequestMethod.GET)
    public void edit(HttpServletResponse response, Long id){
        OvertimeDto overtime = null;
        try {
            overtime = overtimeService.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResponseUtils.renderJson(response, overtime);
    }

    /**
     * 修改加班记录操作
     * @param dto
     * @return
     */
    @RequestMapping(value = "/overtime/edit", method = RequestMethod.POST)
    public void edit(HttpServletRequest request, HttpServletResponse response, OvertimeDto dto){
        Response<OvertimeDto> res = new Response<OvertimeDto>();
        LoginUser loginUser = getLoginUser(request);
        dto.setUserId(loginUser.getId());
        try {
            dto.setLastUpdateTime(new Date());
            overtimeService.update(dto);
            res.setMessage("修改加班记录成功!");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(DataStatus.HTTP_FAILE);
            res.setMessage(e.getMessage());
        }
        ResponseUtils.renderJson(response, res);
    }

    /**
     * 单个删除/批量删除操作
     * @param request
     * @param response
     * @param ids
     */
    @RequestMapping(value = "/overtime/del")
    public void del(HttpServletRequest request, HttpServletResponse response, Long[] ids){
        Response<PageResult<OvertimeDto>> res = new Response<PageResult<OvertimeDto>>();
        try {
            overtimeService.batchDelOvertime(ids);
            res.setMessage("删除加班记录成功!");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(DataStatus.HTTP_FAILE);
            res.setMessage(e.getMessage());
        }
        ResponseUtils.renderJson(response, res);
    }

    /**
     * 个人加班记录导出
     * @param request
     * @param response
     * @param param
     */
    @RequestMapping(value = "/overtime/export")
    public void export(HttpServletRequest request, HttpServletResponse response, OvertimeDto param){
        LoginUser loginUser = getLoginUser(request);
        param.setUserId(loginUser.getId());
        // 导出参数
        HSSFWorkbook wb = new HSSFWorkbook();// 建立新HSSFWorkbook对象
        HSSFRow row = null;

        // 导出
        try {
            // 查询获取数据
            String fillName = loginUser.getCname()+"@"+loginUser.getEname()+"加班记录";
            HSSFSheet sheet = wb.createSheet(fillName);// 建立新的sheet对象
            ExportExcelUtils.setTableName(response, fillName);
            String[] cellNameArr = {"加班日期", "开始时间", "结束时间", "总计(单位:小时)","备注"};
            ExportExcelUtils.setCellHead(sheet, row, wb, cellNameArr);// 创建列头
            List<String[]> list = new ArrayList<String[]>();
            List<OvertimeDto> datas = overtimeService.queryByParam(param);
            for (OvertimeDto sett : datas) {
                String[] data = {
                        getString(DateUtils.formatDate(sett.getWorkDate(), DateUtils.DATE_FROMAT_DAY)),
                        getString(sett.getStartTime()),
                        getString(sett.getEndTime()),
                        getString(sett.getTotalHouse()),
                        getString(sett.getNote())
                };
                list.add(data);
            }
            int[] cellTypeArr = {1, 1, 1, 1, 1};// 1为字符串
            ExportExcelUtils.setCellData(sheet, row, list, cellTypeArr); // 创建列的数据
            wb.write(response.getOutputStream());// 导出excel
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}