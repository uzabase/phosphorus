package com.uzabase.phosphorus

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedCondition

trait HasDriverWait {
	def driverWait(secounds:Int=1)(implicit driver:WebDriver) = new WebDriverWait(driver,secounds)
	def wait[T](f: => T, secounds:Int=1)(implicit driver: WebDriver) = new WebDriverWait(driver,secounds).until(new ExpectedCondition[T](){def apply(webDriver:WebDriver) = f})
}