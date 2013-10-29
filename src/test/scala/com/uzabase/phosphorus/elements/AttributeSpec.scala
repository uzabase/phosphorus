package com.uzabase.phosphorus.elements

import com.uzabase.phosphorus.UnitSpecification


class AttributeSpec extends UnitSpecification{

	"HTMLエレメントにおける属性" should {
		"idのxpath構文" in {
			id("hoge").fullSyntax must beEqualTo("[contains(@id,'hoge')]")
		}
		"textのxpath構文" in {
			text_("hoge").fullSyntax must beEqualTo("[text()='hoge']")
		}
		"nameのxpath構文" in {
			name("hoge").fullSyntax must beEqualTo("[@name='hoge']")
		}
		"valueのxpath構文" in {
			value_("hoge").fullSyntax must beEqualTo("[@value='hoge']")
		}
		"selectedのxpath構文" in {
			selected().fullSyntax must beEqualTo("[@selected='selected']")
		}
	}
}