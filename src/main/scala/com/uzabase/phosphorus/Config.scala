package com.uzabase.phosphorus

import java.util.Properties

class Config(val properties: Properties) {
  def applicationUrl = properties.getProperty("application.url")
  def chromeDriverUrl = properties.getProperty("chrome.driver.url")
  def chromeBinary = properties.getProperty("chrome.binary")
  def ieDriverUrl = properties.getProperty("ie.driver.url")
  def lang = properties.getProperty("browser.lang")
  def isChrome = chromeDriverUrl != null && !chromeDriverUrl.isEmpty
  def isLang = lang != null && !lang.isEmpty
  def isRemote = {
    val remote = properties.getProperty("remote")
    if (remote == null || remote.isEmpty() || remote.toLowerCase() == "false")
      false
    else
      true
  }
}

object Config {

  private lazy val defaultFile = System.getProperty("uat.property.file.name")
  private val properties = new Properties

  def apply() = getProp match {
    case Some(s) => create(s)
    case None => create("uat.properties")
  }
  private def create(fileName: String) = {
    properties.load(Thread.currentThread.getContextClassLoader.getResourceAsStream(fileName))
    new Config(properties)
  }

  private def getProp: Option[String] = if (defaultFile != null && !defaultFile.isEmpty) Some(defaultFile) else None
}