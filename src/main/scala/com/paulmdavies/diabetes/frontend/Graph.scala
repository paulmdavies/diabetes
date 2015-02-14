package com.paulmdavies.diabetes.frontend

import scalafx.scene.chart.NumberAxis
import scalafx.scene.chart.LineChart
import scalafx.scene.chart.XYChart
import scalafx.collections.ObservableBuffer
import scalafx.Includes._

class Graph()
{
    var i = 2
    
    val xAxis = new NumberAxis
    val yAxis = new NumberAxis
    
    val lc = new LineChart( xAxis, yAxis )
    
    val series = new XYChart.Series[Number,Number]
    
    private [frontend] var data = ObservableBuffer( Seq(
        ( 0, 0 ),
        ( 1, 1 )
    ).map{ case ( x, y ) => XYChart.Data[Number, Number]( x, y ) } )
    
    series.data = data
    
    lc.getData.add( series )
    
    def apply() : LineChart[Number,Number] = lc
    
    def add( x : Int, y : Int ) : Unit =
    {
        println( "Adding new data point (%d,%d)".format( x, y ) )
        data :+= XYChart.Data[Number, Number]( x, y )
    }
    
    def add() : Unit =
    {
        add( i, i )
        i += 1
    }
}