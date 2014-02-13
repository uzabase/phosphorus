package com.uzabase.phosphorus

import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.NoSuchElementException
import java.lang.Boolean
import com.uzabase.phosphorus.elements.Element


trait Condition {
	def notPresent(xpath: String)(implicit driver: WebDriver) = new ExpectedCondition[Boolean](){
		def apply(driver: WebDriver):Boolean = {
			try {
				driver.findElement(By.xpath(xpath))
				return Boolean.FALSE
			}catch {
				case e:NoSuchElementException => Boolean.TRUE 
			}
		}
	}.apply(driver)
	def present(xpath: String)(implicit driver: WebDriver) = ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)).apply(driver)
	
	def selected(xpath: String)(implicit driver: WebDriver) = ExpectedConditions.elementToBeSelected(By.xpath(xpath)).apply(driver)
	
	def notSelected(xpath: String)(implicit driver: WebDriver) = ExpectedConditions.elementSelectionStateToBe(By.xpath(xpath), false).apply(driver)
}