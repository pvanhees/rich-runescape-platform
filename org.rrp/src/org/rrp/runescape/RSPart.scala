package org.rrp.runescape

import org.eclipse.e4.ui.di.Focus
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Label
import javax.inject.Inject
import javax.annotation.PostConstruct

class RSPart @Inject() (parent: Composite) {
  
  val wrapper = new AppletWrapper("http://www.runescape.com/game.ws?j=1")
  val comp = new Composite(parent, SWT.EMBEDDED)
  wrapper.show(comp)
  
  @Focus
  def focus() = {
    comp.setFocus
  }

}