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
	def notPresent(xpath: String) = new ExpectedCondition[Boolean](){
		def apply(driver: WebDriver):Boolean = {
			try {
				driver.findElement(By.xpath(xpath))
				return Boolean.FALSE
			}catch {
				case e:NoSuchElementException => Boolean.TRUE 
			}
		}
	}
	def present(xpath: String) = ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))
	
	def selected(xpath: String) = ExpectedConditions.elementToBeSelected(By.xpath(xpath))
	
	def notSelected(xpath: String) = ExpectedConditions.elementSelectionStateToBe(By.xpath(xpath), false)
}