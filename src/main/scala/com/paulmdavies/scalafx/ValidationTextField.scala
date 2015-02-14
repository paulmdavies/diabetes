package com.paulmdavies.scalafx

import scalafx.scene.control.TextField
import scalafx.Includes._

abstract class ValidationTextField( callback : Boolean => Unit ) extends TextField
{
    def validate() : Boolean
    
    onKeyReleased = handle { callback( validate() ) }
}