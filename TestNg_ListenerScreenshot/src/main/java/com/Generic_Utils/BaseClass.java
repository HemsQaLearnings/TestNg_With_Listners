package com.Generic_Utils;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.PomPages.Pom_loginPage;

public class BaseClass implements IframeConstant {
	public WebDriver driver;
	public static WebDriver sdriver;
	public FileUtils fLib = new FileUtils();
	public JavaUtils jLib = new JavaUtils();
	public ExcelUtils eLib = new ExcelUtils();
	public WebDriverUtils wLib = new WebDriverUtils();
	public Pom_loginPage pl;

	@Parameters({ "browser" })
	@BeforeClass
	public void Config_bc(@Optional("chrome") String br) throws IOException {
		// int random = jLib.getRandomdata(100);// Random

		if (br.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
			wLib.waitforPageLoad(driver, 15); // plus maximize method is also present inside this method
			sdriver = driver;
			driver.get(fLib.readata("URL"));
		}

		else if (br.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
			wLib.waitforPageLoad(driver, 15); // plus maximize method is also present inside this method
			sdriver = driver;
			driver.get(fLib.readata("URL"));
		}

		else if (br.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
			wLib.waitforPageLoad(driver, 15); // plus maximize method is also present inside this method
			sdriver = driver;
			driver.get(fLib.readata("URL"));
		}

	}

	@BeforeMethod
	public void loginSetup() throws Throwable {
		/*
		 * String URL = fLib.readata("URL"); driver.get(URL); String Pt_USN =
		 * fLib.readata("USERNAME"); String Pt_PSW = fLib.readata("PASSWORD"); //String
		 * Pt_PSW = "12345"; // username form property file;
		 */

		// driver.findElement(By.name("user-name")).sendKeys(Pt_USN); // password form
		// property file;
		// driver.findElement(By.name("password")).sendKeys(Pt_PSW);
		// click on submit '
		// driver.findElement(By.xpath("//input[@type='submit']")).click();
		// Data fetching form ExcelSheet

		// =====================================================================================================
		ExcelUtils xlutils = new ExcelUtils();
		pl = new Pom_loginPage(driver);
		wLib.waituntilElementTobeClickable(driver, 10, pl.getUsn());
		pl.loginToApp(xlutils.getCellData("sheet1", 1, 0), xlutils.getCellData("sheet1", 1, 1));

		pl.getLogin_btn().click();

	}

	
	  @AfterMethod public void appShutdown() {
	  
		  pl.getLogout_dropdown().click();
		  pl.getLogout_btn().click();
	  
	  }
	 

	@AfterClass
	public void Teardown() {

		driver.quit();
	}

}
