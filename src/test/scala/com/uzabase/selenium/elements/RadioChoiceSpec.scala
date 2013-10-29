package com.uzabase.selenium.elements

import com.uzabase.selenium.UnitSpecification
import org.openqa.selenium.WebDriver

class RadioChoiceSpec extends UnitSpecification{

	"ラジオボタン" should {
		"ラジオボタンのグループにて1つ以上表示されている" in {
			var r1 = mock[Radio]
			r1.displayed returns false
			var r2 = mock[Radio]
			r2.displayed returns false
			var r3 = mock[Radio]
			r3.displayed returns true
			new RadioChoice(List(r1,r2,r3)).displayed must beTrue
		}
		"ラジオボタンのグループが全て非表示である" in {
			var r1 = mock[Radio]
			r1.displayed returns false
			var r2 = mock[Radio]
			r2.displayed returns false
			var r3 = mock[Radio]
			r3.displayed returns false
			new RadioChoice(List(r1,r2,r3)).displayed must beFalse
		}
		"指定した文字列と一致するラベルが対象とするラジオボタンをクリックする" in {
			val label = mock[Element]
			val driver = mock[WebDriver]
			label.text returns "hoge"
			val radio = mock[Radio]
			radio.label returns label
			new RadioChoice(List(radio)).choice("hoge")(driver)
			there was one(label).text
			there was one(radio).click
		}
	}
}