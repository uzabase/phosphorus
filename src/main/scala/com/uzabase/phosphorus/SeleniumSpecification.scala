package com.uzabase.phosphorus

import org.specs2._
import org.openqa.selenium.firefox.FirefoxDriver
import org.specs2.specification._
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.concurrent.TimeUnit

@RunWith(classOf[JUnitRunner])
abstract class SeleniumSpecification extends Specification with CoreMatchers with HasDriverWait{

	implicit val driver = DriverFactory().create

	def setUp = {
		driver.manage().window().maximize()
		driver.get(Config().applicationUrl);
		driver.manage.timeouts.implicitlyWait(2, TimeUnit.SECONDS);
	}

	def cleanUp = driver.quit

	def title = driver.getTitle
	
	def createWait(secounds:Int=10) = new WebDriverWait(driver,secounds)

	override def map(fs: => Fragments) = Step(setUp) ^ fs ^ Step(cleanUp)
}