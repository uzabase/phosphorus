package com.uzabase.phosphorus

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL

class DriverFactory {
  def create: WebDriver = {
    if (Config().isChrome) {
      System.setProperty("webdriver.chrome.driver", Config().chromeDriverUrl)
      val options = new ChromeOptions
      if (Config().isLang)
        options.addArguments("--lang=" + Config().lang)
      if (Config().isRemote)
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeCapabilities(options));
      return new ChromeDriver(options)
    }
    if (Config().isRemote) {
      val capability = DesiredCapabilities.firefox();
      new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
    } else {
      new FirefoxDriver
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