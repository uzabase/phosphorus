package com.uzabase.phosphorus.elements

import org.openqa.selenium.WebElement
import org.junit.runner.RunWith
import com.uzabase.phosphorus.UnitSpecification

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