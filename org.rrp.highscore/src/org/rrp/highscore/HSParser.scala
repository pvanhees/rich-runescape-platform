package org.rrp.highscore

import java.io.FileNotFoundException
import java.net.URLEncoder

import scala.collection.JavaConversions.asScalaBuffer
import scala.io.Source

import org.jsoup.Jsoup

object HSParser {
  
  val skillsUrl = "http://services.runescape.com/m=hiscore/overall.ws?category_type=0"
  val activitiesUrl = "http://services.runescape.com/m=hiscore/overall.ws?category_type=1"
  val hsUrl = "http://hiscore.runescape.com/index_lite.ws"
    
  private var skillsCache:List[String] = null
  private var activitiesCache:List[String] = null
    
  private def dropDownExtract(dropdown: String, url: String): List[java.lang.String] = {
    val dropdownElement = Jsoup.connect(url).get()
	    .getElementById(dropdown)
        
    dropdownElement.getElementsByTag("ul").first().children().map {
      li =>
        li.text
    }.toList
  }
  
  def skills(): List[String] = {
    if (skillsCache == null)
	    skillsCache = dropDownExtract("skillsDropDown", skillsUrl)
	skillsCache
  }
  
  def activities(): List[String] = {
    if (activitiesCache == null)
	    activitiesCache = dropDownExtract("activityDropDown", activitiesUrl)
	activitiesCache    
  }
  
  def stats(user: String): Map[String, (Int, Int, Int)] = {
    val url = "%s?player=%s".format(hsUrl, URLEncoder.encode(user, "UTF-8"))
    try {
      (skills ++ activities).zip(Source.fromURL(url).getLines.map {
        ln =>
          ln.split(",") match {
            case Array(rank, level, exp) =>
              (rank.toInt, level.toInt, exp.toInt)
              
            case Array(rank, score) =>
              (rank.toInt, score.toInt, -1)
              
            case _ =>
              throw new RuntimeException("Unkown format from Highscore service")
          }
      }.toList).toMap
    } catch {
      case e:FileNotFoundException =>
	      throw new IllegalArgumentException("No player named " + user)
    }
  }
 
}