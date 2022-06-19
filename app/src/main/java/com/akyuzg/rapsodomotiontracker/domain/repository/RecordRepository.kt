package com.akyuzg.rapsodomotiontracker.domain.repository

import com.akyuzg.rapsodomotiontracker.domain.model.Position
import com.akyuzg.rapsodomotiontracker.domain.model.Record
import kotlinx.coroutines.flow.Flow

interface RecordRepository {

    fun getRecords(): Flow<List<Record>>

    suspend fun getPositions(recordId: Int): Flow<List<Position>>

    suspend fun createRecord(record: Record)

    suspend fun insertPosition(position: Position)
}