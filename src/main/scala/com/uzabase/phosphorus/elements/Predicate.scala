package com.uzabase.phosphorus.elements

import org.openqa.selenium.By

trait Predicate extends HasBy{
	def fullSyntax:String = s"[${syntax}]"
	def syntax:String
	def by(tagName:String):By = By.xpath("//"+tagName+fullSyntax)
}
trait Condition extends HasBy

trait HasBy {
  def by(conditionString:String):By
}

case class or(c1:Predicate,c2:Predicate) extends Condition {
  def by(tagName:String):By = By.xpath(s"""//${tagName}[ ${c1.syntax} or ${c2.syntax} ]""")
}

case class and(c1:Predicate,c2:Predicate) extends Condition {
  def by(tagName:String):By = By.xpath(s"""//${tagName}[ ${c1.syntax} and ${c2.syntax} ]""")
}

trait ElementSelectable extends Predicate
trait Attributable extends Predicate 
case class id(value:String) extends Attributable {def syntax = s"contains(@id,'${value}')"}
/** textがspecs2にいるのでアンダースコア付きにしてます。タイポじゃないよ！ */
case class text_(value:String) extends ElementSelectable {def syntax = s"""text()="${value}""""}
case class contains_(value:String) extends ElementSelectable {def syntax = s"""contains(text(),"${value}")"""}
case class class_(value:String) extends ElementSelectable {def syntax = s"@class='${value}'"}
case class name(value:String) extends Attributable {def syntax = s"@name='${value}'"}
case class value_(value:String) extends ElementSelectable {def syntax = s"@value='${value}'"}
case class selected() extends Attributable {def syntax = "@selected='selected'"}