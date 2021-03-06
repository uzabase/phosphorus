package com.uzabase.phosphorus

class ConfigSpec extends UnitSpecification {

	"設定情報" should {
		"テスト対象となるアプリケーションのURLはapplication.urlがプロパティーファイルのキーとなる" in {
			Config().applicationUrl must beEqualTo("http://localhost:8080/myproject")
		}
		"chromeDriverの設定がされている" in {
			Config().isChrome must beTrue
		}
		"chromeのDriverはchrome.driver.urlがプロパティーファイルのキーとなる" in {
			Config().chromeDriverUrl must beEqualTo("/opt/google/chrome/chromedriver")
		}
		"chromeの実行ファイルはchrome.binaryがプロパティーファイルのキーとなる" in {
			Config().chromeBinary must beEqualTo("/opt/google/chrome/chrome")
		}
		"Selenium Gridを使用はremoteがプロパティーファイルのキーとなる" in {
			Config().isRemote must beTrue
		}
		"Internet ExprolerのDriverはie.driver.urlがプロパティーファイルのキーとなる" in {
			Config().ieDriverUrl must beEqualTo("c:\\hoge\\ie")
		}
		"IE Driverが設定されている" in {
			Config().isIE must beTrue
		}
		"Profileが設定されている" in {
			Config().isProfile must beTrue
		}
		"Profile名はprofile.nameがプロパティーファイルのキーとなる" in {
			Config().profileName must beEqualTo("prop")
		}
	}
}