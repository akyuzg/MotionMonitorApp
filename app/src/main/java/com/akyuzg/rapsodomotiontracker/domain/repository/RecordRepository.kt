package com.akyuzg.rapsodomotiontracker.domain.repository

import com.akyuzg.rapsodomotiontracker.domain.model.Position
import com.akyuzg.rapsodomotiontracker.data.local.dto.Coordinate
import com.akyuzg.rapsodomotiontracker.data.local.dto.Record
import kotlinx.coroutines.flow.Flow

interface RecordRepository {

    fun getRecords(): Flow<List<Record>>

    fun getPositions(recordId: Int): Flow<List<Coordinate>>

    suspend fun createRecord(record: Record)

    suspend fun insertPosition(point: Position)
}