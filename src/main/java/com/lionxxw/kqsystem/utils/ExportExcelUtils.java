package com.lionxxw.kqsystem.utils;

import com.lionxxw.kqsystem.code.utils.DateUtils;
import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * excel导出工具类
 *
 * Created by lionxxw on 2016/7/12.
 */
public class ExportExcelUtils {

    public static int cell_row = 0;

    public ExportExcelUtils() {
    }

    public static void setTableName(HttpServletResponse response, String tableName) {
        try {
            response.reset();
            String e = tableName + DateUtils.formatDate(new Date(), DateUtils.DATE_FROMAT_DAY) + ".xls";
            e = new String(e.getBytes("gb2312"), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;fileName=" + e);
        } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
        }
    }

    public static void setCellHead(HSSFSheet sheet, HSSFRow row, HSSFWorkbook wb, String[] cellNameArr) throws Exception {
        HSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setBoldweight((short)700);
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFont(font);
        row = sheet.createRow(0);
        sheet.setDefaultColumnWidth((short)20);

        for(int i = 0; i < cellNameArr.length; ++i) {
            HSSFCell cell = row.createCell(i);
            cell.setCellType(1);
            cell.setCellValue(new HSSFRichTextString(cellNameArr[i]));
            cell.setCellStyle(cellStyle);
        }

        cell_row = 0;
    }

    public static void setCellData(HSSFSheet sheet, HSSFRow row, List<String[]> list, int[] type) throws Exception {
        for(int i = 0; i < list.size(); ++i) {
            row = sheet.createRow(cell_row + 1);
            String[] arr = (String[])list.get(i);

            for(int j = 0; j < ((String[])list.get(i)).length; ++j) {
                HSSFCell cell = row.createCell(j);
                if(type[j] == 0) {
                    cell.setCellType(0);
                    BigDecimal bdvalue = null;

                    try {
                        bdvalue = new BigDecimal(arr[j]);
                    } catch (Exception var10) {
                        bdvalue = new BigDecimal("0");
                    }

                    bdvalue = bdvalue.setScale(2, 4);
                    cell.setCellValue(bdvalue.doubleValue());
                } else if(type[j] == 1) {
                    cell.setCellType(1);
                    cell.setCellValue(new HSSFRichTextString(arr[j]));
                }
            }
            ++cell_row;
        }
    }

    public static void setCellDataRow(HSSFSheet sheet, HSSFRow row, String[] arr, int[] type) throws Exception {
        row = sheet.createRow(cell_row + 1);

        for(int i = 0; i < arr.length; ++i) {
            HSSFCell cell = row.createCell(i);
            if(type[i] == 0) {
                cell.setCellType(0);
                BigDecimal bdvalue = new BigDecimal(arr[i]);
                bdvalue = bdvalue.setScale(2, 4);
                cell.setCellValue(bdvalue.doubleValue());
            } else if(type[i] == 1) {
                cell.setCellType(1);
                cell.setCellValue(new HSSFRichTextString(arr[i]));
            }
        }

        ++cell_row;
    }

    public static void exportExcelData(String excelTitle, String[] cellNameArr, int[] cellTypeArr, List<String[]> dataList, String[] rowData, int[] rowCellTypeArr, boolean totalFlag, HttpServletResponse response) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        Object row = null;
        HSSFSheet sheet = wb.createSheet(excelTitle);
        setTableName(response, excelTitle);
        setCellHead(sheet, (HSSFRow)row, wb, cellNameArr);
        setCellData(sheet, (HSSFRow)row, dataList, cellTypeArr);
        if(rowData != null && rowData.length > 0) {
            if(totalFlag) {
                String[] totalData = new String[]{""};
                int[] totalType = new int[]{1};
                setCellDataRow(sheet, (HSSFRow)row, totalData, totalType);
            }

            setCellDataRow(sheet, (HSSFRow)row, rowData, rowCellTypeArr);
        }

        wb.write(response.getOutputStream());
    }
}