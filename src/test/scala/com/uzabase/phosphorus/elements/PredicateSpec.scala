package com.uzabase.phosphorus.elements

import org.openqa.selenium.By
import org.junit.runner.RunWith
import com.uzabase.phosphorus.UnitSpecification

class PredicateSpec extends UnitSpecification{

	"Xpath構文" should {
		"指定されたタグ名を特定するためのByオブジェクトを取得する" in {
			val predicate = new Predicate{
				def syntax = "hoge"
			}
			predicate.by("div") must beEqualTo(By.xpath("//div[hoge]"))
		}
	}
}