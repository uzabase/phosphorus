package com.uzabase.selenium.elements

import scala.collection.JavaConversions.asScalaBuffer

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.ui.WebDriverWait

class DropDown(val webElement: WebElement, val options: Seq[DropDownOption]) extends Item {

	def optionsValue = options.map(o => o.value)
	def optionsText = options.map(o => o.text)
	def select(atr: ElementSelectable) = atr match{
		case txt:text_ => new Select(webElement).selectByVisibleText(txt.value)
		case value:value_ => new Select(webElement).selectByValue(value.value)
	}
}

object DropDown extends HasUntil{
	
	val tagName = "select"
	
	def apply(atr: Predicate)(implicit driver: WebDriver) = createOption(driver.findElement(atr.by(tagName)))

	def apply(atr: Predicate, wait: WebDriverWait)(implicit driver: WebDriver) = {
		until(wait, atr.by(tagName))
		createOption(driver.findElement(atr.by(tagName)))
	}

	private def createOption(element: WebElement)(implicit driver: WebDriver): DropDown = {
		import collection.JavaConversions._
		new DropDown(element, element.findElements(By.tagName("option")).map(option => DropDownOption(option)))
	}
}