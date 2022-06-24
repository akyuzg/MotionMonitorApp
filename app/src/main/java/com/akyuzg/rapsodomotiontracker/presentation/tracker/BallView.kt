package com.akyuzg.rapsodomotiontracker.presentation.tracker

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.akyuzg.rapsodomotiontracker.data.local.dto.Coordinate
import com.akyuzg.rapsodomotiontracker.domain.model.Position
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.math.abs

class BallView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private var _point: Position? = null
    private val point get() = _point!!

    private var currentVelocity = 0f
    private var started: Boolean = false

    companion object {
        const val VELOCITY = 8f
        const val THRESHOLD = 0.01f
        const val RADIUS = 60f
    }

    fun startMovingIfEligable(gyroX: Float) {
        started = true

        if(abs(gyroX) > THRESHOLD){
            currentVelocity = if (gyroX < 0) -1 * VELOCITY else VELOCITY
        }
        invalidate()
    }

    val paint = Paint().apply {
        color = Color.RED
    }

    fun pointFlow(): Flow<Position> =  flow {
        for (i in 1..100) {
            delay(100)
            emit(point)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if(_point == null){
            _point = Position(width / 2f, height / 2f, 0f )
            canvas?.drawCircle(point.x, point.y, RADIUS, paint)
            return
        }

        _point = Position(point.x, point.y + currentVelocity, point.z)
        canvas?.drawCircle(point.x, point.y, RADIUS, paint)
    }

    fun play(positions: List<Coordinate>) {
         GlobalScope.launch {
                for(pos in positions){
                    _point = Position(pos.x, pos.y, pos.z)
                    invalidate()
                    Thread.sleep(100)
                }
            }
    }

}