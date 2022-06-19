package com.akyuzg.rapsodomotiontracker.domain.usecase

import com.akyuzg.rapsodomotiontracker.data.local.dto.Coordinate
import com.akyuzg.rapsodomotiontracker.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow

class GetPositions(
    private val repository: RecordRepository
) {

    operator fun invoke(recordId:Int): Flow<List<Coordinate>> {
        return repository.getPositions(recordId)
    }

}