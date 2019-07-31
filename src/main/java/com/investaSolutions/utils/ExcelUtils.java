package com.investaSolutions.utils;

import java.io.FileInputStream;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelUtils {

	static HashMap<String, String> HM;
	static HashMap<String, HashMap<String, String>> HMM;
	
	
	private static Workbook GetWorkBook(String path) throws Exception{
		Workbook wObj=null;
		FileInputStream fileInputStream = new FileInputStream(path);
		if(path.endsWith("xlsx")){
			wObj=new XSSFWorkbook(fileInputStream);
		}else{
			wObj=new HSSFWorkbook(fileInputStream);
		}
		return wObj;
	}
	
	public static HashMap<String, HashMap<String, String>> GetData(String path,String sheetName) throws Exception{
		HM=new HashMap<String, String>();
		HMM=new HashMap<String, HashMap<String, String>>();
		Workbook wObj=GetWorkBook(path);
		Sheet sheetObj=wObj.getSheet(sheetName);
		int rowCount=sheetObj.getLastRowNum();
		for(int i=1;i<=rowCount;i++){
			Row rowObj=sheetObj.getRow(i);
			String tcName=rowObj.getCell(0).getStringCellValue();
			int cellCount=rowObj.getLastCellNum()-1;
			for(int j=1;j<=cellCount;j+=2){
				Cell cellObj=rowObj.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				String key=cellObj.getStringCellValue();
				Cell cellObjVal=rowObj.getCell(j+1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				String value=cellObjVal.getStringCellValue();
				HM.put(key, value);
			}
			HMM.put(tcName, HM);
		}
		return HMM;
	}	
}
