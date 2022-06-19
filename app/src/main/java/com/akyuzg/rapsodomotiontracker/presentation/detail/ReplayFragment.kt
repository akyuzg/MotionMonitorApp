package com.akyuzg.rapsodomotiontracker.presentation.detail

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.akyuzg.rapsodomotiontracker.databinding.ReplayFragmentBinding

class ReplayFragment: Fragment(), SensorEventListener {

    private var _binding: ReplayFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var sensorManager: SensorManager
    private lateinit var linearAccelerationSensor: Sensor


    var accelerateY = 0f


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ReplayFragmentBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)?.let {
            this.linearAccelerationSensor = it
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, linearAccelerationSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
                accelerateY = it.values[1]
                //Log.e("SENSOR Y", it.values[1].toString()+"- ")
                binding.ballView.startMoveIfEligable(accelerateY)

            }

        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }
}