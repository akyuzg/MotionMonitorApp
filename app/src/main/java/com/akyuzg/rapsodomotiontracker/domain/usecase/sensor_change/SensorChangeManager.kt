package com.akyuzg.rapsodomotiontracker.domain.usecase.sensor_change

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class SensorChangeManager: ISensorChangeManager(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var linearAccelerationSensor: Sensor

    private var listener: ((values:FloatArray) -> Unit?)? = null


    override fun init(context: Context) {
        this.sensorManager = context?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)?.let {
            this.linearAccelerationSensor = it
        }
    }

    override fun listenSensorChanges(listener: ((values:FloatArray) -> Unit?)?) {
        this.listener = listener
    }


    override fun onResume() {
        sensorManager.registerListener(this, linearAccelerationSensor, SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            if (event.sensor.type == Sensor.TYPE_GYROSCOPE) {
                listener?.invoke(it.values)
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

}