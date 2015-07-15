package com.uzabase.phosphorus.elements

import com.uzabase.phosphorus.UnitSpecification

class HasTagNameSpec extends UnitSpecification{

	"tag名" should {
		"クラス名の文字列を小文字にしたものがタグ名となる" in {
			Sample().tagName must beEqualTo("sample")
		}
	}
}

case class Sample() extends HasTagName