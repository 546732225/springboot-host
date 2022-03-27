package com.example.data.excel;


import com.example.data.annotation.ExcelAttribute;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellBase;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
public class ExcelHandler {

    /**
     * 读取Excel文件的内容
     *
     * @param inputStream excel文件，以InputStream的形式传入
     * @param sheetName   sheet名字
     * @return 以List返回excel中内容
     */
    public static List<Map<String, String>> readExcel(InputStream inputStream, String sheetName) throws IOException {

        //定义工作簿
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        //定义工作表
        XSSFSheet xssfSheet;
        if (StringUtils.isEmpty(sheetName)) {
            // 默认取第一个子表
            xssfSheet = xssfWorkbook.getSheetAt(0);
        } else {
            xssfSheet = xssfWorkbook.getSheet(sheetName);
        }

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        //默认第一行为标题行，index = 0
        XSSFRow titleRow = xssfSheet.getRow(0);
        //循环取每行的数据
        for (int rowIndex = 1; rowIndex < xssfSheet.getPhysicalNumberOfRows(); rowIndex++) {
            XSSFRow xssfRow = xssfSheet.getRow(rowIndex);
            if (xssfRow == null) {
                continue;
            }
            Map<String, String> map = new LinkedHashMap<>();
            //循环取每个单元格(cell)的数据
            for (int cellIndex = 0; cellIndex < xssfRow.getPhysicalNumberOfCells(); cellIndex++) {
                XSSFCell titleCell = titleRow.getCell(cellIndex);
                XSSFCell xssfCell = xssfRow.getCell(cellIndex);
                map.put(getString(titleCell) + "", getString(xssfCell) + "");
            }
            list.add(map);
        }

        return list;
    }

    public static <T> List<T> readExcel(Class<T> clazz, InputStream inputStream) throws Exception {
        return doReadExcel("", clazz, inputStream);
    }

    public static <T> List<T> doReadExcel(String sheetName, Class<T> clazz, InputStream inputStream) throws Exception {
        //获取属性
        List<Field> fields = Arrays.stream(clazz.getDeclaredFields())
                .filter(item -> item.getAnnotation(ExcelAttribute.class) != null)
                .collect(Collectors.toList());

        StopWatch sw = new StopWatch("ReadExcel");
        sw.start("读取Excel文件内容");
        //使用缓冲流 提高效率
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        //定义工作簿
        SXSSFWorkbook xssfWorkbook = new SXSSFWorkbook(new XSSFWorkbook(bufferedInputStream));
        XSSFSheet sheet;
        if (StringUtils.isEmpty(sheetName)) {
            // 默认取第一个子表
            sheet = xssfWorkbook.getXSSFWorkbook().getSheetAt(0);
        } else {
            sheet = xssfWorkbook.getXSSFWorkbook().getSheet(sheetName);
        }

        sw.stop();
        sw.start("解析文件内容");
        List<T> list = new ArrayList<>();
        //循环取每行的数据
        int totalRows = sheet.getPhysicalNumberOfRows();
        for (int index = 1; index < totalRows; index++) {
            XSSFRow xssfRow = sheet.getRow(index);
            if (xssfRow == null) {
                continue;
            }
            T entity = clazz.getDeclaredConstructor().newInstance();
            BeanWrapper beanWrapper = new BeanWrapperImpl(entity);
            //循环取每个单元格(cell)的数据
            for (Field field : fields) {
                field.setAccessible(true);
                ExcelAttribute attribute = field.getAnnotation(ExcelAttribute.class);
                //获取数据
                XSSFCell cell = xssfRow.getCell(getExcelCellIndex(attribute.column()));
                beanWrapper.setPropertyValue(field.getName(), String.valueOf(getString(cell)));
            }
            list.add(entity);
        }
        sw.stop();
        log.info("###读取Excel文件共计耗时：{}毫秒,{}", sw.getTotalTimeMillis(), sw.prettyPrint());
        return list;
    }


    /**
     * 把单元格的内容转为字符串
     *
     * @param xssfCell 单元格
     * @return 字符串
     */
    public static Object getString(CellBase xssfCell) {
        if (xssfCell == null) {
            return "";
        }
        if (xssfCell.getCellType() == CellType.NUMERIC) {
            return xssfCell.getNumericCellValue();
        } else if (xssfCell.getCellType() == CellType.BOOLEAN) {
            return xssfCell.getBooleanCellValue();
        } else if (xssfCell.getCellType() == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(xssfCell)) {
                //是日期类型
                return xssfCell.getDateCellValue();
            } else {
                //是数值类型  解决科学计数法
                BigDecimal bigDecimal = BigDecimal.valueOf(xssfCell.getNumericCellValue());
                return bigDecimal.toPlainString();
            }
        } else if (xssfCell.getCellType() == CellType.STRING) {
            return xssfCell.getStringCellValue();
        } else {
            return xssfCell.getStringCellValue();
        }


    }

    /**
     * 将EXCEL中A,B,C,D,E列映射成0,1,2,3
     *
     * @param col 序列号
     */
    public static int getExcelCellIndex(String col) {
        col = col.toUpperCase();
        // 从-1开始计算,字母重1开始运算。这种总数下来算数正好相同。
        int count = -1;
        char[] cs = col.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            count += (cs[i] - 64) * Math.pow(26, cs.length - 1 - i);
        }
        return count;
    }


}
