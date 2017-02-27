package com.uzabase.phosphorus

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.firefox.internal.ProfilesIni
import org.openqa.selenium.firefox.FirefoxProfile

class DriverFactory {
	def create: WebDriver = {
		if (Config().isChrome) {
			System.setProperty("webdriver.chrome.driver", Config().chromeDriverUrl)
			val options = new ChromeOptions
			if (Config().isLang)
				options.addArguments("--lang=" + Config().lang)
        options.addArguments("--no-sandbox")
			if (Config().isRemote)
				return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeCapabilities(options));
			return new ChromeDriver(options)
		} else if (Config().isIE) {
			System.setProperty("webdriver.ie.driver", Config().ieDriverUrl);
			return new InternetExplorerDriver
		} else {
			val profile = new FirefoxProfile
			if (Config().isLang)
			    profile.setPreference("intl.accept_languages", Config().lang)
			if (Config().isRemote) {
				val capability = DesiredCapabilities.firefox()
				if(Config().isProfile) {
				  profile.setAcceptUntrustedCertificates(true)
				  profile.setAssumeUntrustedCertificateIssuer(false)
				}
        profile.setPreference("network.http.use-cache",false)
				capability.setCapability(FirefoxDriver.PROFILE, profile)
				new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
			} else {
				new FirefoxDriver(profile)
			}
		}
	}

	private def chromeCapabilities(options: ChromeOptions): DesiredCapabilities = {
		val capability = DesiredCapabilities.chrome()
		capability.setCapability(ChromeOptions.CAPABILITY, options)
		capability.setCapability("webdriver.chrome.driver", Config().chromeDriverUrl)
		capability.setCapability("chrome.binary", Config().chromeBinary)
		capability
	}
}
object DriverFactory {
	def apply() = new DriverFactory
}