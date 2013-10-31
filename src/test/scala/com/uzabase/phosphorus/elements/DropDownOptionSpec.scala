package com.uzabase.phosphorus.elements

import org.openqa.selenium.WebElement

import com.uzabase.phosphorus.UnitSpecification

class DropDownOptionSpec extends UnitSpecification{

	"DropDownのOption" should {
		"Optionタグにおけるvalue属性の値を取得する" in {
			val element = mock[WebElement]
			element.getAttribute("value") returns "val1"
			new DropDownOption(element).value must beEqualTo("val1")
		}
		"OptionがDropDownの中で選択状態となっている" in {
			val element = mock[WebElement]
			element.getAttribute("selected") returns "selected"
			new DropDownOption(element).isSelected must beTrue
		}
		"OptionがDropDownの中で選択状態となっていない" in {
			val element = mock[WebElement]
			element.getAttribute("selected") returns null
			new DropDownOption(element).isSelected must beFalse
		}
	}
}