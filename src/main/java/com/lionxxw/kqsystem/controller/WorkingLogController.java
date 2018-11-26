package com.lionxxw.kqsystem.controller;

import com.lionxxw.kqsystem.code.constants.DataStatus;
import com.lionxxw.kqsystem.code.model.*;
import com.lionxxw.kqsystem.code.utils.DateUtils;
import com.lionxxw.kqsystem.code.utils.ResponseUtils;
import com.lionxxw.kqsystem.dto.OvertimeDto;
import com.lionxxw.kqsystem.dto.WorkingLogDto;
import com.lionxxw.kqsystem.mode.LoginUser;
import com.lionxxw.kqsystem.service.WorkingLogService;
import com.lionxxw.kqsystem.utils.ExportExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 个人工作日志管理控制层
 * 1.分页列表查询
 * 2.新增/编辑操作
 * 3.单个删除/批量删除操作
 *
 * Created by lionxxw on 2016/7/12.
 */
@Controller
public class WorkingLogController extends KqsController {

    @Autowired
    private WorkingLogService workingLogService;

    /**
     * 列表查询
     * @return
     */
    @RequestMapping(value = "/workingLog/list")
    public String workingLogList(){
        return "kqs/workingLog/list";
    }

    /**
     * ajax 获取工作日志数据(分页)
     * @param request
     * @param response
     * @param param
     * @param query
     * @throws Exception
     */
    @RequestMapping(value = "/workingLog/getWorkingLogDatas")
    public void getWorkingLogDatas(HttpServletRequest request, HttpServletResponse response, WorkingLogDto param, EasyUiPageQuery query){
        LoginUser loginUser = getLoginUser(request);
        param.setUserId(loginUser.getId());
        PageResult<WorkingLogDto> datas = null;
        try {
            datas = workingLogService.queryByPage(param, new PageQuery(query));
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResponseUtils.renderJson(response, new EasyUIPage<WorkingLogDto>(datas));
    }

    /**
     * 新增工作日志操作
     * @param dto
     * @return
     */
    @RequestMapping(value = "/workingLog/add", method = RequestMethod.POST)
    public void add(HttpServletRequest request, HttpServletResponse response, WorkingLogDto dto){
        Response<PageResult<WorkingLogDto>> res = new Response<PageResult<WorkingLogDto>>();
        LoginUser loginUser = getLoginUser(request);
        dto.setUserId(loginUser.getId());
        try {
            workingLogService.save(dto);
            res.setMessage("新增工作日志成功!");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(DataStatus.HTTP_FAILE);
            res.setMessage(e.getMessage());
        }
        ResponseUtils.renderJson(response, res);
    }

    /**
     * 跳转工作日志编辑页面
     *
     * @param response the response
     * @param id       the id
     * @author lionxxw
     * @date 2016 -07-11 10:25:23
     */
    @RequestMapping(value = "/workingLog/edit", method = RequestMethod.GET)
    public void edit(HttpServletResponse response, Long id){
        WorkingLogDto overtime = null;
        try {
            overtime = workingLogService.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResponseUtils.renderJson(response, overtime);
    }

    /**
     * 修改工作日志操作
     * @param dto
     * @return
     */
    @RequestMapping(value = "/workingLog/edit", method = RequestMethod.POST)
    public void edit(HttpServletRequest request, HttpServletResponse response, WorkingLogDto dto){
        Response<WorkingLogDto> res = new Response<WorkingLogDto>();
        LoginUser loginUser = getLoginUser(request);
        dto.setUserId(loginUser.getId());
        try {
            dto.setLastUpdateTime(new Date());
            workingLogService.update(dto);
            res.setMessage("修改工作日志成功!");
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
    @RequestMapping(value = "/workingLog/del")
    public void del(HttpServletRequest request, HttpServletResponse response, Long[] ids){
        Response<PageResult<WorkingLogDto>> res = new Response<PageResult<WorkingLogDto>>();
        try {
            workingLogService.batchDelWorkingLog(ids);
            res.setMessage("删除工作日志成功!");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(DataStatus.HTTP_FAILE);
            res.setMessage(e.getMessage());
        }
        ResponseUtils.renderJson(response, res);
    }

    /**
     * 个人工作日志导出
     * @param request
     * @param response
     * @param param
     */
    @RequestMapping(value = "/workingLog/export")
    public void export(HttpServletRequest request, HttpServletResponse response, WorkingLogDto param){
        LoginUser loginUser = getLoginUser(request);
        param.setUserId(loginUser.getId());
        // 导出参数
        HSSFWorkbook wb = new HSSFWorkbook();// 建立新HSSFWorkbook对象
        HSSFRow row = null;

        // 导出
        try {
            // 查询获取数据
            String fillName = loginUser.getCname()+"@"+loginUser.getEname()+"工作日志";
            HSSFSheet sheet = wb.createSheet(fillName);// 建立新的sheet对象
            ExportExcelUtils.setTableName(response, fillName);
            String[] cellNameArr = {"日期", "日志"};
            ExportExcelUtils.setCellHead(sheet, row, wb, cellNameArr);// 创建列头
            List<String[]> list = new ArrayList<String[]>();
            List<WorkingLogDto> datas = workingLogService.queryFullWorkingLog(param);
            for (WorkingLogDto sett : datas) {
                String[] data = {
                        getString(DateUtils.formatDate(sett.getWorkDate(), DateUtils.DATE_FROMAT_DAY)),
                        getString(sett.getNote().replaceAll("<[a-zA-Z]+[1-9]?[^><]*>", "").replaceAll("</[a-zA-Z]+[1-9]?>", ""))
                };
                list.add(data);
            }
            int[] cellTypeArr = {1, 1};// 1为字符串
            ExportExcelUtils.setCellData(sheet, row, list, cellTypeArr); // 创建列的数据
            wb.write(response.getOutputStream());// 导出excel
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}