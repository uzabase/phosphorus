package com.uzabase.phosphorus.data

import org.dbunit.JdbcDatabaseTester
import org.dbunit.IDatabaseTester
import org.dbunit.database.IDatabaseConnection
import org.dbunit.dataset.IDataSet
import org.dbunit.dataset.ITable
import org.dbunit.operation.DatabaseOperation

class Connection(val con: IDatabaseConnection, val tester:IDatabaseTester) {
  private lazy val dataSet = con.createDataSet
  def table(name: String) = dataSet.getTable(name) 
  def table(nameList: Seq[String]):Seq[ITable] = nameList.map(name=> dataSet.getTable(name))
  def cleanInsert(target:IDataSet) = DatabaseOperation.CLEAN_INSERT.execute(con, target)
}
object Connection {
  def apply(property: JdbcProperties) = {
    val tester = new JdbcDatabaseTester(property.driverClassName,property.url,property.username,property.password)
    new Connection(tester.getConnection,tester)
  }
}