package com.ya.boottest.utils.file;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 *  文件util
 * </p>
 *
 * @author Ya Shi
 * @since 2023/8/11 11:58
 */
@Slf4j
public class FileUtils {

    public static void createTextCell(XSSFRow row, int col, String text, XSSFCellStyle cellStyle) {
        XSSFCell cell = row.createCell(col);
        cell.setCellValue(text);
        cell.setCellStyle(cellStyle);
    }

    /**
     *  水平方向：参数控制；垂直方向：居中；边框：由枚举控制
     * @param workbook  HSSFWorkbook
     * @param align 文字水平方向
     * @param borderEnum 边框枚举
     * @return HSSFCellStyle
     */
    public static XSSFCellStyle getCellStyle(XSSFWorkbook workbook, HorizontalAlignment align, ExcelBorderEnum borderEnum){
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(align);//水平方向
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        //设置边框
        switch (borderEnum){
            case BORDER_ALL -> {
                cellStyle.setBorderLeft(BorderStyle.THIN);
                cellStyle.setBorderTop(BorderStyle.THIN);
                cellStyle.setBorderRight(BorderStyle.THIN);
                cellStyle.setBorderBottom(BorderStyle.THIN);
            }
            case BORDER_SIDE -> {
                cellStyle.setBorderLeft(BorderStyle.THIN);
                cellStyle.setBorderRight(BorderStyle.THIN);
                cellStyle.setBorderTop(BorderStyle.NONE);
                cellStyle.setBorderBottom(BorderStyle.NONE);
            }
            case BORDER_NO -> {
                cellStyle.setBorderLeft(BorderStyle.NONE);
                cellStyle.setBorderRight(BorderStyle.NONE);
                cellStyle.setBorderTop(BorderStyle.NONE);
                cellStyle.setBorderBottom(BorderStyle.NONE);
            }
            default -> {}
        }
        return cellStyle;
    }

    /**
     * 将HSSFWorkbook文件写入到响应输出流中，供前端下载
     * @param workbook 文件对象
     * @param fileName 文件名
     * @param response HttpServletResponse响应
     * @throws IOException IO异常
     */
    public static void writeToResponse(XSSFWorkbook workbook, String fileName, HttpServletResponse response) throws IOException{
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + processFileName(fileName));
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setCharacterEncoding("utf-8");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            workbook.write(bos);
            byte[] bytes = bos.toByteArray();
            OutputStream outData = response.getOutputStream();
            outData.write(bytes);
            outData.flush();
        } catch (IOException e) {
            log.error("FileUtil writeToResponse workbook写入响应失败-----> " + e);
            throw e;
        }
    }

    /**
     * 对要下载的文件的名称进行编码，防止中文乱码问题。
     *
     * @param fileName 文件名
     * @return String
     */
    public static String processFileName(String fileName) throws IOException {
        String codedFilename;
        String prefix = fileName.lastIndexOf(".") != -1 ? fileName.substring(0, fileName.lastIndexOf(".")) : fileName;
        String extension = fileName.lastIndexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".")) : "";
        String name = java.net.URLEncoder.encode(prefix, StandardCharsets.UTF_8);
        if (name.lastIndexOf("%0A") != -1) {
            name = name.substring(0, name.length() - 3);
        }
        int limit = 150 - extension.length();
        if (name.length() > limit) {
            name = java.net.URLEncoder.encode(prefix.substring(0, Math.min(prefix.length(), limit / 9)), StandardCharsets.UTF_8);
            if (name.lastIndexOf("%0A") != -1) {
                name = name.substring(0, name.length() - 3);
            }
        }
        name = name.replaceAll("[+]", "%20");
        codedFilename = name + extension;
        log.info("FileUtil processFileName codedFilename-----> " + codedFilename);
        return codedFilename;
    }
}
