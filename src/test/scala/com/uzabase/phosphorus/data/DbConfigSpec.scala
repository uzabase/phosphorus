package com.uzabase.phosphorus.data

import com.uzabase.phosphorus.UnitSpecification

class DbConfigSpec extends UnitSpecification{

  "データベース接続情報" should {
    "テスト対象となるデータベースの接続URLはjdbc.urlがプロパティーファイルのキーとなる" in {
      JdbcProperties().url must beEqualTo("jdbc:derby://localhost:1527/test")
    }
    "DriverClassの情報はjdbc.driverClassNameがプロパティーファイルのキーとなる" in {
      JdbcProperties().driverClassName must beEqualTo("org.apache.derby.jdbc.ClientDriver")
    }
    "接続ユーザー名はjdbc.usernameがプロパティーファイルのキーとなる" in {
      JdbcProperties().username must beEqualTo("test")
    }
    "接続パスワードはjdbc.passwordがプロパティーファイルのキーとなる" in {
      JdbcProperties().password must beEqualTo("test")
    }
  }
}