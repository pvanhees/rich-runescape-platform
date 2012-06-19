package org.rrp.runescape
import java.net.URL
import org.jsoup.Jsoup

class AppletParser (url: String) {
  
  val loc = Jsoup.connect(url).get().getElementById("game").attr("src")
  val src = Jsoup.connect(loc).get().getElementById("deployJava").toString()
  
  def absoluteGamePack(): URL = new URL("%s/%s".format(host, gamePack)) 
  
  def host() = new URL(loc.substring(0, loc.indexOf(".com/") + 4))
  
  def gamePack(): String = {
    val r = """archive=([^']*)\s'""".r
    r.findAllIn(src).matchData.foreach {
      m => 
        return m.group(1)
    }
    // TODO throw something
    null
  }
 
  def mainClass(): String = {
    val r = """code=([^']*).class""".r
    r.findAllIn(src).matchData.foreach {
      m => 
        return m.group(1)
    }
    // TODO throw something
    null
  }
  
  def params(): scala.collection.mutable.Map[String, String] = {
    val params = scala.collection.mutable.Map[String, String]()
    val r = """name="([^"]*)".*value="([^"]*)"""".r
    r.findAllIn(src).matchData.foreach {
      m => 
        params(m.group(1)) = m.group(2)
    }
    params   
  }

}