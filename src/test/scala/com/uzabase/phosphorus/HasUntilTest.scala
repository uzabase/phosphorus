package com.uzabase.phosphorus

import org.junit._
import org.hamcrest.CoreMatchers._
import org.junit.Assert._
import mockit.Mocked
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions
import mockit.Expectations
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.WebElement
import com.uzabase.phosphorus.elements.HasUntil

class HasUntilTest {
	
	@Mocked
	var wWait:WebDriverWait = null
	@Mocked
	var conditions:ExpectedConditions = null
	@Mocked
	var condition:ExpectedCondition[WebElement] = null
	
	@Test
	def Elementが表示されるまでWebDriverWaitの時間だけ待つ:Unit = {
		val by = By.xpath("//select")
		new Expectations{{
			ExpectedConditions.presenceOfElementLocated(by)
			Expectations.result = condition
			wWait.until(condition)
		}}
		new HasUntil{}.until(wWait, by)
	}
}