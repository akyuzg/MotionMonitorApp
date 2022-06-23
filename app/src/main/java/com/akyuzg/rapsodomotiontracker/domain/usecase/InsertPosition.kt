package com.akyuzg.rapsodomotiontracker.domain.usecase

import android.util.Log
import com.akyuzg.rapsodomotiontracker.domain.model.Position
import com.akyuzg.rapsodomotiontracker.domain.repository.RecordRepository

class InsertPosition(
    private val repository: RecordRepository
) {
    suspend operator fun invoke(recordId: Long, position: Position){
        Log.e("InsertPosition", "recordId = $recordId")
        repository.insertPosition(recordId, position)
    }
}