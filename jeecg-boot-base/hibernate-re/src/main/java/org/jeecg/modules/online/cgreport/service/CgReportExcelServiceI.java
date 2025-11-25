//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.cgreport.service;

import java.util.Collection;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface CgReportExcelServiceI {
    HSSFWorkbook exportExcel(String title, Collection<?> titleSet, Collection<?> dataSet);
}
