package com.uzabase.phosphorus

import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.NoSuchElementException
import java.lang.Boolean
import com.uzabase.phosphorus.elements.Element
import com.uzabase.phosphorus.elements.Item
import org.openqa.selenium.support.ui.WebDriverWait


trait Condition {
	def notPresent(xpath: String)(implicit driver: WebDriver) = waitDriver(driver).until(new ExpectedCondition[Boolean](){
		def apply(driver: WebDriver):Boolean = {
			try {
				driver.findElement(By.xpath(xpath))
				return Boolean.FALSE
			}catch {
				case e:NoSuchElementException => Boolean.TRUE 
			}
		}
	})
	
	def present(xpath: String)(implicit driver: WebDriver) = waitDriver(driver).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)))
	
	def selected(xpath: String)(implicit driver: WebDriver) = waitDriver(driver).until(ExpectedConditions.elementToBeSelected(By.xpath(xpath)))
	
	def clickable(item: Item)(implicit driver: WebDriver) = waitDriver(driver).until(ExpectedConditions.elementToBeClickable(item.webElement))
	
	def clickable(xpath: String)(implicit driver: WebDriver) = waitDriver(driver).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)))
	
	def notSelected(xpath: String)(implicit driver: WebDriver) = waitDriver(driver).until(ExpectedConditions.elementSelectionStateToBe(By.xpath(xpath), false))
	
  private def waitDriver(driver: WebDriver):WebDriverWait = new WebDriverWait(driver,Config().waitTime)
}