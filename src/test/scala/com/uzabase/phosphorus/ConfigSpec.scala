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
  }
}