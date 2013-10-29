package com.uzabase.selenium.elements

import com.uzabase.selenium.UnitSpecification
import org.openqa.selenium.By

class InputXpathSpec extends UnitSpecification{

	"inputタグにおけるXpath構文" should {
		"type名及び指定された構文にてinputタグを特定するXpath構文のByオブジェクトを取得する" in {
			val predicate = mock[Predicate]
			predicate.syntax returns "@name='hoge'"
			new InputXpath{val typeName = "text"}.path(predicate) must beEqualTo(By.xpath("//input[@type='text' and @name='hoge']"))
		}
	}
}