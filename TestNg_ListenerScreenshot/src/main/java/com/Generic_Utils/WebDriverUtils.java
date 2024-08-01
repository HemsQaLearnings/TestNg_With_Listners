package com.Generic_Utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtils {

	public void maximize(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	public void minimize(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	public WebDriverWait WebDriverWaitObject(WebDriver driver, int sec)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(sec));
		return wait;
	}
	public void waitforPageLoad(WebDriver driver,int sec) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(sec));
		driver.manage().window().maximize();
	}
	public void waituntilEletoBeVisible(WebDriver driver,int sec,WebElement element)
	{
		//important learning
		WebDriverWaitObject(driver, sec).until(ExpectedConditions.visibilityOf(element));

	}
	public void waituntilAlertIsPresent(WebDriver driver,int sec) {
	
		WebDriverWaitObject(driver, sec).until(ExpectedConditions.alertIsPresent());
	}
	public void waituntilElementTobeClickable(WebDriver driver,int sec,WebElement element)
	{
		WebDriverWaitObject(driver, sec).until(ExpectedConditions.elementToBeClickable(element));
	}
	public void presenceOfAllElementsLocatedBy(WebDriver driver,int sec,WebElement element)
	{
		WebDriverWaitObject(driver, sec).until(ExpectedConditions.presenceOfElementLocated((By) element));
	}
	//Create an object for Dropdown so that you can we can optimize the code easily
	public Select selectDropDownOptionsObject(WebElement element)
	{
		Select sel=new Select(element);
		return sel;
	}
	public void selectDropDownOptions(String text_value,WebElement element)
	{
		selectDropDownOptionsObject(element).selectByValue(text_value);	
	}
	public void selectDropDownOptions(WebElement element,int index)
	{
		selectDropDownOptionsObject(element).selectByIndex(index);
	}
	public void selectDropDownOptions(WebElement element,String value)
	{
		selectDropDownOptionsObject(element).selectByVisibleText(value);
	}
	//end of Dropdown
	
	//mouseHover create an Object and Optimize
	public Actions mouseHoverClickObject(WebDriver driver) 
	{
		Actions act=new Actions(driver);
		return act;
	}
	/**
	 * This method will press enter key
	 * @param driver
	 */
	public void mouseHoverClickKeys(WebDriver driver)
	{
		mouseHoverClickObject(driver).sendKeys(Keys.ENTER).perform();
	}
	//click on element
	public void mouseHoverClick(WebDriver driver,WebElement element) 
	{
		mouseHoverClickObject(driver).click(element).perform();
		
	}

	//double click on element
	public void mouseHoverClick(WebElement element,WebDriver driver) 
	{
		mouseHoverClickObject(driver).doubleClick(element).perform();
	}
	//drag and drop

	public void mouseHoverClick(WebDriver driver,WebElement src,WebElement destination)
	{
		mouseHoverClickObject(driver).dragAndDrop(src, destination);
	}
	//just right click
	public void mouseHoverClickcontext(WebDriver driver)
	{
		mouseHoverClickObject(driver).contextClick().perform();
	}
	//scroll action using mouse hover
	public void mouseHoverClickcontext(WebDriver driver,int x,int y)
	{
		mouseHoverClickObject(driver).moveByOffset(x, y);
	}
	//using mouse action scroll to element
	public void mouseHoverClickcontext(WebDriver driver,WebElement element)
	{
		mouseHoverClickObject(driver).scrollToElement(element);
	}
	public void mouseHoverClickcontext(WebElement src,WebDriver driver)
	{
		mouseHoverClickObject(driver).contextClick(src).perform();
	}
	
	public void AlertAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	public void AlertDismiss(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	public void AlertSendKeys(WebDriver driver,String text)
	{
		driver.switchTo().alert().sendKeys(text);
	}
	public void javascriptExecutorById(WebDriver driver)
	{
		 JavascriptExecutor js=(JavascriptExecutor)driver;
		 js.executeScript("document.getElementById('text-field').value='ABC'");
	}
	public void javascriptExecutorByTextpass(WebDriver driver,WebElement element,String text)
	{
		 JavascriptExecutor js=(JavascriptExecutor)driver;
		 js.executeScript("arguments[0].value='"+text+"'", element);
	}
	public void javascriptExecutorByHiddenElement(WebDriver driver,String text,WebElement element)
	{
		 JavascriptExecutor js=(JavascriptExecutor)driver;
		 js.executeScript("arguments[0].value='"+text+"'", element);
	}
	public void javascriptExecutorScrollByXandY(WebDriver driver,int x,int y)
	{
		 JavascriptExecutor js=(JavascriptExecutor)driver;
		 js.executeScript("window.scrollBy("+x+","+y+")");
	}
	public void javascriptExecutorScrollIntoViewtoElement(WebDriver driver,WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
	/**
	 * 
	 * @param driver
	 * @param screenshotName
	 * @return
	 * @throws Exception
	 */
	public static String takescreenshot(WebDriver driver,String screenshotName) throws Exception
	{
			JavaUtils jLib= new JavaUtils();
			TakesScreenshot ts = (TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			String Path ="./ScreenShot/"+screenshotName+" "+jLib.getSystemDateInFormat()+".jpg";
			File dst=new File(Path);
			String spath = dst.getAbsolutePath();
			FileUtils.copyFile(src, dst);
			return spath;
				
	}
	
	/**
	 * THis method will press enter key
	 * @throws Throwable
	 */
	public void enterKey() throws Throwable
	{
		Robot rb = new Robot();
		rb.keyPress(KeyEvent.VK_ENTER);
	}
	
	
	/**
	 * This method is to release the key
	 * @throws Throwable 
	 * @throws Throwable
	 */
	public void enterRelease() throws Throwable
	{
		Robot rb = new Robot();
		rb.keyRelease(KeyEvent.VK_ENTER);
	}
	
	
	/**
	 * This method will switch the frame based on index
	 * @param driver
	 * @param index
	 */
	public void swithToFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	
	
	/**
	 * This method will switch the frame based on name or ID
	 * @param driver
	 * @param nameOrId
	 */
	public void switchToFrame(WebDriver driver, String nameOrId)
	{
		driver.switchTo().frame(nameOrId);
	}
	
	
	/**
	 * This method will switch the frame based on address of the element
	 * @param driver
	 * @param address
	 */
	public void switchToFrame(WebDriver driver, WebElement address)
	{
		driver.switchTo().frame(address);
	}

	
	public void switchToWindow(WebDriver driver, String expected)
	{
		//step1: use getWindowHandles to capture all window ids
		Set<String> windows = driver.getWindowHandles();
		
		//step2: iterate thru the windows
		Iterator<String> it = windows.iterator(); 
		
		//step3: check whether there is next window
		while(it.hasNext())
		{
			//step4: capture current window id
			String winId = it.next();
			
			//step5: switch to current window and capture title
			String actual = driver.switchTo().window(winId).getTitle();
			
			//step6: check whether the current window is expected
			if(actual.contains(expected))
			{
				break;
			}
		}
	}

	/**
	 * This method will perform random scroll
	 * @param driver
	 */
	public void scrollAction(WebDriver driver)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)","");
	}
	
	/**
	 * THis method will scroll untill the specified element is found
	 * @param driver
	 * @param element
	 */
	public void scrollAction(WebDriver driver, WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int y = element.getLocation().getY();
		js.executeScript("window.scrollBy(0,"+y+")", element);
		//js.executeScript("argument[0].scrollIntoView()",element);
				
	}


	
}
