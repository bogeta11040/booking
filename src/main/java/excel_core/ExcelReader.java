package excel_core;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {

    public Map<String,String> getRowDataByID(String file, String sheetName,String ID,String dataNum,boolean suffix) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet(sheetName);

        int lastColumnNum = sheet.getRow(1).getLastCellNum();
        int lastRowNum = sheet.getLastRowNum();

        Map<String,String> data = new HashMap<>();

        for(int j = 0; j <= lastRowNum; j++){
            if(sheet.getRow(j).getCell(0).getStringCellValue().equalsIgnoreCase(ID)){
                for(int i = 0; i<lastColumnNum;i++){
                    String key,value;

                    try {
                        key = sheet.getRow(1).getCell(i).getStringCellValue();
                    }catch (Exception e) {
                        key = String.valueOf(sheet.getRow(1).getCell(i).getNumericCellValue());
                    }

                    try {
                        value = sheet.getRow(j).getCell(i).getStringCellValue();
                    }catch (Exception e){
                        value = String.valueOf(sheet.getRow(j).getCell(i).getNumericCellValue());
                    }

                    if(suffix){
                        data.put(key+"_"+dataNum, value);
                    }else {
                        data.put(key, value);
                    }
                }
            }
        }

        return data;
    }

    public Map<String,String> mergeData(Map<String,String> data,String file,String sheet,String key) throws IOException {
        String[] object = data.get(key).split(";");

        for (int i = 0; i<object.length;i++){
            data.putAll(new ExcelReader().getRowDataByID(file,sheet,object[i],String.valueOf(i+1),true));
        }

        return data;
    }
}