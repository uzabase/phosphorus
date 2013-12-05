package com.uzabase.phosphorus

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.Platform
import java.net.URL

class DriverFactory {
	def create: WebDriver = {
		if (Config().isChrome) {
			System.setProperty("webdriver.chrome.driver", Config().chromeDriverUrl)
			val options = new ChromeOptions
			if (Config().isLang)
				options.addArguments("--lang=" + Config().lang);
			return new ChromeDriver(options)
		}
		//		val capabilities = new DesiredCapabilities();
		//		capabilities.setJavascriptEnabled(true);
		//		val firefoxDriver = new FirefoxDriver(capabilities)
		//		firefoxDriver.asInstanceOf[RemoteWebDriver].getCapabilities()
		val capability = DesiredCapabilities.firefox();
		capability.setCapability("platform", Platform.ANY);
		capability.setCapability("binary", "/ms/dist/fsf/PROJ/firefox/16.0.0/bin/firefox"); //for linux

		//capability.setCapability("binary", "C:\\Program Files\\Mozilla  Firefox\\msfirefox.exe"); //for windows                
		val currentDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
		return currentDriver
	}
}
object DriverFactory {
	def apply() = new DriverFactory
}