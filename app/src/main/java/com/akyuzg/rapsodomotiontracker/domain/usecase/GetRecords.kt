package com.akyuzg.rapsodomotiontracker.domain.usecase

import com.akyuzg.rapsodomotiontracker.data.local.dto.Record
import com.akyuzg.rapsodomotiontracker.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetRecords(
    private val repository: RecordRepository
) {

    operator fun invoke(): Flow<List<Record>> {
        return repository.getRecords().map { records
            -> records.sortedByDescending { it.id }
        }
    }

}