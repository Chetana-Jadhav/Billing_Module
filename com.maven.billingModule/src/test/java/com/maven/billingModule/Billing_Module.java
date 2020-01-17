package com.maven.billingModule;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.maven.billingModule.*;

import org.testng.annotations.Listeners;
public class Billing_Module {
	public static Select dropdown;
	public WebDriver driver;
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	@BeforeTest
	public void setExtent() {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/myReport.html");

		htmlReporter.config().setDocumentTitle("Automation Report"); 
		htmlReporter.config().setReportName("Functional Testing");
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);


		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("user", "Chetana");
	}
	@AfterTest
	public void endReport() {
		extent.flush();


	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost/LogicInvoice/upload/admin/index.php?load=common/login");
		driver.manage().timeouts().implicitlyWait(3000,TimeUnit.SECONDS);
		WebElement username =driver.findElement(By.xpath("//input[@id='input-username']"));
		username.clear();
		username.sendKeys("admin");

		WebElement password=driver.findElement(By.xpath("//input[@id='input-password']"));
		password.clear();
		password.sendKeys("admin123");

		WebElement click=driver.findElement(By.xpath("//button[@class='btn btn-lg btn-primary btn-block']"));
		click.click();
		driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);

	}
	@Test(priority=1)
	public void verifyDashboardTitle(){
		test=extent.createTest("verifyDashboardTitle");
		Assert.assertEquals("Dashboar",driver.getTitle());

	}
	@Test(priority=2)
	public void addCustomer(){
		test=extent.createTest("addCustomer");
		Actions builder=new Actions(driver);
		WebElement billing = driver.findElement(By.xpath("/html[1]/body[1]/nav[1]/ul[1]/li[3]/a[1]/i[1]"));
		builder.moveToElement(billing).build().perform();
		driver.findElement(By.xpath("/html[1]/body[1]/nav[1]/ul[1]/li[3]/ul[1]/li[1]/a[1]/span[1]")).click();
		Assert.assertEquals("Customers", driver.getTitle());
		WebElement AddCustomer = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/a[1]"));
		AddCustomer.click();
		WebElement firstName=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/input[1]"));
		firstName.clear();
		firstName.sendKeys("Chetana");
		WebElement LastName=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[2]/div[1]/input[1]"));
		LastName.clear();
		LastName.sendKeys("Jadhav");
		WebElement company=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[3]/div[1]/input[1]"));
		company.clear();
		company.sendKeys("I-Tech");
		WebElement website=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[4]/div[1]/input[1]"));
		website.clear();
		website.sendKeys("www.samplesite.com");
		WebElement Email=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[5]/div[1]/input[1]"));
		Email.clear();
		Email.sendKeys("test@test.com");
		WebElement passowrd1=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[6]/div[1]/input[1]"));
		passowrd1.clear();
		passowrd1.sendKeys("test123");
		WebElement confirmPassowrd=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[7]/div[1]/input[1]"));
		confirmPassowrd.clear();
		confirmPassowrd.sendKeys("test123");
		dropdown = new Select(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[8]/div[1]/select[1]")));
		dropdown.selectByVisibleText("Enabled");

		WebElement saveButton = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/button[1]/i[1]"));
		saveButton.click();
		driver.manage().timeouts().implicitlyWait(60000,TimeUnit.SECONDS);
	}
	@Test(priority=3)
	public void createInvoice(){
		test=extent.createTest("createInvoice");
		Actions builder=new Actions(driver);
		WebElement billing = driver.findElement(By.xpath("/html[1]/body[1]/nav[1]/ul[1]/li[3]/a[1]/i[1]"));
		builder.moveToElement(billing).build().perform();
		driver.findElement(By.xpath("/html[1]/body[1]/nav[1]/ul[1]/li[3]/ul[1]/li[2]/a[1]/span[1]")).click();
		Actions action1=new Actions(driver);
		WebElement addinvoice = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/a[1]"));
		addinvoice.click();
		WebElement customer= driver.findElement(By.id("input-customer"));
		Actions builder2 =new Actions(driver);
		Actions seriesOfActions = builder2.moveToElement(customer).click().sendKeys(customer,"Chetana Jadhav");
		seriesOfActions.perform();
		driver.findElement(By.linkText("Chetana Jadhav (test@test.com)")).click();
		dropdown = new Select(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[8]/div[1]/select[1]")));
		dropdown.selectByVisibleText("Approved");
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[9]/div[1]/div[1]/input[1]")).click();
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[10]/button[1]/i[1]")).click();
		WebElement paymentAdd1=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[4]/div[1]/input[1]"));
		paymentAdd1.sendKeys("nashik");
		WebElement paymentAdd2=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[5]/div[1]/input[1]"));
		paymentAdd2.sendKeys("nashik");
		WebElement paymentcity=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[6]/div[1]/input[1]"));
		paymentcity.sendKeys("nashik");
		WebElement PaymentPostcode=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[7]/div[1]/input[1]"));
		PaymentPostcode.sendKeys("422012");
		WebElement PaymentCountry=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[8]/div[1]/input[1]"));
		PaymentCountry.sendKeys("India");
		WebElement PaymentZone=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[9]/div[1]/input[1]"));
		PaymentZone.sendKeys("COD");
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[11]/button[1]/i[1]")).click();
		dropdown = new Select(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[3]/div[1]/div[1]/select[1]")));
		dropdown.selectByVisibleText("Bank Transfer");
		WebElement paymentName=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[3]/div[2]/div[1]/input[1]"));
		paymentName.sendKeys("credit card");
		WebElement payDescription=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[3]/div[3]/div[1]/textarea[1]"));
		paymentName.sendKeys("This is test data for payment description");
		dropdown = new Select(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[3]/div[4]/div[1]/select[1]")));
		dropdown.selectByVisibleText("US Dollar");
		WebElement currencyValue=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[3]/div[5]/div[1]/input[1]"));
		currencyValue.sendKeys("1.00");
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[3]/div[7]/button[1]")).click();
		WebElement addItem=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[4]/div[1]/table[1]/tbody[1]/tr[2]/td[1]/button[1]"));
		addItem.click();
		WebElement ItemTitle=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[4]/div[1]/table[1]/tbody[1]/tr[2]/td[1]/input[1]"));
		ItemTitle.sendKeys("Laptop");
		WebElement ItemDescription=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[4]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/textarea[1]"));
		ItemDescription.sendKeys("Lenovo Laptop");
		WebElement ItemQuantity=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[4]/div[1]/table[1]/tbody[1]/tr[2]/td[3]/input[1]"));
		ItemDescription.sendKeys("2");
		WebElement ItemPrice=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[4]/div[1]/table[1]/tbody[1]/tr[2]/td[4]/div[1]/input[1]"));
		ItemPrice.sendKeys("50000.00");
		dropdown = new Select(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[4]/div[1]/table[1]/tbody[1]/tr[2]/td[5]/select[1]")));
		dropdown.selectByVisibleText("None");
		WebElement Discount=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[4]/div[1]/table[1]/tbody[1]/tr[2]/td[6]/div[1]/input[1]"));
		Discount.sendKeys("5000.00");
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[4]/div[3]/button[1]")).click();
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/button[1]")).click();
		Assert.assertEquals("Invoices", driver.getTitle());
	}
	@Test(priority=4)
	public void RecurringPayment(){
		test=extent.createTest("RecurringPayment");
		Actions builder=new Actions(driver);
		WebElement billing = driver.findElement(By.xpath("/html[1]/body[1]/nav[1]/ul[1]/li[3]/a[1]/i[1]"));
		builder.moveToElement(billing).build().perform();
		driver.findElement(By.xpath("/html[1]/body[1]/nav[1]/ul[1]/li[3]/ul[1]/li[3]/a[1]")).click();
		Assert.assertEquals("Recurring Payments", driver.getTitle());
		WebElement addRecurringPayment=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/a[1]"));
		addRecurringPayment.click();
		WebElement Customer= driver.findElement(By.id("input-customer"));
		Actions builder2 =new Actions(driver);
		Actions seriesOfActions = builder2.moveToElement(Customer).click().sendKeys(Customer,"Chetana Jadhav");
		seriesOfActions.perform();
		driver.findElement(By.linkText("Chetana Jadhav (test@test.com)")).click();
		WebElement comment=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[2]/div[1]/textarea[1]"));
		comment.sendKeys("test comment");
		dropdown = new Select(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[3]/div[1]/select[1]")));
		dropdown.selectByVisibleText("Enabled");
		dropdown = new Select(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[4]/div[1]/select[1]")));
		dropdown.selectByVisibleText("Monthly");
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[5]/div[1]/div[1]/input[1]")).click();
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[6]/button[1]")).click();
		dropdown = new Select(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[1]/div[1]/select[1]")));
		dropdown.selectByVisibleText("Bank Transfer");
		WebElement PayName=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[2]/div[1]/input[1]"));
		PayName.sendKeys("PayTM");
		WebElement PayDescrptn=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[3]/div[1]/textarea[1]"));
		PayDescrptn.sendKeys("Test payment description");
		dropdown = new Select(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[4]/div[1]/select[1]")));
		dropdown.selectByVisibleText("US Dollar");
		WebElement crncyValue=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[5]/div[1]/input[1]"));
		crncyValue.sendKeys("1.00");
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[7]/button[1]/i[1]")).click();
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[3]/div[1]/table[1]/tbody[1]/tr[2]/td[1]/button[1]")).click();
		WebElement Title=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[3]/div[1]/table[1]/tbody[1]/tr[2]/td[1]/input[1]"));
		Title.sendKeys("SofaSet");
		WebElement description= driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[3]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/textarea[1]"));
		description.sendKeys("item description");
		WebElement quantity= driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[3]/div[1]/table[1]/tbody[1]/tr[2]/td[3]/input[1]"));
		quantity.sendKeys("1");
		WebElement price= driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[3]/div[1]/table[1]/tbody[1]/tr[2]/td[4]/div[1]/input[1]"));
		price.sendKeys("30000");
		dropdown = new Select(driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[3]/div[1]/table[1]/tbody[1]/tr[2]/td[5]/select[1]")));
		dropdown.selectByVisibleText("None");
		WebElement discount= driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[3]/div[1]/table[1]/tbody[1]/tr[2]/td[6]/div[1]/input[1]"));
		discount.sendKeys("1000");
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[3]/div[3]/button[1]")).click();
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/button[1]")).click();

	}
	@AfterMethod

	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent report
			String screenshotPath =  Billing_Module.getScreenshot(driver, result.getName());
			test.addScreenCaptureFromPath(screenshotPath);// adding screen shot
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
		}
		driver.findElement(By.xpath("/html[1]/body[1]/header[1]/ul[1]/li[2]/a[1]")).click();
		driver.findElement(By.xpath("/html[1]/body[1]/header[1]/ul[1]/li[2]/ul[1]/li[1]/a[1]")).click();

		driver.close();
	}
	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		// after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/Screenshot/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;

	}
}
