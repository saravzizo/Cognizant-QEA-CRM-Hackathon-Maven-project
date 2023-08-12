package Pages;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import Base.Base;

public class FListing extends Base {

	public void register() throws IOException {

		try {
			System.out.println("\n=================== FREE LISTING ===================\n");
			openURL("websiteURLKey");
			reportPass("Free Listing page is Opened");
			System.out.println("Free Listing page is opened");

			FileInputStream fs = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/TestData.xlsx");
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet("Data");

			driver.findElement(By.id("fmb0")).sendKeys(sheet.getRow(1).getCell(0).getStringCellValue());
			reportPass("Invalid data is entered in the Mobile number field");

			driver.findElement(By.xpath("//*[@id=\"add_div0\"]/div[3]/button")).click();
			reportPass("Submit the data");

			String str = driver.findElement(By.id("fcoe")).getText();
			System.out.println("Invalid Input error message :" + str);

			Screenshoot("FList");
			reportPass("Error is obtained");
			
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

}
