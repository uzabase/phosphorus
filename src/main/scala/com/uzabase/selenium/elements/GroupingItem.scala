package com.uzabase.selenium.elements

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import scala.util.control.NonFatal

trait GroupingItem[E <: ChoiceableItem] extends Displayable with HasFind{
	val list:Seq[E]
	
	def displayed = list.exists(r => r.displayed)
	
	def choice(target:String)(implicit driver:WebDriver){
		list.find {r => r.label.text == target} match {
			case Some(v) => v.click
			case None => Unit
		}
	}
	
	override def hashCode = {
		val prime = 31
		var result = 1
		prime * result + (if (list == null) 0 else list.hashCode)
	}
	
	override def equals(other:Any) = other match {
		case rc:GroupingItem[E] => rc.list == list
		case _ => false
	}
}

case class RadioChoice(list: Seq[Radio]) extends GroupingItem[Radio]
case class CheckBox(list: Seq[Box]) extends GroupingItem[Box]

object RadioChoice extends GroupingItemCompanyon[Radio,RadioChoice]{
	val typeName = "radio"
	def choiceableItem(el:WebElement,label:Item) = Radio(el,label)
}
object CheckBox extends GroupingItemCompanyon[Box,CheckBox]{
	val typeName = "checkbox"
	def choiceableItem(el:WebElement,label:Item) = Box(el,label)
}

trait GroupingItemCompanyon[I <: ChoiceableItem,E <: GroupingItem[I]] extends HasUntil with InputXpath with HasFind{
	val typeName:String
	def choiceableItem(choiceableElement:WebElement,choiceableLabel:Item):I
	def apply(list:Seq[I]):E
	def apply(nameAttribute:String)(implicit driver:WebDriver):E = {
		import collection.JavaConversions._
		apply(driver.findElements(path(name(nameAttribute))).map{radio:WebElement => 
			try	{
				choiceableItem(radio,Element(find(By.xpath(s"//label[@for='${radio.getAttribute("id")}']"))))
			}catch {
				case t: NoSuchElementException => choiceableItem(radio,EmptyItem())
				case NonFatal(t) => choiceableItem(radio,EmptyItem())
			}
		})
	}
}

case class Radio(webElement: WebElement,label:Item) extends ChoiceableItem
case class Box(webElement: WebElement,label:Item) extends ChoiceableItem
trait ChoiceableItem extends Item {
	val label:Item
}