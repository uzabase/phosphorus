package com.uzabase.phosphorus.data

import java.util.Properties

class JdbcProperties(val properties: Properties) {
  def url = properties.getProperty("jdbc.url")
  def driverClassName = properties.getProperty("jdbc.driverClassName")
  def username = properties.getProperty("jdbc.username")
  def password = properties.getProperty("jdbc.password")
}

object JdbcProperties {
  
  private val properties = new Properties
  
  def apply() = {
    properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"))
    new JdbcProperties(properties)
  }
}