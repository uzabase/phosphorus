package com.uzabase.selenium

import org.specs2.matcher.Matcher
import org.specs2.matcher.Expectable
import com.uzabase.selenium.elements.DropDown
import com.uzabase.selenium.elements.Item
import com.uzabase.selenium.elements.Displayable
import com.uzabase.selenium.elements.Displayable
import com.uzabase.selenium.elements.Displayable

trait CoreMatchers {
	def beDisplayed = new ElementMatcher((element:Displayable)=>element.displayed,s"element are displayed. ",s"element are not displayed. ")
	def haveOption(options:String*) = new DropDownMatcher(options)
}

class ElementMatcher(val result:(Displayable)=> Boolean,ok: String, ko: String) extends Matcher[Displayable]{
	def apply[E <: Displayable](e: Expectable[E]) = result(result(e.value),ok,ko,e)
}

class DropDownMatcher(expectOptions:Seq[String]) extends Matcher[DropDown] {
	def apply[E <: DropDown](e: Expectable[E]) = {
		val byExpect = expectOptions.filterNot(option => e.value.optionsText.contains(option))
		val byDropDown = e.value.optionsText.filterNot(option => expectOptions.contains(option))
		result(byExpect.isEmpty && byDropDown.isEmpty,"ok",s"expected options is ${expectOptions}, but actual ${e.value.optionsText} ",e)
	}
}