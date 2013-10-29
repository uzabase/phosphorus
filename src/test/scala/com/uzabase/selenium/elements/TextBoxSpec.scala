package com.uzabase.selenium.elements

import org.openqa.selenium.WebElement
import com.uzabase.selenium.TextBox
import com.uzabase.selenium.UnitSpecification
import org.junit.runner.RunWith

class TextBoxSpec extends UnitSpecification {

	"テキストボックス" should {
		"指定した文字列をテキストボックスのフィールドに入力する" in {
			val element = mock[WebElement]
			val input = "hoge"
			new TextBox(element).input(input)
			there was one(element).clear
			there was one(element).sendKeys(input)
		}
		"指定した文字列をテキストボックスに追記する" in {
			val element = mock[WebElement]
			val input = "h"
			new TextBox(element).appending(input)
			there was one(element).sendKeys(input)
		}
	}
}