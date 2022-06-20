package com.akyuzg.rapsodomotiontracker.domain.usecase

import com.akyuzg.rapsodomotiontracker.domain.model.Position
import com.akyuzg.rapsodomotiontracker.domain.repository.RecordRepository

class InsertPosition(
    private val repository: RecordRepository
) {
    suspend operator fun invoke(recordId: Int, position: Position){
        repository.insertPosition(recordId, position)
    }
}