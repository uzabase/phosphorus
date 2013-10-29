package com.uzabase.selenium

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import com.uzabase.selenium.elements.Predicate
import com.uzabase.selenium.elements.HasValue
import com.uzabase.selenium.elements.HasUntil
import com.uzabase.selenium.elements.Item
import com.uzabase.selenium.elements.InputXpath

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