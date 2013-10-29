package com.uzabase.phosphorus

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.specs2.specification.Scope
import org.specs2.mutable._
import org.junit.Ignore

@Ignore("ドライバーが起動してしまうのでIgnore")
class DriverFactorySpec extends UnitSpecification{
	
	"WebDriverの生成" should {
		"設定ファイルにChromeDriverが設定されている場合はChromeDriverを返す" in {
			DriverFactory().create must beAnInstanceOf[ChromeDriver]
		}
		/*"設定ファイルにChromeDriverが設定されていない場合はFirefoxDriverを返す" in new changeProperty{
			DriverFactory().create must beAnInstanceOf[FirefoxDriver]
		}
		trait changeProperty extends Scope with After{
			System.setProperty("uat.property.file.name", "uat2.properties")
			def after = {
				System.setProperty("uat.property.file.name", "")
			}
		}*/
	}
}