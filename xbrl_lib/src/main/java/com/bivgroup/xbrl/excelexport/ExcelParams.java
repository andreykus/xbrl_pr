package com.bivgroup.xbrl.excelexport;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * Created by bush on 20.03.2017.
 */
public class ExcelParams {
    String file1="property1.xlsx";
    String file2="property2.xlsx";
    /**
     * creates an {@link HSSFWorkbook} with the specified OS filename.
     */
    private static HSSFWorkbook readFile(String filename) throws IOException {
        FileInputStream fis = new FileInputStream(filename);
        try {
            return new HSSFWorkbook(fis);		// NOSONAR - should not be closed here
        } finally {
            fis.close();
        }
    }

    private void get(HSSFWorkbook wb){

            System.out.println("Data dump:\n");

            for (int k = 0; k < wb.getNumberOfSheets(); k++) {
                HSSFSheet sheet = wb.getSheetAt(k);
                int rows = sheet.getPhysicalNumberOfRows();
                System.out.println("Sheet " + k + " \"" + wb.getSheetName(k) + "\" has " + rows
                        + " row(s).");
                for (int r = 0; r < rows; r++) {
                    HSSFRow row = sheet.getRow(r);
                    if (row == null) {
                        continue;
                    }

                    System.out.println("\nROW " + row.getRowNum() + " has " + row.getPhysicalNumberOfCells() + " cell(s).");
                    for (int c = 0; c < row.getLastCellNum(); c++) {
                        HSSFCell cell = row.getCell(c);
                        String value;

                        if(cell != null) {
//                            switch (cell.getCellType()TypeEnum()) {
//
//                                case FORMULA:
//                                    value = "FORMULA value=" + cell.getCellFormula();
//                                    break;
//
//                                case NUMERIC:
//                                    value = "NUMERIC value=" + cell.getNumericCellValue();
//                                    break;
//
//                                case STRING:
//                                    value = "STRING value=" + cell.getStringCellValue();
//                                    break;
//
//                                case BLANK:
//                                    value = "<BLANK>";
//                                    break;
//
//                                case BOOLEAN:
//                                    value = "BOOLEAN value-" + cell.getBooleanCellValue();
//                                    break;
//
//                                case ERROR:
//                                    value = "ERROR value=" + cell.getErrorCellValue();
//                                    break;
//
//                                default:
//                                    value = "UNKNOWN value of type " + cell.getCellTypeEnum();
//                            }
                            System.out.println("CELL col=" + cell.getColumnIndex() + " VALUE="
                                    + "");//value);
                        }
                    }
                }
            }

    }

}
