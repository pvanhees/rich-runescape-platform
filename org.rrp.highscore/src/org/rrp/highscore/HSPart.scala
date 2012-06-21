package org.rrp.highscore

import javax.inject.Inject
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.SWT
import org.eclipse.swt.graphics.Color
import org.eclipse.e4.core.di.annotations.Execute
import org.eclipse.e4.ui.di.Focus

class HSPart @Inject() (parent: Composite) {
  
  println("CREATION")
  parent.setBackground(new Color(null, 50, 50, 200))
  
  @Focus
  def focus() = {
    println("FOCUSSSSSSSSSS")
  }

}