package com.paulmdavies.diabetes.backend

import org.scalatest._
import Matchers._

import org.joda.time._

class DataStoreTests extends FlatSpec with Matchers 
{
    behavior of "DataStore"
    
    it should "support adding of a reading" in
    {
        val now = DateTime.now
        val ds = DataStore()
        ds.addBloodSugarReading( now, 4.0 )
        ds.bloodSugarReadings( now ) should equal ( 4.0 )
    }
    
    it should "support querying all readings" in 
    {
        val now = DateTime.now
        val earlier = now.minus( Period.seconds( 10 ) )
        val ds = DataStore()
        ds.addBloodSugarReading( earlier, 3.0 )
        ds.addBloodSugarReading( now, 4.0 )
        ds.readings() should equal ( Seq( ( earlier, 3.0 ), ( now, 4.0 ) ) )
    }
    
    it should "return queried readings in chronological order" in 
    {
        val now = DateTime.now
        val earlier = now.minus( Period.seconds( 10 ) )
        val ds = DataStore()
        ds.addBloodSugarReading( now, 4.0 )
        ds.addBloodSugarReading( earlier, 3.0 )
        ds.readings() should equal ( Seq( ( earlier, 3.0 ), ( now, 4.0 ) ) )
    }
    
    it should "support querying readings in a given time interval" in
    {
        val now = DateTime.now
        val earlier = now.minus( Period.seconds( 10 ) )
        val ds = DataStore()
        ds.addBloodSugarReading( now, 4.0 )
        ds.addBloodSugarReading( earlier, 3.0 )
        ds.readings( Some( new Interval( now.minus( Period.seconds( 5 ) ), now.plus( Period.seconds( 5 ) ) ) ) ) should equal ( Seq( ( now, 4.0 ) ) )
    }
}