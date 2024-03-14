package com.ya.boottest.manage.order.service.impl;

import cn.hutool.core.date.DateUtil;
import com.ya.boottest.autocode.order.service.IFruitOrderLineService;
import com.ya.boottest.autocode.order.service.IFruitOrderService;
import com.ya.boottest.manage.order.service.OrderService;
import com.ya.boottest.utils.file.FileUtils;
import com.ya.boottest.utils.result.BaseResult;
import com.ya.boottest.utils.servlet.ServletUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

import static com.ya.boottest.utils.file.ExcelBorderEnum.BORDER_NO;

/**
 * <p>
 *  订单服务实现
 * </p>
 *
 * @author Ya Shi
 * @since 2023/8/12 16:55
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    IFruitOrderService iFruitOrderService;

    @Resource
    IFruitOrderLineService iFruitOrderLineService;

    @Override
    public Object export(String orderNum) {
        // 1. 生成Excel Workbook对象
        XSSFWorkbook workbook = initWorkbook(orderNum);
        HttpServletResponse rep = ServletUtils.getResponse();
        String errMessage;
        String fileName = "超市购进单-"+ DateUtil.today() + ".xlsx";
        if(Objects.isNull(rep)){
            throw new NullPointerException("HttpServletResponse 为空");
        }
        try {
            // 2. 将HSSFWorkbook文件写入到响应输出流中，供前端下载
            FileUtils.writeToResponse(workbook, fileName, rep);
            return null;
        }catch (IOException ioe){
            log.error("OrderServiceImpl export --- 导出过程中遇到输入输出异常： {}" ,ioe.toString());
            errMessage = "导出过程中遇到输入输出异常" + ioe;
        } catch (Exception e){
            log.error("OrderServiceImpl export --- 导出过程中遇到其他异常： {}" ,e.toString());
            errMessage = "导出过程中遇到其他异常：" + e;
        }

        return BaseResult.fail(errMessage);
    }

    private XSSFWorkbook initWorkbook(String orderNum){
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFCellStyle mainTitleCellStyle = FileUtils.getCellStyle(workbook, HorizontalAlignment.CENTER, BORDER_NO);
        // 设置字体
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 14);
        font.setBold(true); // 字体增粗
        font.setUnderline(Font.U_SINGLE); // 字体下划线
        mainTitleCellStyle.setFont(font);
        // 设置背景颜色
        mainTitleCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        mainTitleCellStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());

        XSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(0, "我是sheet1");
        // 冻结行
        sheet.createFreezePane(0, 1, 0, 1);
        // 设置列宽度
        sheet.setColumnWidth(0, 2000);
        sheet.setColumnWidth(1, 3000);
        sheet.setColumnWidth(2, 5000);
        sheet.setColumnWidth(3, 3000);

        // 填充行内容
        int r = 0;
        XSSFRow row = sheet.createRow(r);
        // 设置行高度
        row.setHeightInPoints(25);
        // 表格合并
        sheet.addMergedRegion(new CellRangeAddress(r, r, 0, 4));
        FileUtils.createTextCell(row, 0, "水果测试单据", mainTitleCellStyle);

        return workbook;
    }


}
