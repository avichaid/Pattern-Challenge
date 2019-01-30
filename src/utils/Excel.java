package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	
	/**
	 * Read the Excel data and convert to a Matrix
	 * @param - file path, sheet name
	 */
	public static Object[][] getTableArray(String filePath, String sheetName){
		Object[][]tabArray = null;

		try{
			FileInputStream ExcelFile = new FileInputStream(filePath);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);

			int ci=1,cj=0;
			int totalRows = ExcelWSheet.getPhysicalNumberOfRows();
			int totalCols = ExcelWSheet.getRow(0).getLastCellNum();
			tabArray = new Object[totalRows-1][totalCols];
			for (int i=0; ci<totalRows;ci++){
				int j=0;
				for (cj=0; cj<totalCols; cj++){
					Object data = getCellData(ci, cj);
					tabArray[i][j] = data;
					j++;
				}
				i++;
			}
		} catch (FileNotFoundException ex){
			System.out.println("Exception! Excel file could not be found!");
		} catch (IOException ex){
			System.out.println("Exception! IO error occured while trying to load excel file!");
		} catch (Exception e) {
			System.out.println("Exception! Error occured while trying to load excel file!");
		}
		return tabArray;
	}

	
	/**
	 * Read the test data from the Excel cell
	 * @param - Row num and Col num
	 */
	private static Object getCellData(int RowNum, int ColNum) throws Exception {
		try {
			String CellData = "";
			double numericCellData;
			long intCellData;
			double doubleCellData;
			Boolean booleanCellData= false;

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			if (Cell.getCellTypeEnum() == CellType.STRING)
				CellData = Cell.getStringCellValue();
			else if (Cell.getCellTypeEnum() == CellType.NUMERIC){
				numericCellData = Cell.getNumericCellValue();
				intCellData = (long)numericCellData;
				doubleCellData = (double)numericCellData;
				if (doubleCellData % 1 == 0)
					CellData = String.valueOf(intCellData);
				else
					CellData = String.valueOf(doubleCellData);
			}
			else if (Cell.getCellTypeEnum() == CellType.BOOLEAN){
				booleanCellData = Cell.getBooleanCellValue();
				CellData = String.valueOf(booleanCellData);
			}
			return CellData;
		} catch (Exception e) {
			return "";
		}
	}
}
