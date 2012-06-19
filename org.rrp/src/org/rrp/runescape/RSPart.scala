package org.rrp.runescape

import javax.inject.Inject
import org.eclipse.e4.ui.di.Focus
import org.eclipse.swt.widgets.Composite

class RSPart {
  
  val wrapper = new AppletWrapper("http://www.runescape.com/game.ws?j=1")
  
  @Inject
  def initUI(parent: Composite) = {
    println(parent)
	wrapper.show(parent)
  }
  
  @Focus
  def focus() = {
    println("FOCUSSSSSSSSS")
  }

}