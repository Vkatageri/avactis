package DataDrivenTest;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class DDTUsingXLSCSVDB1 {

	WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {
		driver = new ChromeDriver();
	}

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviderFunctions.class)
	public void f(String user, String pass) {

		driver.get("https://nichethyself.com/tourism/home.html");
		WebElement userName = driver.findElement(By.name("username"));
		userName.sendKeys(user);
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys(pass);
		password.submit();
	}

	@AfterMethod
	public void aftertMethod() {
		driver.quit();
	}

}
