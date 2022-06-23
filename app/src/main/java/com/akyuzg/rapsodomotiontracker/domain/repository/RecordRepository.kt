package com.akyuzg.rapsodomotiontracker.domain.repository

import com.akyuzg.rapsodomotiontracker.domain.model.Position
import com.akyuzg.rapsodomotiontracker.data.local.dto.Coordinate
import com.akyuzg.rapsodomotiontracker.data.local.dto.Record
import kotlinx.coroutines.flow.Flow

interface RecordRepository {

    fun getRecords(): Flow<List<Record>>

    fun getPositions(recordId: Long): Flow<List<Coordinate>>

    suspend fun createRecord(record: Record): Long

    suspend fun insertPosition(recordId: Long, point: Position)
}