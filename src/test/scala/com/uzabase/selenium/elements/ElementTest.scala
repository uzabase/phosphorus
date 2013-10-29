package com.uzabase.selenium.elements

import java.lang.Boolean

import org.hamcrest.CoreMatchers.is
import org.junit.Assert.assertThat
import org.junit.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

import mockit.Expectations
import mockit.Mocked

class ElementTest {

	@Mocked
	var element: WebElement = null
	@Mocked
	var otherElement: WebElement = null
	@Mocked
	var by: By = null
	@Mocked
	implicit var driver:WebDriver = null
	@Mocked
	var actions:Actions = null

	@Test
	def エレメントをClickする: Unit = {
		new Expectations {{
			element.click
		}}
		new Clickable(){val webElement= element}.click
		
	}
	
	@Test
	def 画面上に表示されている = {
		new Expectations{{
			element.isDisplayed
			Expectations.result = Boolean.TRUE
		}}
		assertThat(new Item(){val webElement=element}.displayed, is(true))
	}
	
	@Test
	def 画面上に表示されていない = {
		new Expectations{{
			element.isDisplayed
			Expectations.result = Boolean.FALSE
		}}
		assertThat(new Item(){val webElement=element}.displayed, is(false))
	}
	
	@Test
	def 活性化している = {
		new Expectations{{
			element.isEnabled
			Expectations.result = Boolean.TRUE
		}}
		assertThat(new HasEnable(){val webElement = element}.enabled, is(true))
	}
	
	@Test
	def 活性化していない = {
		new Expectations{{
			element.isEnabled
			Expectations.result = Boolean.FALSE
		}}
		assertThat(new HasEnable(){val webElement = element}.enabled, is(false))
	}
	
	@Test
	def 選択状態である = {
		new Expectations{{
			element.isSelected
			Expectations.result = Boolean.TRUE
		}}
		assertThat(new Selectable(){val webElement = element}.selected, is(true))
	}
	
	@Test
	def 選択状態ではない = {
		new Expectations{{
			element.isSelected
			Expectations.result = Boolean.FALSE
		}}
		assertThat(new Selectable(){val webElement = element}.selected, is(false))
	}
	
	@Test
	def エレメントノードのテキスト文字列を取得する = {
		new Expectations{{
			element.getText
			Expectations.result = "hoge"
		}}
		assertThat(new HasText(){val webElement = element}.text, is("hoge"))
	}
	
	@Test
	def エレメントを指定された対象のエレメントまで移動する:Unit = {
		new Expectations{{
			new Actions(driver)
			actions.dragAndDrop(element, otherElement)
		}}
		new Item(){val webElement=element}.dragAndDrop(new Item(){val webElement=otherElement})
	}
	
	@Test
	def エレメントを現在の位置から指定されたX及びYの分だけ移動する:Unit = {
		new Expectations{{
			new Actions(driver)
			actions.dragAndDropBy(element, 10, 20)
		}}
		new Item(){val webElement=element}.dragAndDrop(10, 20)
	}
}