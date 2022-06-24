package com.akyuzg.rapsodomotiontracker.presentation.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.akyuzg.rapsodomotiontracker.databinding.ReplayFragmentBinding
import com.akyuzg.rapsodomotiontracker.domain.usecase.sensor_change.ISensorChangeManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RecordAndReplayFragment: Fragment() {

    private var _binding: ReplayFragmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var sensorChangeManager: ISensorChangeManager

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

        sensorChangeManager.init(context!!)

        sensorChangeManager.listenSensorChanges { values ->
            binding.ballView.startMovingIfEligable(values[0])
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

                if(!viewModel.finished && !viewModel.recording){
                    binding.ballView.play(it)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        sensorChangeManager.onResume()
    }

    override fun onPause() {
        super.onPause()
        sensorChangeManager.onPause()
    }


}