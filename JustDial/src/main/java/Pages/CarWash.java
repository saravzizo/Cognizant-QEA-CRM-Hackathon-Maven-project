package Pages;

import java.io.*;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Base.Base;

public class CarWash extends Base {

	public void Wash() throws InterruptedException, IOException {
		try {
			System.out.println("\n================ CAR SERVICE DETAILS ===============\n");
			openURL("WashingUrl");
			reportPass("Car Washing Service page is Opened");
			Thread.sleep(2000);
			List<WebElement> names = driver.findElements(By.xpath("//div/div[2]/h2/a"));
			List<WebElement> rating = driver.findElements(By.xpath(
					"/html/body/div/section/section/div/div[5]/div[1]/div/div[1]/div/div/div/div[2]/div[1]/div[1]"));
			List<WebElement> contacts = driver.findElements(By.xpath("//div/div/div/div/a/div/span"));
			List<WebElement> voting = driver.findElements(By.xpath("//div/div[2]/div[1]/div[3]"));
			reportPass("Storing Details in lists");
			
			File file = new File(".\\Report\\CarDetails.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);

			XSSFRow row1 = sheet.createRow(0);
			row1.createCell(0).setCellValue("Name");
			row1.createCell(1).setCellValue("Rating(above 4.0)");
			row1.createCell(2).setCellValue("Contact");
			row1.createCell(3).setCellValue("Rating Count");
			int k = 1;
			System.out.println("List of Car Wash Services with greater than 4 Ratings:\n\n");
			System.out.println("Ratings(above 4.0)   Centre Name   Rating Count   Contact\n");

			for (int i = 0; i < voting.size(); i++) {
				float rate1 = Float.parseFloat(rating.get(i).getText());
				String str = voting.get(i).getText();
				String[] s = str.split(" ");
				int votes = Integer.parseInt(s[0]);

				if (rate1 > 4 && votes > 20) {
					XSSFRow row = sheet.createRow(k);
					String iname = names.get(i).getText();
					String irating = rating.get(i).getText();
					String icontact = contacts.get(i).getText();
					String ivoting = voting.get(i).getText();

					row.createCell(0).setCellValue(iname);
					row.createCell(1).setCellValue(irating + "/5");
					row.createCell(2).setCellValue(icontact);
					row.createCell(3).setCellValue(ivoting);
					FileOutputStream os = new FileOutputStream(".\\Report\\CarDetails.xlsx");
					wb.write(os);

					k++;
					System.out.println(rating.get(i).getText() + " - " + names.get(i).getText() + " - " + votes + " - "
							+ contacts.get(i).getText());
				}

			}
			wb.close();
			Screenshoot("CarWash");
			reportPass("Details inserted into Excel sheet");
			reportPass("Details printed Successfully.");
			System.out.println("\n====================================================\n");
		}

		catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
}
