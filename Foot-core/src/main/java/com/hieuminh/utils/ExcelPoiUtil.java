package com.hieuminh.utils;



import com.hieuminh.constant.SystemConstant;
import com.hieuminh.dto.utils.ReportDTO;
import com.hieuminh.dto.utils.ReportRevenueDTO;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



@Component
public class ExcelPoiUtil {

    private static final String  path = SystemConstant.BASE_DIR_NOT_SERVER;
    public static final int COLUMN_INDEX_ID= 0;
    public static final int COLUMN_INDEX_PRODCUTNAME= 1;
    public static final int COLUMN_INDEX_STORENAME= 2;
    public static final int COLUMN_INDEX_TIME= 3;
    public static final int COLUMN_INDEX_TOTAL= 4;

    public static final int COLUMN_INDEX_TYPENAME= 0;
    public static final int COLUMN_INDEX_TYPE_COUNT= 1;
    public static final int COLUMN_INDEX_MONEY= 2;
    public static final int COLUMN_INDEX_PERCEN= 3;

    private static CellStyle cellStyleFormatNumber = null;

    public static Workbook getWordBook(String fileName, String fileLocation) throws IOException {
        FileInputStream fileExcel = new FileInputStream(fileLocation);
        Workbook workbook =null;

        if (fileName.endsWith("xls")){
            workbook = new HSSFWorkbook(fileExcel);
        }else if (fileName.endsWith("xlsx")){
            workbook = new XSSFWorkbook(fileExcel);
        }
        return workbook;
    }

    public static String getCellValue(Cell cell){
        String cellValue="";
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_STRING:
                cellValue = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = Boolean.toString(cell.getBooleanCellValue());
                break;
            case  Cell.CELL_TYPE_NUMERIC:
                cellValue = NumberToTextConverter.toText(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                switch (cell.getCellType()){
                    case Cell.CELL_TYPE_STRING:
                        cellValue =  cell.getStringCellValue();
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        cellValue = NumberToTextConverter.toText(cell.getNumericCellValue());
                        break;
                }
        }
        return  cellValue;
    }


    public static void ExportExcel(List<ReportDTO> reportDTOS,List<ReportRevenueDTO> reportRevenueDTOS ) throws IOException {
        String time =new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        String path = SystemConstant.BASE_DIR_NOT_SERVER+"/report"+"/store"+
                reportDTOS.get(0).getStoreDTO().getIdstore()+"_"+time.replace("_","")+"_"+(reportDTOS.get(0).getDateTime().replace("/",""))+".xlsx";

        File file = new File(StringUtils.substringBeforeLast((reportDTOS.get(0).getDateTime().replace("/",""))+".xlsx", "/"));
        if (!Files.exists(Paths.get(path))) {
            file.mkdirs();
        }
        Workbook workbook = getWorkReport(path);

        // Create sheet
        Sheet sheet = workbook.createSheet("REPORT"); // Create sheet with sheet name

        int rowIndex = 0;
        writeHeaderType(sheet, rowIndex);
        rowIndex++;
        for (ReportRevenueDTO rr : reportRevenueDTOS) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            // Write data on row
            writeExcelType(rr, row);
            rowIndex++;
        }
        writeFooter(sheet, rowIndex,reportRevenueDTOS);
        rowIndex++;

        rowIndex+=3;


