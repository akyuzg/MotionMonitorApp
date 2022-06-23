package com.akyuzg.rapsodomotiontracker.domain.usecase

import android.util.Log
import com.akyuzg.rapsodomotiontracker.data.local.dto.Coordinate
import com.akyuzg.rapsodomotiontracker.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow

class GetPositions(
    private val repository: RecordRepository
) {

    operator fun invoke(recordId: Long): Flow<List<Coordinate>> {
        Log.e("GetPositions", "recordId = $recordId")
        return repository.getPositions(recordId)
    }

}