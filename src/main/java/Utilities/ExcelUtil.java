package Utilities;

import java.io.FileInputStream;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author VEKANNA
 *
 */
public class ExcelUtil {
	String path;
	public static FileInputStream fis = null;
	public static XSSFWorkbook workbook = null;
	public static XSSFSheet sheet = null;
	public static XSSFRow row = null;
	public static XSSFCell cell = null;
	public static String filename = System.getProperty("user.dir") + "\\src\\TestData\\Data.xlsx";

	/**
	 * Connect to excel. This is a constructor.
	 * 
	 * @param path
	 *            Test data file location
	 */
	public ExcelUtil(String path) {

		this.path = path;
		try {
			fis = new FileInputStream(path);

			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Get data from the test data excel
	 * 
	 * @param testDataWorkSheetName
	 *            - Sheet Name
	 * @param columnName
	 *            - Column name in the test data
	 * @param rowNum
	 *            - Row number in the test data
	 * @return Data from test data excel
	 */
	public static String getDataFromExcel(String testDataWorkSheetName, String columnName, int rowNum) {

		ExcelUtil dataTable = null;
		try {
			dataTable = null;
			dataTable = new ExcelUtil(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataTable.getCellData(testDataWorkSheetName, columnName, rowNum);

	}

	/**
	 * Get the cell data
	 * 
	 * @param sheetName
	 *            Sheet name in the excel
	 * @param colName
	 *            Column name
	 * @param rowNum
	 *            Row number
	 */
	public String getCellData(String sheetName, String colName, int rowNum) {
		try {
			if (rowNum <= 0)
				return "";

			int index = workbook.getSheetIndex(sheetName);
			int col_Num = -1;
			if (index == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				// System.out.println(row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
					col_Num = i;
			}
			if (col_Num == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(col_Num);

			if (cell == null)
				return "";

			if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

				String cellText = String.valueOf(cell.getNumericCellValue());
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// format in form of M/D/YY
					double d = cell.getNumericCellValue();

					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;

				}

				return cellText;
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());

		} catch (Exception e) {

			e.printStackTrace();
			return "row " + rowNum + " or column " + colName + " does not exist in xls";
		}
	}
}
