package com.uzabase.phosphorus.elements

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.WebDriverWait

class TextBox(val webElement:WebElement) extends Item with HasValue with HasInput

object TextBox extends HasUntil with InputXpath{
	val typeName = "text"
	def apply(atr: Predicate)(implicit driver:WebDriver):TextBox = new TextBox(driver.findElement(path(atr)))
	def apply(element:WebElement) = new TextBox(element)
	def apply(by:Predicate,wait:WebDriverWait)(implicit driver:WebDriver):TextBox = {
		until(wait, path(by))
		apply(by)
	}
}

trait HasInput {
	val webElement:WebElement
	def appending(text:String) = webElement.sendKeys(text)
	def input(text:String){
		webElement.clear()
		webElement.sendKeys(text)
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


case class TextArea(webElement:WebElement) extends Item with HasInput
object TextArea extends ElementCompanion[TextArea] {
	override def tagName = "textarea"
}
