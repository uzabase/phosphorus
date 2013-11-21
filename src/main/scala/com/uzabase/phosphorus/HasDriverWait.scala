package com.uzabase.phosphorus

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import com.thoughtworks.selenium.Wait
import java.lang.Boolean

trait HasDriverWait {
	def driverWait(secounds:Int=1)(implicit driver:WebDriver) = new WebDriverWait(driver,secounds)
	def wait(f:() => Boolean, milliseconds:Long = 1000) = new Wait(){override def until = f()}.wait("waiting error.",milliseconds);
}