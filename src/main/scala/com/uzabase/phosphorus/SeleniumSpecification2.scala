package com.uzabase.phosphorus

import org.specs2._
import org.openqa.selenium.firefox.FirefoxDriver
import org.specs2.specification._
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.concurrent.TimeUnit
import org.dbunit.JdbcDatabaseTester
import org.dbunit.dataset.excel.XlsDataSet
import java.io.File
import org.dbunit.operation.DatabaseOperation
import org.dbunit.util.fileloader.CsvDataFileLoader
import scala.collection.immutable.List
import com.uzabase.phosphorus.data.JdbcProperties
import com.uzabase.phosphorus.data.Connection
import org.dbunit.dataset.ITable

abstract class SeleniumSpecification2 extends Specification {
  
	var cashed:Seq[ITable] = null
	
	def setUp = {
		val xls = new XlsDataSet(Thread.currentThread.getContextClassLoader.getResourceAsStream("Expected.xls"))
		val conn = Connection(JdbcProperties())
		cashed = conn.table(xls.getTableNames.toList)
		conn.cleanInsert(xls)
	}

	def cleanUp = {
		
	}

	override def map(fs: => Fragments) = Step(setUp) ^ fs ^ Step(cleanUp)
}