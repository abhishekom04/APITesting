package api.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadWriteExcelData {

    String filePath = System.getProperty("user.dir")+ File.separator+"TestData"+File.separator+"NewData.xlsx";

    public String readDataFromExcel() {
        String data = "";
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);

            int rows = sheet.getLastRowNum();
            int cols = sheet.getRow(0).getLastCellNum();


            for (int i = 0; i < rows; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < cols; j++) {
                    data=row.getCell(j).getStringCellValue();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @DataProvider(name = "testData")
    public Object[][] dataParameterization() throws IOException {

            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);

            int rows = sheet.getLastRowNum();
            int cols = sheet.getRow(0).getLastCellNum();

            DataFormatter formatter = new DataFormatter();
            String[][] testData= new String[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    testData[i + 1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
                }
            }
            workbook.close();
        return testData;
    }

    @Test
    public void writeDataToExcel1() throws IOException {

        File file = new File(filePath);
        Workbook workbook=new XSSFWorkbook();  // If file does not exist, create new workbook and sheet
        Sheet sheet= workbook.createSheet("Sheet1");;

        // Example: write multiple test data
        String[] results = {"PASS", "FAIL", "PASS", "PASS"};

        for (int i = 0; i < results.length; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                row = sheet.createRow(i);
            }
            Cell cell = row.createCell(0); // write in first column
            cell.setCellValue(results[i]);
        }

        // Save changes (creates file if not present)
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
        workbook.close();

        System.out.println("Test data written successfully!");
    }

    @Test
    public void writeDataToExcel2() throws IOException {

        File file = new File(filePath);
        Workbook workbook;
        Sheet sheet;

        if (file.exists()) {
            // If file exists, open it
            FileInputStream fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet("Sheet1");
            if (sheet == null) {
                sheet = workbook.createSheet("Sheet1");
            }
            fis.close();
        } else {
            // If file does not exist, create new workbook and sheet
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Sheet1");
        }

        // Example: write multiple test data
        String[] results = {"PASS", "FAIL", "PASS", "PASS"};

        for (int i = 0; i < results.length; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                row = sheet.createRow(i);
            }
            Cell cell = row.createCell(0); // write in first column
            cell.setCellValue(results[i]);
        }

        // Save changes (creates file if not present)
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
        workbook.close();

        System.out.println("Test data written successfully!");
    }

}
