package com.example.investsmartapp.ui.screens

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.CandleStickChart
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.utils.ColorTemplate

@Composable
fun CandlestickChart(symbol: String, candlestickData: List<List<Any>>) {
    val entries = candlestickData.mapIndexed { index, data ->
        val open = (data[1] as String).toFloat()
        val high = (data[2] as String).toFloat()
        val low = (data[3] as String).toFloat()
        val close = (data[4] as String).toFloat()
        CandleEntry(index.toFloat(), high, low, open, close)
    }

    val candleDataSet = CandleDataSet(entries, "Candlestick Data").apply {
        setDrawIcons(false)
        shadowColor = ColorTemplate.getHoloBlue()
        shadowWidth = 0.7f
        decreasingColor = ColorTemplate.rgb("FF0000")
        decreasingPaintStyle = Paint.Style.FILL
        increasingColor = ColorTemplate.rgb("00FF00")
        increasingPaintStyle = Paint.Style.STROKE
    }

    val candleData = CandleData(candleDataSet)

    AndroidView(
        factory = { context: Context ->
            CandleStickChart(context).apply {
                data = candleData
                invalidate()
            }
        },
        modifier = Modifier.fillMaxWidth().height(300.dp)
    )
}
