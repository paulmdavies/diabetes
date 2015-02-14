package com.paulmdavies.diabetes.frontend

import org.joda.time._
import scalafx.scene.layout.GridPane
import scalafx.scene.control._
import scalafx.Includes._
import com.paulmdavies.scalafx.ValidationTextField

class BloodSugarEntryPane( private [frontend] val callback : ( DateTime, Double ) => Unit ) extends GridPane
{
    val submitButton = new Button( "Submit" ) {
        onMouseClicked = handle( { 
            submit()
        } )
    }
    
	val bloodSugarField = new ValidationTextField( ( good : Boolean ) => submitButton.disable = !good )
    {
        def apply() : Double = this.text.value.toDouble
        
        def validate() : Boolean =
        {
            try
            {
                this.text.value.toDouble
                true
            }
            catch
            {
                case e : NumberFormatException => false
            }
        }
        
        
    }
    
    this.add( new Label( "Blood Sugar Reading" ), 0, 0 )
    this.add( {
        val gp = new GridPane()
        gp.add( new Label( "Reading:" ), 0, 0 )
        gp.add( bloodSugarField, 1, 0 )
        gp
    }, 0, 1 )
    this.add( submitButton, 0, 2 )
    
    private [frontend] def submit() : Unit =
    {
        val time = DateTime.now()
        val reading = bloodSugarField.apply()
        println( "Submitting reading: time %s and value %f".format( time, reading ) )
        
        
    }
}