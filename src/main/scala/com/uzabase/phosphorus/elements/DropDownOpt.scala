package com.uzabase.phosphorus.elements

import org.openqa.selenium.WebElement

case class DropDownOption(webElement: WebElement) extends Item with HasValue {
	def isSelected = webElement.getAttribute("selected") == "selected"
}

object DropDownOption extends ElementCompanyon[DropDownOption] with HasTagName{
	override def tagName = "option"
}