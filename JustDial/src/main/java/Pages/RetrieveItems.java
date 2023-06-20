package Pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import Base.Base;

public class RetrieveItems extends Base {
	public void items() throws IOException {
		try {
			System.out.println("\n=============== RETERIVING SUB-MENUS ===============\n");
			openURL("GymUrl");
			Thread.sleep(2000);

			driver.findElement(By.xpath("//*[@id=\"onCloseMobile\"]/span")).click();
			reportPass("Login pop-up handled Successfully");
			Screenshoot("Gym");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("scrollBy(0, 3000)");
			System.out.println("Gym Sub-Menu items :");
			Thread.sleep(2000);

			List<WebElement> options = driver.findElements(By.xpath("//*[@id=\"sectwo\"]/div[2]/div/ul/li"));
			int in = options.size() - 1;
			options.remove(in);

			File file = new File(".\\Report\\GymDetails.xlsx");
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			
			XSSFRow row1 = sheet.createRow(0);
			row1.createCell(0).setCellValue("GYM SUB_MENU LIST");

			int k = 1;

			String  [] str = new String[50];
			
			for(int i=0;i<10;i++){
				
			    str[i]=options.get(i).getText();
			    XSSFRow row = sheet.createRow(k);	
				row.createCell(0).setCellValue(str[i]);
				FileOutputStream os = new FileOutputStream(".\\Report\\GymDetails.xlsx");
				wb.write(os);
				System.out.println((String)str[i]);

				k++;
			} 
			
			wb.close();
			reportPass("Details inserted into Excel sheet");
			reportPass("Sub-Menus are obtained Successfully.");

		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

}
