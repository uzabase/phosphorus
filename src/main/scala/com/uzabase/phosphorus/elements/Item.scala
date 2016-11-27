package com.uzabase.phosphorus.elements

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
	
	def attribute(atrName:String) = webElement.getAttribute(atrName)
	
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
object Element extends ElementCompanion[Element]{
	def apply(xpath:String)(implicit driver:WebDriver):Element = apply(driver.findElement(By.xpath(xpath)))
	def apply(xpath:String,wait:WebDriverWait)(implicit driver:WebDriver):Element = {
		until(wait, By.xpath(xpath));
		apply(driver.findElement(By.xpath(xpath)))
	}
}

case class Div(webElement:WebElement) extends Item
object Div extends ElementCompanion[Div]{
	override def tagName = "div"
}

case class Image(webElement:WebElement) extends Item
object Image extends ElementCompanion[Image]{
	override def tagName = "img"
}

case class Anchor(webElement:WebElement) extends Item
object Anchor extends ElementCompanion[Anchor] {
	override def tagName = "a"
	def apply(text:String)(implicit driver: WebDriver):Anchor = apply(text_(text))
}

case class Span(webElement:WebElement) extends Item
object Span extends ElementCompanion[Span] {
	override def tagName = "span"
}

case class Ul(webElement:WebElement) extends Item
object Ul extends ElementCompanion[Ul]{
	override def tagName = "ul"
}

case class Li(webElement:WebElement) extends Item
object Li extends ElementCompanion[Li]  {
	override def tagName = "li"
}

case class Em(webElement:WebElement) extends Item
object Em extends ElementCompanion[Em]  {
	override def tagName = "em"
}

case class Button(webElement:WebElement) extends Item
object Button extends ElementCompanion[Button] {
	override def tagName = "button"
}

case class H1(webElement:WebElement) extends Item
object H1 extends ElementCompanion[H1] {
	override def tagName = "h1"
}

case class H2(webElement:WebElement) extends Item
object H2 extends ElementCompanion[H2] {
	override def tagName = "h2"
}

case class H3(webElement:WebElement) extends Item
object H3 extends ElementCompanion[H3] {
	override def tagName = "h3"
}

case class Dt(webElement:WebElement) extends Item
object Dt extends ElementCompanion[Dt] {
	override def tagName = "dt"
}
case class Dd(webElement:WebElement) extends Item
object Dd extends ElementCompanion[Dd] {
	override def tagName = "dd"
}

case class Label(webElement:WebElement) extends Item
object Label extends ElementCompanion[Label] {
	override def tagName = "label"
}

case class Th(webElement:WebElement) extends Item
object Th extends ElementCompanion[Th] {
	override def tagName = "th"
}

case class Td(webElement:WebElement) extends Item
object Td extends ElementCompanion[Td] {
	override def tagName = "td"
}

case class Paragraph(webElement:WebElement) extends Item
object Paragraph extends ElementCompanion[Paragraph] {
	override def tagName = "p"
}


trait ElementCompanion[E <: Item] extends HasUntil with HasTagName {
	def apply(element:WebElement):E
	def apply(by:HasBy)(implicit driver:WebDriver):E = apply(driver.findElement(by.by(tagName)))
	def apply(by:HasBy,wait:WebDriverWait)(implicit driver:WebDriver):E = {
		until(wait, by.by(tagName))
		apply(by)
	}
	def elements(xpath:String)(implicit driver:WebDriver):Seq[E] = {
		import scala.collection.JavaConversions.asScalaBuffer
		driver.findElements(By.xpath(xpath)).map(e => apply(e))
	}
		def elements(xpath:String,wait:WebDriverWait)(implicit driver:WebDriver):Seq[E] = {
		import scala.collection.JavaConversions.asScalaBuffer
		until(wait,By.xpath(xpath))
		driver.findElements(By.xpath(xpath)).map(e => apply(e))
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

case class EmptyItem() extends Item {
	val webElement:WebElement = null
	override def displayed = false
	override def click{}
	override def text = ""
	override def dragAndDrop(target:Item)(implicit driver:WebDriver) = new Actions(driver)
	override def dragAndDrop(xOffset:Int,yOffset:Int)(implicit driver:WebDriver) = new Actions(driver)
}