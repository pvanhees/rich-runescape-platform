package org.rrp.runescape

import java.applet.Applet
import java.applet.AppletContext
import java.applet.AppletStub
import java.net.URL
import java.net.URLClassLoader

import scala.collection.mutable.Map

import org.eclipse.swt.awt.SWT_AWT
import org.eclipse.swt.widgets.Composite

class AppletWrapper(gameurl: String) {
  
  val parser = new AppletParser(gameurl)
  
  val stub = new AppletStub(){
    
    def getDocumentBase(): URL = parser.host
    
    def getCodeBase(): URL = parser.host
    
    def appletResize(width:Int , height:Int) = {}
    
    def getAppletContext(): AppletContext = null
    
    def getParameter(key: String): String = {
      adjustParams(parser.params).get(key).getOrElse("")
    }
    
    def isActive(): Boolean = true
    
  }
  
  val clLoader = new URLClassLoader(Array(parser.absoluteGamePack))
  
  val clazz = clLoader.loadClass(parser.mainClass).newInstance
 
  def adjustParams(p:Map[String, String]):Map[String, String] = {
    p("separate_jvm") = "false"
    p("haveie6") = "false"  
    p
  }
  
  def show(parent: Composite) = {
    clazz match {
      case applet: Applet => 
        applet.setStub(stub)
        applet.init()
        applet.start()
        
        val frame = SWT_AWT.new_Frame(parent)
        frame.add(applet)
        
      case _ => throw new ClassCastException
    }
  }

}