package com.akyuzg.rapsodomotiontracker.domain.usecase.sensor_change

import android.content.Context

abstract class ISensorChangeManager {
    abstract fun init(context: Context)
    abstract fun listenSensorChanges(listener: ((values:FloatArray) -> Unit?)?)
    abstract fun onResume()
    abstract fun onPause()
}