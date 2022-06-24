package com.akyuzg.rapsodomotiontracker.presentation.detail

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akyuzg.rapsodomotiontracker.data.local.dto.Coordinate
import com.akyuzg.rapsodomotiontracker.domain.model.Position
import com.akyuzg.rapsodomotiontracker.domain.usecase.RecordUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordAndReplayViewModel @Inject  constructor(
    private val recordUseCases: RecordUseCases
): ViewModel() {

    var recordId = 0L
    var recording = false
    var finished = false

    val status = MutableLiveData("")
    val recordable = MutableLiveData(false)


    fun getPositions(): Flow<List<Coordinate>> {
        return recordUseCases.getPositions(recordId)
    }

    fun insertPosition(position: Position) = viewModelScope.launch {
        recordUseCases.insertPosition(recordId, position)
    }

    fun startRecording() {
        recording = true
        recordable.value = false
        status.value = RecordStatus.RECORDING.status
    }

    fun recordFinished() {
        finished = true
        recording = false
        recordable.value = false
        status.value = RecordStatus.FINISHED.status
    }


}