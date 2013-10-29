package com.uzabase.phosphorus

import java.util.Properties

class Config(val properties: Properties) {
	def applicationUrl = properties.getProperty("application.url")
	def chromeDriverUrl = properties.getProperty("chrome.driver.url")
	def isChrome = chromeDriverUrl != null && !chromeDriverUrl.isEmpty
}

object Config {
	def apply() = {
		val fileName = if(getProperty != null) getProperty else "uat.properties"
		val properties = new Properties
		properties.load(Thread.currentThread.getContextClassLoader.getResourceAsStream(fileName))
		new Config(properties)
	}
	private def getProperty = System.getProperty("uat.property.file.name")
}