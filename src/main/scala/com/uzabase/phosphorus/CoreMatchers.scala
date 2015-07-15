package com.uzabase.phosphorus

import org.specs2.matcher.Matcher
import org.specs2.matcher.Expectable
import com.uzabase.phosphorus.elements.DropDown
import com.uzabase.phosphorus.elements.Displayable
import com.uzabase.phosphorus.elements.Item
import org.openqa.selenium.NoSuchElementException
import scala.util.control.NonFatal

trait CoreMatchers {
	def beDisplayed = new ElementMatcher((element:Displayable)=>element.displayed,s"element are displayed. ",s"element are not displayed. ")
	def haveOption(options:String*) = new DropDownMatcher(options)
	def notExist = new ExistMatcher
  
}

class ExistMatcher extends Matcher[Item] {
	def apply[E <: Item](expectable: Expectable[E]) = {
		try {
			val item = expectable.value
			result(false,"ok","expected to not exists, but exists. ",expectable)
		}catch {
			case e: NoSuchElementException => result(true,"ok","",expectable)
			case NonFatal(e) => result(false,"ok",s" Oops ! ${e.getStackTrace()}",expectable)
		}
	}
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