package com.paulmdavies.diabetes.frontend

import org.joda.time._
import scalafx.scene.layout.GridPane
import scalafx.scene.control._
import scalafx.Includes._

class BloodSugarEntryPane( private [frontend] val callback : ( DateTime, Double ) => Unit ) extends GridPane
{
    //private [frontend] val datePicker = new DatePicker
    
    val submitButton = new Button( "Submit" ) {
        onMouseClicked = handle( { 
            submit()
        } )
    }
    
	val bloodSugarField = new TextField()
    {
        def apply() : Double =
        {
            this.text.value.toDouble
        }
        
        onKeyReleased = handle {
            println( "Typed" )
            val value = this.text.value
            try 
            {
                value.toDouble
                println( "Good Value: %s".format( value ) )
                submitButton.disable = false
            }
            catch
            {
                case e : NumberFormatException => 
                {
                    println( "Bad Value: %s".format( value ) )
                    submitButton.disable = true 
                }
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
    //this.add( datePicker, 0, 1 )
    
    private [frontend] def submit() : Unit =
    {
        val time = DateTime.now()
        val reading = bloodSugarField.apply()
        println( "Submitting reading: time %s and value %f".format( time, reading ) )
        
        
    }
}