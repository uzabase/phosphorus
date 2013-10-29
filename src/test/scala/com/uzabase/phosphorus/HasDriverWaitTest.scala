package com.uzabase.phosphorus

import org.junit.Test
import mockit.Mocked
import org.openqa.selenium.support.ui.WebDriverWait
import mockit.Expectations
import org.openqa.selenium.WebDriver

class HasDriverWaitTest {

	@Mocked
	var driverWait:WebDriverWait = null
	@Mocked
	var driver:WebDriver = null
	
	@Test
	def 指定された秒数だけ待つWebDriverWaitを取得する:Unit = {
		new Expectations{{
			new WebDriverWait(driver,10)
		}}
		new HasDriverWait{}.driverWait(10)(driver)
	}
}