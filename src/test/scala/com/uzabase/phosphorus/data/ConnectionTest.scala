package com.uzabase.phosphorus.data

import org.junit.Test
import mockit.Mocked
import mockit.Expectations
import mockit.Expectations.result
import org.dbunit.JdbcDatabaseTester
import org.junit.Assert._
import org.hamcrest.CoreMatchers._
import org.dbunit.database.IDatabaseConnection
import org.dbunit.dataset.IDataSet
import org.dbunit.dataset.ITable
import scala.collection.immutable.List
import org.dbunit.operation.DatabaseOperation
import org.junit.Ignore

class ConnectionTest {

  @Mocked
  var prop:JdbcProperties = null
  @Mocked
  var database:JdbcDatabaseTester = null
  @Mocked
  var conn: IDatabaseConnection = null
  @Mocked
  var dataSet: IDataSet = null
  @Mocked
  var replace: IDataSet = null
  @Mocked
  var table: ITable = null
  @Mocked
  var table2: ITable = null
  @Mocked
  var operation: DatabaseOperation = null
  
  @Test
  def driver_url_username_passwordの情報を基にJdbcDatabaseTesterを生成する:Unit = {
    new Expectations {{
      prop.driverClassName;result = "className";
      prop.url;result = "url";
      prop.username;result = "name";
      prop.password;result = "pass";
      database = new JdbcDatabaseTester("className","url","name","pass")
      database.getConnection;result = conn
    }}
    Connection(prop)
  }
  
  @Test
  def 指定したテーブル名に関する情報を取得する:Unit = { 
    new Expectations{{
      conn.createDataSet;result = dataSet
      dataSet.getTable("COMPANY");result = table
    }}
    assertThat(new Connection(conn,database).table("COMPANY"), is(table))
  }
  
  @Test
  def 指定した複数のテーブルに関する情報を取得する:Unit = {
    new Expectations{{
      conn.createDataSet;result = dataSet
      dataSet.getTable("COMPANY");result = table
      dataSet.getTable("EMPLOYEE");result = table2
    }}
    val expected:Seq[ITable] = List(table,table2)
    assertThat(new Connection(conn,database).table(List("COMPANY","EMPLOYEE")), is(expected))
  }
  @Ignore
  @Test
  def 指定したデータセットの情報にてデータを置き換える:Unit = {
    new Expectations{{
      operation.execute(conn, replace)
    }}
    new Connection(conn,database).cleanInsert(replace)
  }
}