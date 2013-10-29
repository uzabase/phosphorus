package com.uzabase.selenium.elements

import org.openqa.selenium.By

trait InputXpath {
	val typeName:String
	def path(atr: Predicate) = By.xpath(s"//input[@type='${typeName}' and ${atr.syntax}]")
}