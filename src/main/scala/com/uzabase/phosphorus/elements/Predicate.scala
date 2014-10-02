package com.uzabase.phosphorus.elements

import org.openqa.selenium.By

trait Predicate{
	def fullSyntax:String = s"[${syntax}]"
	def syntax:String
	def by(tagName:String):By = By.xpath("//"+tagName+fullSyntax)
}
trait ElementSelectable extends Predicate
trait Attributable extends Predicate 
case class id(value:String) extends Attributable {def syntax = s"contains(@id,'${value}')"}
/** textがspecs2にいるのでアンダースコア付きにしてます。タイポじゃないよ！ */
case class text_(value:String) extends ElementSelectable {def syntax = s"""text()="${value}""""}
case class class_(value:String) extends ElementSelectable {def syntax = s"@class='${value}'"}
case class name(value:String) extends Attributable {def syntax = s"@name='${value}'"}
case class value_(value:String) extends ElementSelectable {def syntax = s"@value='${value}'"}
case class selected() extends Attributable {def syntax = "@selected='selected'"}