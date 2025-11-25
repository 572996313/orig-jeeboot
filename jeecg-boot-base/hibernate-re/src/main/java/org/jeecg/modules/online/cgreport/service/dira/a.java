//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgreport.service.dira;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import lombok.Generated;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.jeecg.modules.online.cgreport.service.CgReportExcelServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("cgReportExcelService")
public class a implements CgReportExcelServiceI {
    @Generated
    private static final Logger a = LoggerFactory.getLogger(a.class);

    public HSSFWorkbook exportExcel(String title, Collection<?> titleSet, Collection<?> dataSet) {
        HSSFWorkbook workbook = null;

        try {
            if (titleSet == null || titleSet.size() == 0) {
                throw new Exception("读取表头失败！");
            }

            if (title == null) {
                title = "";
            }

            workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet(title);
            int rindex = 0;
            int cindex = 0;
            Row row = sheet.createRow(rindex);
            row.setHeight((short)450);
            CellStyle titleStyle = this.a(workbook);
            List titleList = (List)titleSet;
            Iterator itData = dataSet.iterator();

            for(Object titleMObj : titleList) {
                Map titleM = (Map)titleMObj;
                String titleContent = (String)titleM.get("field_txt");
                Cell cell = row.createCell(cindex);
                RichTextString text = new HSSFRichTextString(titleContent);
                cell.setCellValue(text);
                cell.setCellStyle(titleStyle);
                ++cindex;
            }

            HSSFCellStyle bodyStyle = this.c(workbook);

            while(itData.hasNext()) {
                cindex = 0;
                ++rindex;
                row = sheet.createRow(rindex);
                Map dataM = (Map)itData.next();

                for(Object titleMObj : titleList) {
                    Map titleM = (Map)titleMObj;
                    String field = (String)titleM.get("field_name");
                    String content = dataM.get(field) == null ? "" : dataM.get(field).toString();
                    Cell cell = row.createCell(cindex);
                    RichTextString text = new HSSFRichTextString(content);
                    cell.setCellStyle(bodyStyle);
                    cell.setCellValue(text);
                    ++cindex;
                }
            }

            for(int i = 0; i < titleList.size(); ++i) {
                sheet.autoSizeColumn(i);
            }
        } catch (Exception e) {
            a.error(e.getMessage(), e);
        }

        return workbook;
    }

    private HSSFCellStyle a(HSSFWorkbook workbook) {
        HSSFCellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setBorderLeft(BorderStyle.THIN);
        titleStyle.setBorderRight(BorderStyle.THIN);
        titleStyle.setBorderBottom(BorderStyle.THIN);
        titleStyle.setBorderTop(BorderStyle.THIN);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return titleStyle;
    }

    private void a(int rows, int columns, HSSFWorkbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);
        CellStyle cellStyle = this.c(workbook);

        for(int i = 1; i <= rows; ++i) {
            Row row = sheet.createRow(i);

            for(int j = 0; j < columns; ++j) {
                row.createCell(j).setCellStyle(cellStyle);
            }
        }

    }

    private HSSFCellStyle b(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

    private HSSFCellStyle c(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        return style;
    }
}
