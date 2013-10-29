package com.uzabase.selenium.elements

import com.uzabase.selenium.UnitSpecification
import org.openqa.selenium.By
import org.junit.runner.RunWith

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