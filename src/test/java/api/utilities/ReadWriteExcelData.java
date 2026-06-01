package api.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;

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
    public void writeDataToExcel() throws IOException {

        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        String[] results = {"PASS", "FAIL", "PASS", "PASS"}; // test outcomes

        for (int i = 0; i < results.length; i++) {
            Row row = sheet.getRow(i + 1); // skip header row
            if (row == null) {
                row = sheet.createRow(i + 1);
            }
            Cell cell = row.createCell(0); // write in 3rd column
            cell.setCellValue(results[i]);
        }

        // Save changes
        fis.close();
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
        workbook.close();

        System.out.println("Data written successfully!");

    }

}
