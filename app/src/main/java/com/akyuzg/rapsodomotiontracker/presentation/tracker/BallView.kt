package com.akyuzg.rapsodomotiontracker.presentation.tracker

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.akyuzg.rapsodomotiontracker.domain.model.Position
import kotlin.math.abs

class BallView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private var point = Position(x = 0f, y = 0f, z = 0f)
    private var velocityY = 0f
    private var started: Boolean = false

    companion object {
        const val VELOCITY = 10f
        const val THRESHOLD = 0.01f
    }

    fun startMoveIfEligable(accelerateY: Float) {
        started = true

        if(abs(accelerateY) > THRESHOLD){
            velocityY = if (accelerateY < 0) VELOCITY else -1 * VELOCITY
        }
        invalidate()
    }

    val paint = Paint().apply {
        color = Color.RED
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if(!started){
            point = Position(width / 2f, height / 2f, 0f )
            canvas?.drawCircle(point.x, point.y, 60f, paint)
            return
        }

        point = Position(point.x, point.y + velocityY, point.z)
        canvas?.drawCircle(point.x, point.y, 60f, paint)
    }

}