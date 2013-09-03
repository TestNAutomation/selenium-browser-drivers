package com.eliasnogueira.browserdrivers;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


import com.opera.core.systems.OperaDriver;

public class BasicBrowserDriversTest {

	final String chromeBinary = "C:/Users/Elias/AppData/Local/Google/Chrome/Application/chrome.exe";
	final String webdriverChromeBinary = "C:/drivers/chromedriver.exe";
	final String ieDriverServer = "C:/drivers/IEDriverServer.exe";
	final String phantomjsBinaryPath = "C://phantomjs/phantomjs.exe";
	
	WebDriver driver = null;
	DesiredCapabilities capabilities = null;
	
	@Before
	public void setUp() throws Exception {
		capabilities = new DesiredCapabilities();
		capabilities.setJavascriptEnabled(true);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}


	@Test
	public void firefoxTest() {
		driver = new FirefoxDriver();
		simpleTest(driver);
	}

	
	@Test
	public void googleChromeTest() {
		capabilities = DesiredCapabilities.chrome();
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability("chrome.binary", chromeBinary);
		System.setProperty("webdriver.chrome.driver", webdriverChromeBinary); 

		driver = new ChromeDriver(capabilities);
		simpleTest(driver);
	}

	
	@Test
	public void internetExplorerETest() {
		File file = new File(ieDriverServer);
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		
		driver = new InternetExplorerDriver(capabilities);
		simpleTest(driver);
	}
	

	@Test
	public void operaTest() {
		driver = new OperaDriver(capabilities);
		simpleTest(driver);
	}
	

	@Test
	public void htmlUnitDriverTest() {
		driver = new HtmlUnitDriver(capabilities);
		simpleTest(driver);
	}
	

	@Test
	public void ghostDriverTest() {
		System.setProperty("phantomjs.binary.path", phantomjsBinaryPath);
		driver = new PhantomJSDriver(capabilities);
		simpleTest(driver);
	}
	
	
	private void simpleTest(WebDriver driver) {
		driver.get("https://github.com");
	}
		
	
}
