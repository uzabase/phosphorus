package com.uzabase.phosphorus

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait

trait HasDriverWait {
	def driverWait(secounds:Int=1)(implicit driver:WebDriver) = new WebDriverWait(driver,secounds)
}