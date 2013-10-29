package com.uzabase.selenium.elements

import org.hamcrest.CoreMatchers.is
import org.junit.Assert.assertThat
import org.junit.Test
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select

import mockit.Expectations
import mockit.Expectations.result
import mockit.Mocked

class DropDownTest {
	
	@Mocked
	var webElement:WebElement = null
	@Mocked
	var option1:DropDownOption = null
	@Mocked
	var option2:DropDownOption = null
	@Mocked
	var option3:DropDownOption = null
	@Mocked
	var select:Select = null

	@Test
	def DropDownが持つOptionにおけるvalue属性の文字列を取得する = {
		new Expectations{{
			option1.value;result = "val1";
			option2.value;result = "val2";
			option3.value;result = "val3";
		}}
		val expect:Seq[String] = List("val1","val2","val3")
		assertThat(new DropDown(webElement,List(option1,option2,option3)).optionsValue, is(expect))
	}
	
	@Test
	def DropDownが持つOptionにおけるテキストノードの文字列を取得する = {
		new Expectations{{
			option1.text;result = "text1";
			option2.text;result = "text2";
			option3.text;result = "text3";
		}}
		val expect:Seq[String] = List("text1","text2","text3")
		assertThat(new DropDown(webElement,List(option1,option2,option3)).optionsText, is(expect))
	}
	
	@Test
	def 指定した文字列に一致するテキストノードを持つOptionを選択状態にする:Unit = {
		new Expectations{{
			new Select(webElement)
			select.selectByVisibleText("op1")
		}}
		new DropDown(webElement,List(option1,option2,option3)).select(text_("op1"))
	}
	
	@Test
	def 指定した文字列に一致するvalue属性値を持つOptionを選択状態にする:Unit = {
		new Expectations{{
			new Select(webElement)
			select.selectByValue("val1")
		}}
		new DropDown(webElement,List(option1,option2,option3)).select(value_("val1"))
	}
}