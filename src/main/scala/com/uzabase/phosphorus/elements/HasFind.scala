package com.uzabase.phosphorus.elements

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

trait HasFind {
	def find(by: =>By)(implicit driver:WebDriver) = driver.findElement(by)
}