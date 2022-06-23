package com.akyuzg.rapsodomotiontracker.presentation.detail

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.akyuzg.rapsodomotiontracker.databinding.ReplayFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecordAndReplayFragment: Fragment(), SensorEventListener {

    private var _binding: ReplayFragmentBinding? = null
    private val binding get() = _binding!!



    private lateinit var sensorManager: SensorManager
    private lateinit var linearAccelerationSensor: Sensor

    private val viewModel: RecordAndReplayViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.recordId = arguments?.getLong("recordId")!!
    }

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

        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel

        this.sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)?.let {
            this.linearAccelerationSensor = it
        }

        binding.recordButton.setOnClickListener {
            viewModel.startRecording()
            lifecycle.coroutineScope.launch {
                binding.ballView.pointFlow()
                    .onCompletion { viewModel.recordFinished() }
                    .collect {
                        viewModel.insertPosition(it)
                    }
            }
        }

        lifecycle.coroutineScope.launch {
            viewModel.getPositions().collect {
                viewModel.recordable.value = it.isEmpty()
                if(!viewModel.recordable.value!!){
                    binding.ballView.play(it)
                }
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
            if (event.sensor.type == Sensor.TYPE_GYROSCOPE) {
                binding.ballView.startMovingIfEligable(it.values[0])
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }
}