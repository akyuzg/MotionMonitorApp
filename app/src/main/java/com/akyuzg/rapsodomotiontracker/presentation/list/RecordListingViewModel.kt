package com.akyuzg.rapsodomotiontracker.presentation.list

import androidx.lifecycle.ViewModel
import com.akyuzg.rapsodomotiontracker.data.local.dto.Record
import com.akyuzg.rapsodomotiontracker.domain.usecase.RecordUseCases
import com.akyuzg.rapsodomotiontracker.domain.utils.RecordFaker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RecordListingViewModel @Inject constructor(
    private val recordUseCases: RecordUseCases,
    private val recordFaker: RecordFaker
): ViewModel() {

    fun allRecords(): Flow<List<Record>> = recordUseCases.getRecords()

    suspend fun createRecord(): Long {
        val record = Record(
            name = recordFaker.getName(),
            description = recordFaker.getDescription(),
            createdAt = System.currentTimeMillis()
        )
        return recordUseCases.createRecord(record)
    }
}