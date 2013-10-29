package com.uzabase.phosphorus.elements

import org.openqa.selenium.By
import com.uzabase.phosphorus.UnitSpecification

class XpathSpec extends UnitSpecification{

	"Xpath" should {
		"inputタグの場合は指定された属性情報とtype属性の名前を基にXpathを生成する" in {
			val atr = mock[Predicate]
			atr.syntax returns "text()='hoge'"
			val xpath = new InputXpath{val typeName = "text"}
			xpath.path(atr) must beEqualTo(By.xpath("//input[@type='text' and text()='hoge']"))
		}
	}
}