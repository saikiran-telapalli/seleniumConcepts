package HandlingExcelAndWeBtables;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelOperations{



	public String readExcel(String filePath,String sheetName,int rowNum,int colNum) throws IOException{

		// load file
		FileInputStream fis = new FileInputStream(filePath); 
		// Load workbook
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		// Load sheet
		Sheet sheet = wb.getSheet(sheetName);
		// getRow() specify which row we want to read  and getCell() specify which column to read. 
		// getStringCellValue() specify that we are reading String data.
		String excelValue= sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
		System.out.println(excelValue);
		System.out.println("Read data of given cell: "+sheet.getRow(1).getCell(2).getStringCellValue());

		//sh.getLastRowNum() to get last row number
		System.out.println("No. of rows : " + sheet.getLastRowNum());
		//sh.getRow(0).getLastCellNum() to get last coloumn number
		System.out.println("No of col: "+sheet.getRow(0).getLastCellNum());
		//sh.getFirstRowNum() to get first row number
		System.out.println("getFirstRowNum: "+sheet.getFirstRowNum());

		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		System.out.println("rowCount: "+rowCount);


		for (int i = 0; i < rowCount+1; i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				System.out.print(row.getCell(j).getStringCellValue()+"|| ");
			}
			System.out.println();
		}

		// here we need to specify where you want to save file
		FileOutputStream fout = new FileOutputStream(filePath);
		// here createCell will create column
		// and setCellvalue will set the value
		sheet.getRow(0).createCell(3).setCellValue("Age");
		sheet.getRow(1).createCell(3).setCellValue("25");
		// finally write content 
		wb.write(fout);
		// close the file
		fout.close();

		return excelValue;


	}

//	public static void main(String[] args) throws IOException {
//		ExcelOperations ReadExcel = new ExcelOperations();
//		String filePath = "/Users/saikiran/Downloads/MainSeleniumAutomationFW-master/src/main/resources/excelFiles/login.xlsx";
//		ReadExcel.readExcel(filePath,"login",1,1);
//	}

}
