package com.uzabase.phosphorus.elements

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.WebDriverWait

class TextBox(val webElement:WebElement) extends Item with HasValue{
	def appending(text:String) = webElement.sendKeys(text)
	def input(text:String){
		webElement.clear()
		webElement.sendKeys(text)
	}
}

object TextBox extends HasUntil with InputXpath{
	val typeName = "text"
	def apply(atr: Predicate)(implicit driver:WebDriver):TextBox = new TextBox(driver.findElement(path(atr)))
	def apply(element:WebElement) = new TextBox(element)
	def apply(by:Predicate,wait:WebDriverWait)(implicit driver:WebDriver):TextBox = {
		until(wait, path(by))
		apply(by)
	}
}

class Password(element: WebElement) extends TextBox(element)
object Password extends HasUntil with InputXpath{
	val typeName = "password"
	def apply(atr: Predicate)(implicit driver:WebDriver) = new Password(driver.findElement(path(atr)))
	def apply(element:WebElement) = new Password(element)
	def apply(by:Predicate,wait:WebDriverWait)(implicit driver:WebDriver):Password = {
		until(wait, path(by))
		apply(by)
	}
}