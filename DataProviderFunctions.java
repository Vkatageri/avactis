package DataDrivenTest;
import java.io.File;
import org.testng.annotations.DataProvider;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DataProviderFunctions {
	
	@DataProvider(name = "LoginData")
	public Object[][] dataFromXLS() {
		Object[][] data = readDataFromXLS("test/resources/data/CredentialsOrangeHRM.xls", "NT", "NTStartEnd");
		return data;
	}

	public String[][] readDataFromXLS(String xlFilePath, String sheetName, String marker) {
		String[][] tabArray = null;
		try {
			Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
			Sheet sheet = workbook.getSheet(sheetName);
			Cell tableStart = sheet.findCell(marker);

			int startRow, startCol, endRow, endCol, ci, cj;
			startRow = tableStart.getRow();
			startCol = tableStart.getColumn();

			Cell tableEnd = sheet.findCell(marker, startCol + 1, startRow + 1, 100, 64000, false);

			endRow = tableEnd.getRow();
			endCol = tableEnd.getColumn();

			System.out.println("startRow-" + startRow + ", endRow=" + endRow + ", " + "startcol=" + startCol
					+ ",endCol=" + endCol);
			tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
			ci = 0;
			for (int i = startRow + 1; i < endRow; i++, ci++) {
				cj = 0;

				for (int j = startCol + 1; j < endCol; j++, cj++) {
					tabArray[ci][cj] = sheet.getCell(j, i).getContents();
				}
			}
		} catch (BiffException e) {
			System.out.println("tihe file you specified does not exist");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Please check file path, sheet name and tag name are correct");
			e.printStackTrace();

		}
		return (tabArray);
	}
}