package com.uzabase.phosphorus

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver

class DriverFactory {
	def create:WebDriver = {
		if(Config().isChrome) {
			System.setProperty("webdriver.chrome.driver", Config().chromeDriverUrl)
			val options = new ChromeOptions
			if(Config().isLang)
				options.addArguments("--lang="+Config().lang);
			return new ChromeDriver(options)
		}
		val capabilities = new DesiredCapabilities();
		capabilities.setJavascriptEnabled(true);
		val firefoxDriver = new FirefoxDriver(capabilities)
		firefoxDriver.asInstanceOf[RemoteWebDriver].getCapabilities()
		return firefoxDriver
	}
}
object DriverFactory {
	def apply() = new DriverFactory
}