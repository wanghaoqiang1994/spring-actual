package com.haoqiangwang.plat.service;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

@Service
public class HandlerOneService {

    private static final Logger logger = LoggerFactory.getLogger(HandlerOneService.class);

    public static String outputFile = "D:\\testOne.xls";


    /**
     * 处理背景颜色和字体
     *
     * @param saveFile
     * @param suffixName
     */
    public void handlerOne(File saveFile, String suffixName) {
        logger.info("进行业务处理...");
        try {
            FileInputStream fileInputStream = new FileInputStream(saveFile);
            Workbook workbook = null;
            if (suffixName.equals(".xlsx")) {
                logger.info("文件为.xlsx结尾的...");
                workbook = operateXSSFWork(fileInputStream);
            }
            if (suffixName.equals(".xls")) {
                logger.info("文件为.xls结尾的...");
                workbook = operateHSSFWork(fileInputStream);
            }

            //将工作簿中的内容写入文件
            logger.info("创建新文件为：{}", saveFile.getPath());
            FileOutputStream fileOutputStream = new FileOutputStream(new File(saveFile.getPath()));
            workbook.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            workbook.close();

        } catch (Exception e) {

            logger.error("业务处理出现异常" + e);
        }
    }

    /**
     * 判断是否为数字类型
     *
     * @param cellVal
     * @return
     */
    public boolean isNumber(String cellVal) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(cellVal).matches();
    }

    /**
     * 得到加粗，填充黄色背景
     *
     * @param hssfWorkbook
     * @return
     */
    public HSSFCellStyle getStyleHssf(HSSFWorkbook hssfWorkbook) {
        HSSFFont font = hssfWorkbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示

        HSSFCellStyle hssfCellStyle = hssfWorkbook.createCellStyle();
        hssfCellStyle.setFont(font);
        hssfCellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
        hssfCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        return hssfCellStyle;
    }

    /**
     * 得到加粗，填充黄色背景
     *
     * @param xssfWorkbook
     * @return
     */
    public XSSFCellStyle getStyleXssf(XSSFWorkbook xssfWorkbook, boolean flag) {
        XSSFFont font = xssfWorkbook.createFont();
        if(flag) {
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        }

        XSSFCellStyle xssfCellStyle = xssfWorkbook.createCellStyle();
        xssfCellStyle.setFont(font);
        xssfCellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
        xssfCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        return xssfCellStyle;
    }

    /**
     * 处理xSSF文件 .xlsx结尾
     *
     * @param fileInputStream
     * @throws Exception
     */
    public Workbook operateXSSFWork(FileInputStream fileInputStream) throws Exception {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
        //获取sheet页数
        int sumSheet = xssfWorkbook.getNumberOfSheets();
        logger.info("此文件共有sheet页数为：{}", sumSheet);
        for (int i = 0; i < sumSheet; i++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(i);
            //获得总列数
            int coloumNum = xssfSheet.getRow(0).getPhysicalNumberOfCells();
            //获得总行数
            int rowNum = xssfSheet.getPhysicalNumberOfRows();
            logger.info("总共行数为：{}", rowNum);
            for (int j = 0; j < rowNum; j++) {
                //获取每行
                XSSFRow xssfRow = xssfSheet.getRow(j);
                //获取到每行第三列的值
                XSSFCell xssfCell = xssfRow.getCell(2);
                //获取单元格的值
                String cellVal = getCellValue(xssfCell);
                logger.info("单元格的值为：{}", cellVal);
                //满足此条件
                if (!StringUtils.isEmpty(cellVal) && cellVal.length() == 4 && isNumber(cellVal)) {
                    //进行操作
                    logger.info("进行格式操作...");
                    //xssfRow.setRowStyle(getStyleXssf(xssfWorkbook));
                    int cells = xssfRow.getPhysicalNumberOfCells();
                    for(int m=0; m<cells; m++){
                        XSSFCell tempCell = xssfRow.getCell(m);
                        if(m == 2) {
                            tempCell.setCellStyle(getStyleXssf(xssfWorkbook, true));
                        }else{
                            tempCell.setCellStyle(getStyleXssf(xssfWorkbook, false));
                        }
                    }
                }
            }
        }
        return xssfWorkbook;
    }

    /**
     * 处理HSSF文件 .xls结尾
     *
     * @param fileInputStream
     * @throws Exception
     */
    public Workbook operateHSSFWork(FileInputStream fileInputStream) throws Exception {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fileInputStream);
        //获取sheet页数
        int sumSheet = hssfWorkbook.getNumberOfSheets();
        logger.info("此文件共有sheet页数为：{}", sumSheet);
        for (int i = 0; i < sumSheet; i++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(i);
            //获得总列数
            int coloumNum = hssfSheet.getRow(0).getPhysicalNumberOfCells();
            //获得总行数
            int rowNum = hssfSheet.getPhysicalNumberOfRows();
            logger.info("总共行数为：{}", rowNum);
            for (int j = 0; j < rowNum; j++) {
                //获取到第三列
                HSSFCell hssfCell = hssfSheet.getRow(j).getCell(2);
                //获取单元格的值
                String cellVal = hssfCell.getStringCellValue();
                logger.info("单元格的值为：{}", cellVal);
                //满足此条件
                if (!StringUtils.isEmpty(cellVal) && cellVal.length() == 4 && isNumber(cellVal)) {
                    //进行操作
                    logger.info("进行格式操作...");
                    hssfSheet.getRow(j).setRowStyle(getStyleHssf(hssfWorkbook));
                    hssfCell.setCellStyle(getStyleHssf(hssfWorkbook));
                }
            }
        }

        return hssfWorkbook;
    }

    /**
     * 获取单元格值
     *
     * @param cell
     * @return
     */
    public String getCellValue(XSSFCell cell) {
        String value = "";
        // 以下是判断数据的类型
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC:
                value = cell.getNumericCellValue() + "";
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    if (date != null) {
                        value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                    } else {
                        value = "";
                    }
                } else {
                    value = new DecimalFormat("0").format(cell.getNumericCellValue());
                }
                break;
            case HSSFCell.CELL_TYPE_STRING: // 字符串
                value = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                value = cell.getBooleanCellValue() + "";
                break;
            case HSSFCell.CELL_TYPE_FORMULA: // 公式
                value = cell.getCellFormula() + "";
                break;
            case HSSFCell.CELL_TYPE_BLANK: // 空值
                value = "";
                break;
            case HSSFCell.CELL_TYPE_ERROR: // 故障
                value = "非法字符";
                break;
            default:
                value = "未知类型";
                break;
        }
        return value;
    }

    public void util() {

        try {
            FileInputStream fileInputStream = new FileInputStream("saveFile");
            Workbook workbook = WorkbookFactory.create(fileInputStream);

            //是操作Excel2003以前（包括2003）的版本，扩展名是.xls
            HSSFWorkbook hssfWorkbook = (HSSFWorkbook) WorkbookFactory.create(fileInputStream);

            //是操作Excel2007的版本，扩展名是.xlsx
            XSSFWorkbook xssfWorkbook = (XSSFWorkbook) WorkbookFactory.create(fileInputStream);

            //当数据量超出65536条后
            SXSSFWorkbook sxssfWorkbook = (SXSSFWorkbook) WorkbookFactory.create(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        HSSFCellStyle setBorder = wb.createCellStyle();

        //一、设置背景色：
        setBorder.setFillForegroundColor((short) 13);// 设置背景色
        setBorder.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        //二、设置边框:
        setBorder.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        setBorder.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        setBorder.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        setBorder.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        //三、设置居中:
        setBorder.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中

        //四、设置字体:
        HSSFFont font = wb.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 16);//设置字体大小
        HSSFFont font2 = wb.createFont();
        font2.setFontName("仿宋_GB2312");
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        font2.setFontHeightInPoints((short) 12);
        setBorder.setFont(font);//选择需要用到的字体格式

        //五、设置列宽:
        sheet.setColumnWidth(0, 3766); //第一个参数代表列id(从0开始),第2个参数代表宽度值

        //六、设置自动换行:
        setBorder.setWrapText(true);//设置自动换行

        //七、合并单元格:
        //参数1：行号 参数2：起始列号 参数3：行号 参数4：终止列号
        CellRangeAddress region2 = new CellRangeAddress(0, 0, (short) 0, (short) 11);

        //但应注意两个构造方法的参数不是一样的，具体使用哪个取决于POI的不同版本。
        sheet.addMergedRegion(region2);

        //目前用过的就这么多，后续有新的会继续添加。

        //八、加边框
        /*HSSFCellStyle cellStyle = wookBook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setBorderBottom(HSSFCellStyle.BorderBORDER_MEDIUM);
        cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        cellStyle.setTopBorderColor(HSSFColor.BLACK.index);*/

    }

    public static void main(String[] args) {
        try {
            // 创建新的Excel 工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();

            // 设置字体
            HSSFFont font = workbook.createFont();
            // font.setColor(HSSFFont.COLOR_RED);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short) 14);

            // HSSFFont font2 = workbook.createFont();
            // font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            // font.setFontHeightInPoints((short)14);

            // 设置样式
            HSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFont(font);
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            //cellStyle.setFillBackgroundColor(HSSFColor.YELLOW.index);
            cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            // HSSFCellStyle cellStyle2= workbook.createCellStyle();
            // cellStyle.setFont(font2);
            // cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            // 在Excel工作簿中建一工作表，其名为缺省值
            // 如要新建一名为"月报表"的工作表，其语句为：
            HSSFSheet sheet = workbook.createSheet("月报表");
            CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0,
                    11);
            sheet.addMergedRegion(cellRangeAddress);

            //第一行
            // 在索引0的位置创建行（最顶端的行）
            HSSFRow row = sheet.createRow(0);
            // 在索引0的位置创建单元格（左上端）
            HSSFCell cell = row.createCell(0);
            // 定义单元格为字符串类型
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellStyle(cellStyle);
            // 在单元格中输入一些内容
            cell.setCellValue(new HSSFRichTextString("北京亿卡联科技发展有限公司小区门禁维修月报表"));

            //第二行
            cellRangeAddress = new CellRangeAddress(1, 1, 3, 6);
            sheet.addMergedRegion(cellRangeAddress);
            row = sheet.createRow(1);
            HSSFCell datecell = row.createCell(3);
            datecell.setCellType(HSSFCell.CELL_TYPE_STRING);
            datecell.setCellStyle(cellStyle);
            datecell.setCellValue("时间间隔xxxxx");

            cellRangeAddress = new CellRangeAddress(1, 1, 9,
                    10);
            sheet.addMergedRegion(cellRangeAddress);
            row.createCell(9).setCellValue("单位：元");

            //第三行
            row = sheet.createRow(2);
            row.createCell(0).setCellValue("一、");
            row.createCell(1).setCellValue("基本资料");

            //第4行
            row = sheet.createRow(3);
            row.createCell(1).setCellValue("小区名称：");
            cellRangeAddress = new CellRangeAddress(3, 3, 2, 11);
            sheet.addMergedRegion(cellRangeAddress);
            row.createCell(2).setCellValue("xxxxx");

            //第5行
            row = sheet.createRow(4);
            row.createCell(1).setCellValue("座落地点：");
            cellRangeAddress = new CellRangeAddress(4, 4, 2, 11);
            sheet.addMergedRegion(cellRangeAddress);
            row.createCell(2).setCellValue("xxxxx");

            //填充数据
            /*for (int i = 0; i < 10; i++) {
                row = sheet.createRow(9 + i + 1);
                row.createCell(0).setCellValue("日期");
                row.createCell(1).setCellValue("维修事项");
                row.createCell(2).setCellValue("材料清单");
                row.createCell(3).setCellValue("数量");
                row.createCell(4).setCellValue("单价");
                row.createCell(5).setCellValue("材料金额");

                row.createCell(7).setCellValue("日期");
                row.createCell(8).setCellValue("技工");
                row.createCell(9).setCellValue("工时数");
                row.createCell(10).setCellValue("单价");
                row.createCell(11).setCellValue("工时金额");
            }*/

            //第n+10行
            row = sheet.createRow(8);
            //cellRangeAddress=new CellRangeAddress(19,19,0,4);
            //sheet.addMergedRegion(cellRangeAddress);
            row.createCell(0).setCellValue("累计:");
            row.createCell(1).setCellValue("xxx");
            row.createCell(7).setCellValue("累计:");
            row.createCell(8).setCellValue("xxx");


            // 新建一输出文件流
            FileOutputStream fOut = new FileOutputStream(outputFile);
            // 把相应的Excel 工作簿存盘
            workbook.write(fOut);
            fOut.flush();
            // 操作结束，关闭文件
            fOut.close();
            System.out.println("文件生成...");
        } catch (Exception e) {
            System.out.println("已运行 xlCreate() : " + e);
        }
    }

    private void cteateCell(HSSFWorkbook wb, HSSFRow row, short col, String val) {
        HSSFCell cell = row.createCell(col);
        // cell.setEncoding(HSSFCell.ENCODING_UTF_16);
        cell.setCellValue(val);
        HSSFCellStyle cellstyle = wb.createCellStyle();
        cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
        cell.setCellStyle(cellstyle);
    }
}
