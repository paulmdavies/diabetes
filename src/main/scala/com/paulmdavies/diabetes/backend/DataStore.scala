package com.paulmdavies.diabetes.backend

import org.joda.time._

object DataStore
{
    def apply() : DataStore = new DataStore()
}

class DataStore private [backend] ()
{
    private [backend] var bloodSugarReadings = Map[DateTime,Double]() 
    
    def addBloodSugarReading( time : DateTime, level : Double ) : Unit =
    {
        bloodSugarReadings += ( time -> level )
    }
    
    def readings( intervalOpt : Option[Interval] = None ) : Seq[(DateTime,Double)] = 
    {
        ( intervalOpt match
        {
            case Some( interval ) => bloodSugarReadings.filter( reading => interval.contains( reading._1 ) )
            case None => bloodSugarReadings
        } ).toSeq.sortBy( _._1.getMillis )
    }
}