package com.uzabase.selenium.elements

import com.uzabase.selenium.UnitSpecification

class HasTagNameSpec extends UnitSpecification{

	"tag名" should {
		"クラス名の文字列を小文字にしたものがタグ名となる" in {
			Sample().tagName must beEqualTo("sample")
		}
	}
}

case class Sample extends HasTagName