        // Write header
        writeHeader(sheet, rowIndex);
        // Write data
        rowIndex++;
        for (ReportDTO r : reportDTOS) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            // Write data on row
            writeExcel(r, row);
            rowIndex++;
        }

        // Write footer
       writeFooterTable2(sheet, rowIndex,reportDTOS);

        // Auto resize column witdth
        int numberOfColumn = sheet.getRow(1).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        // Create file excel
        createOutputFile(workbook, path);
        System.out.println("Done!!!");

    }
    private static Workbook getWorkReport(String excelFilePath) throws IOException {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    private static void writeHeaderType(Sheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);

        // Create row
        Row row = sheet.createRow(rowIndex);

        // Create cells
        Cell cell = row.createCell(COLUMN_INDEX_TYPENAME);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("TypeProduct");

        cell = row.createCell(COLUMN_INDEX_TYPE_COUNT);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Count");

        cell = row.createCell(COLUMN_INDEX_MONEY);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("TotalMoney(VND)");

        cell = row.createCell(COLUMN_INDEX_PERCEN);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Take(%)");

    }

    // Write header with format
    private static void writeHeader(Sheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);

        // Create row
        Row row = sheet.createRow(rowIndex);

        // Create cells
        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Id");

        cell = row.createCell(COLUMN_INDEX_PRODCUTNAME);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("ProductName");

        cell = row.createCell(COLUMN_INDEX_STORENAME);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("StoreName");

        cell = row.createCell(COLUMN_INDEX_TIME);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Time");

        cell = row.createCell(COLUMN_INDEX_TOTAL);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Total");
    }
    private static void writeExcelType(ReportRevenueDTO rr, Row row) {

            // Format number
            short format = (short)BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");

            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);


        Cell cell = row.createCell(COLUMN_INDEX_TYPENAME);
        cell.setCellValue(rr.getTypeDTO().getTypeName());


        cell = row.createCell(COLUMN_INDEX_TYPE_COUNT);
        cell.setCellValue(rr.getCountType());

        cell = row.createCell(COLUMN_INDEX_MONEY);
        cell.setCellValue(rr.getSumMoneyType());
        cell.setCellStyle(cellStyleFormatNumber);

        cell = row.createCell(COLUMN_INDEX_PERCEN);
        cell.setCellValue((double) Math.round(rr.getPercen() * 1000) / 1000);



    }

    // Write data
    private static void writeExcel(ReportDTO reportDTO, Row row) {

            // Format number
            short format = (short)BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");

            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);


        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellValue(reportDTO.getProductDTO().getIdProduct());


        cell = row.createCell(COLUMN_INDEX_PRODCUTNAME);
        cell.setCellValue(reportDTO.getProductDTO().getNameProduct());

        cell = row.createCell(COLUMN_INDEX_STORENAME);
        cell.setCellValue(reportDTO.getStoreDTO().getName());


        cell = row.createCell(COLUMN_INDEX_TIME);
        cell.setCellValue(reportDTO.getDateTime());

        // Create cell formula
        // totalMoney = price * quantity
        cell = row.createCell(COLUMN_INDEX_TOTAL);
        cell.setCellValue(reportDTO.getCountProduct());

    }

    // Create CellStyle for header
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    // Write footer
    private static void writeFooter(Sheet sheet, int rowIndex,List<ReportRevenueDTO> reportRevenueDTOS ) {

        // Create row
        Row row = sheet.createRow(rowIndex);

            // Format number
            short format = (short)BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");

            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);


        Integer col = reportRevenueDTOS.size()+1;
        String formulaC =   "SUM(C2:C"+col+")";
        String formulaB =   "SUM(B2:B"+col+")";

        Cell cell1 = row.createCell(COLUMN_INDEX_MONEY, CellType.FORMULA);
        cell1.setCellFormula(formulaC);
        cell1.setCellStyle(cellStyleFormatNumber);

        Cell cell2 = row.createCell(COLUMN_INDEX_TYPE_COUNT, CellType.FORMULA);
        cell2.setCellFormula(formulaB);



    }
    private static void writeFooterTable2(Sheet sheet, int rowIndex,List<ReportDTO> reportDTOS ) {
        // Create row
        Row row = sheet.createRow(rowIndex);




        Integer col = reportDTOS.size()+1;

        String formulaE =   "SUM(E2:E"+col+")";

        Cell cell2 = row.createCell(COLUMN_INDEX_TOTAL, CellType.FORMULA);
        cell2.setCellFormula(formulaE);
    }

    // Auto resize column width
    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    // Create output file
    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }


}
