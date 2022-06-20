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
import androidx.lifecycle.coroutineScope
import com.akyuzg.rapsodomotiontracker.data.mapper.PositionMapper
import com.akyuzg.rapsodomotiontracker.databinding.ReplayFragmentBinding
import com.akyuzg.rapsodomotiontracker.domain.usecase.RecordUseCases
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ReplayFragment: Fragment(), SensorEventListener {

    private var _binding: ReplayFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var sensorManager: SensorManager
    private lateinit var linearAccelerationSensor: Sensor

    @Inject
    lateinit var recordUseCases: RecordUseCases


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
        sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)?.let {
            this.linearAccelerationSensor = it
        }

        binding.startButton.setOnClickListener {
            lifecycle.coroutineScope.launch {
                binding.ballView.pointFlow().collect {
                    recordUseCases.insertPosition(1, it)
                }
            }
        }

        lifecycle.coroutineScope.launch {
            recordUseCases.getPositions(1).collect {
                Log.e("POSITIONS", "size = "+it.size.toString())
            }
        }

    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, linearAccelerationSensor, SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                //Log.e("SENSOR", "X = "+it.values[0].toString()+"- "+"Y = "+it.values[1].toString()+"Z = "+it.values[2].toString())
                binding.ballView.startMovingIfEligable(it.values[0])

            }

        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }
}