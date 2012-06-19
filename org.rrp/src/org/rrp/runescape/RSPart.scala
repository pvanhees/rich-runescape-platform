package org.rrp.runescape

import org.eclipse.e4.ui.di.Focus
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Label

import javax.inject.Inject

class RSPart {
  
  val wrapper = new AppletWrapper("http://www.runescape.com/game.ws?j=1")
  
  @Inject
  def initUI(parent: Composite) = {
	wrapper.show(new Composite(parent, SWT.EMBEDDED))
  }
  
  @Focus
  def focus() = {
    println("FOCUSSSSSSSSS")
  }

}