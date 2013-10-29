package com.uzabase.phosphorus.elements

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class TextBox(val webElement:WebElement) extends Item with HasValue{
	def appending(text:String) = webElement.sendKeys(text)
	def input(text:String){
		webElement.clear()
		webElement.sendKeys(text)
	}
}

object TextBox extends HasUntil with InputXpath{
	val typeName = "text"
	def apply(atr: Predicate)(implicit driver:WebDriver) = new TextBox(driver.findElement(path(atr)))
	def apply(element:WebElement) = new TextBox(element)
}