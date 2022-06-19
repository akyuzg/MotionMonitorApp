package com.akyuzg.rapsodomotiontracker.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akyuzg.rapsodomotiontracker.domain.model.Record
import com.akyuzg.rapsodomotiontracker.domain.usecase.RecordUseCases
import com.akyuzg.rapsodomotiontracker.domain.utils.RecordHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordListingViewModel @Inject constructor(
    private val recordUseCases: RecordUseCases
): ViewModel() {

    fun allRecords(): Flow<List<Record>> = recordUseCases.getRecords()

    fun createRecord() = viewModelScope.launch {
        val record = Record(
            name = RecordHelper.getRandomName(),
            description = RecordHelper.getRandomDescription(),
            createdAt = System.currentTimeMillis()
        )

        recordUseCases.createRecord(record)
    }
}