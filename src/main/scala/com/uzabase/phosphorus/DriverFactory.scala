package com.uzabase.phosphorus

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL

class DriverFactory {
	def create:WebDriver = {
		if(Config().isChrome) {
			System.setProperty("webdriver.chrome.driver", Config().chromeDriverUrl)
			val options = new ChromeOptions
			if(Config().isLang)
				options.addArguments("--lang="+Config().lang);
			return new ChromeDriver(options)
		}
		val capability = DesiredCapabilities.firefox();
		new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
	}
}
object DriverFactory {
	def apply() = new DriverFactory
}