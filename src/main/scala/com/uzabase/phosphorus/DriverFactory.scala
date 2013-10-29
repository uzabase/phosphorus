package com.uzabase.phosphorus

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.WebDriver

class DriverFactory {
	def create:WebDriver = {
		if(Config().isChrome) {
			System.setProperty("webdriver.chrome.driver", Config().chromeDriverUrl)
			return new ChromeDriver
		}
		return new FirefoxDriver
	}
}
object DriverFactory {
	def apply() = new DriverFactory
}