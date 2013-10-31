package com.uzabase.phosphorus

import org.specs2.matcher.Expectable

import com.uzabase.phosphorus.elements.DropDown

class DropDownMatcherSpec extends UnitSpecification{
	
	val expectable = mock[Expectable[DropDown]]
	val dropDown = mock[DropDown]

	sequential
	
	"DropDownの検証" should {
		"期待されたoptionの数が実際のDropDownのoptionより足りない場合はNGとなる" in {
			expectable.value returns dropDown
			dropDown.optionsText returns List("1","2","3","4")
			new DropDownMatcher(List("1","2","3")).apply(expectable).isSuccess must beFalse
		}
		"期待されたoptionの数が実際のDropDownのoptionより多い場合はNGとなる" in {
			expectable.value returns dropDown
			dropDown.optionsText returns List("1","2","3")
			new DropDownMatcher(List("1","2","3","4")).apply(expectable).isSuccess must beFalse
		}
		"DropDownのoptionが指定された値と完全に一致する場合はOKとなる" in {
			expectable.value returns dropDown
			dropDown.optionsText returns List("1","2","3")
			new DropDownMatcher(List("1","2","3")).apply(expectable).isSuccess must beTrue
		}
	}
}