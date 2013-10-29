package com.uzabase.selenium.elements

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

trait Item extends Displayable with Selectable 
								with HasText 
								with Clickable 
								with HasEnable 
								with HasDragAndDrop{
	
	def displayed = webElement.isDisplayed
	
	override def hashCode = {
		val prime = 31
		var result = 1
		prime * result + (if (webElement == null) 0 else webElement.hashCode)
	}
	
	override def equals(other:Any) = other match {
		case el:Item => el.webElement == webElement
		case _ => false
	} 
}

case class Element(webElement:WebElement) extends Item
object Element extends HasUntil{
	def apply(by:Predicate,xpath:String)(implicit driver:WebDriver):Element = new Element(driver.findElement(By.xpath(xpath)))
	def apply(by:Predicate,xpath:String,wait:WebDriverWait)(implicit driver:WebDriver):Element = {
		until(wait, By.xpath(xpath))
		new Element(driver.findElement(By.xpath(xpath)))
	}
}

case class Div(webElement:WebElement) extends Item
object Div extends ElementCompanyon[Div]

case class Anchor(webElement:WebElement) extends Item
object Anchor extends ElementCompanyon[Anchor] {
	override def tagName = "a"
	def apply(text:String)(implicit driver: WebDriver):Anchor = apply(text_(text))
}

case class Span(webElement:WebElement) extends Item
object Span extends ElementCompanyon[Span]

case class Ul(webElement:WebElement) extends Item
object Ul extends ElementCompanyon[Ul]

case class Li(webElement:WebElement) extends Item
object Li extends ElementCompanyon[Li]  

trait ElementCompanyon[E <: Item] extends HasUntil with HasTagName {
	def apply(element:WebElement):E
	def apply(by:Predicate)(implicit driver:WebDriver):E = apply(driver.findElement(by.by(tagName)))
	def apply(by:Predicate,wait:WebDriverWait)(implicit driver:WebDriver):E = {
		until(wait, by.by(tagName))
		apply(by)
	}
}

trait HasUntil {
	def until(wait: WebDriverWait, by:By) = wait.until(ExpectedConditions.presenceOfElementLocated(by))
}
trait HasWebElement {
	val webElement:WebElement
}
trait HasValue extends HasWebElement{
	def value = webElement.getAttribute("value")
}

trait Displayable {
	def displayed:Boolean
}
trait Selectable extends HasWebElement {
	def selected = webElement.isSelected
}
trait HasText extends HasWebElement {
	def text = webElement.getText
}
trait HasEnable extends HasWebElement {
	def enabled = webElement.isEnabled
}
trait Clickable extends HasWebElement {
	def click = webElement.click
}
trait HasTagName {
	def tagName:String = getClass.getSimpleName.toLowerCase
}
trait HasDragAndDrop extends HasWebElement{
	def dragAndDrop(target:Item)(implicit driver:WebDriver) = new Actions(driver).dragAndDrop(webElement, target.webElement)
	def dragAndDrop(xOffset:Int,yOffset:Int)(implicit driver:WebDriver) = new Actions(driver).dragAndDropBy(webElement, xOffset, yOffset)
}

case class EmptyItem extends Item {
	val webElement:WebElement = null
	override def displayed = false
	override def click{}
	override def text = ""
	override def dragAndDrop(target:Item)(implicit driver:WebDriver) = new Actions(driver)
	override def dragAndDrop(xOffset:Int,yOffset:Int)(implicit driver:WebDriver) = new Actions(driver)
}