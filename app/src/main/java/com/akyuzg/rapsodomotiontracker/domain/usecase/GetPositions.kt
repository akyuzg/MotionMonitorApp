package com.akyuzg.rapsodomotiontracker.domain.usecase

import com.akyuzg.rapsodomotiontracker.domain.model.Position
import com.akyuzg.rapsodomotiontracker.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow

class GetPositions(
    private val repository: RecordRepository
) {

    suspend operator fun invoke(recordId:Int): Flow<List<Position>> {
        return repository.getPositions(recordId)
    }

}