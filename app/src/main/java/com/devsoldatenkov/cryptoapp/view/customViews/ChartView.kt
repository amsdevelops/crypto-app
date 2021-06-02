package com.devsoldatenkov.cryptoapp.view.customViews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.devsoldatenkov.remote.entity.CoinHistoryDto

class ChartView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null) : View(context, attributeSet) {
    private val path = Path()
    private var priceStep = 0f
    private var stepX = 0f
    private var maxPrice = 0f
    private var minPrice = 0f
    private var currentPrice = 0f
    private var priceInterval = 0f
    private var coinDataHistoryData = listOf<CoinHistoryDto>()
    private val scale = 0.8f
    private val _matrix = Matrix()
    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 5f
        color = Color.YELLOW
    }
    private val dashedPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 5f
        color = Color.GRAY
        pathEffect = DashPathEffect(floatArrayOf(30f, 20f), 0f)
    }
    private val maxPriceTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL_AND_STROKE
        strokeWidth = 2f
        textSize = 50f
        typeface = Typeface.SANS_SERIF
        color = Color.GREEN
        textAlign = Paint.Align.RIGHT
    }
    private val minPriceTextPaint = Paint(maxPriceTextPaint).apply {
        color = Color.RED
    }
    private val currentPriceTextPaint = Paint(maxPriceTextPaint).apply {
        color = Color.WHITE
        textAlign = Paint.Align.LEFT
    }

    override fun onDraw(canvas: Canvas) {
        if (coinDataHistoryData.isEmpty()) return
        drawChart(canvas)
        drawPriceLine(canvas, LineType.CURRENT)
        drawPriceLine(canvas, LineType.MAX)
        drawPriceLine(canvas, LineType.MIN)
    }

    private fun drawPriceLine(canvas: Canvas, lineType: LineType) {
        var y = 0f
        var paint = maxPriceTextPaint
        var text = maxPrice

        when (lineType) {
            LineType.MIN -> {
                y = height.toFloat()
                paint = minPriceTextPaint
                text = minPrice
            }
            LineType.CURRENT -> {
                y = height - (currentPrice - minPrice) * priceStep
                paint = currentPriceTextPaint
                text = currentPrice
            }
            else -> {}
        }

        path.reset()
        path.moveTo(0f, y)
        path.lineTo(width.toFloat(), y)
        _matrix.setScale(scale, scale, width / 2f, height / 2f)
        path.transform(_matrix)
        canvas.drawPath(path, dashedPaint)
        val maxPriceText = String.format("%.2f", text) + "$"
        canvas.drawTextOnPath(maxPriceText, path, 5f, 0f, paint)
    }

    private fun drawChart(canvas: Canvas) {
        stepX = width / coinDataHistoryData.size.toFloat()
        maxPrice = coinDataHistoryData.maxByOrNull { it.priceUsd }?.priceUsd?.toFloat() ?: 0f
        minPrice = coinDataHistoryData.minByOrNull { it.priceUsd }?.priceUsd?.toFloat() ?: 0f
        currentPrice = coinDataHistoryData.last().priceUsd.toFloat()
        calculateHeight()

        coinDataHistoryData.forEachIndexed { index, data ->
            val x = index * stepX
            val y = height - (data.priceUsd.toFloat() - minPrice) * priceStep
            when (index) {
                0 -> {
                    path.reset()
                    path.moveTo(x + stepX, y)
                }
                else -> {
                    path.lineTo(x, y)
                }
            }
        }
        _matrix.setScale(scale, scale, width / 2f, height / 2f)
        path.transform(_matrix)
        canvas.drawPath(path, linePaint)
    }

    fun setCoinHistoryData(list: List<CoinHistoryDto>) {
        coinDataHistoryData = list
        invalidate()
    }

    private fun calculateHeight() {
        priceInterval = maxPrice - minPrice
        priceStep = height / priceInterval
    }


    enum class LineType {
        MAX,
        MIN,
        CURRENT
    }
}