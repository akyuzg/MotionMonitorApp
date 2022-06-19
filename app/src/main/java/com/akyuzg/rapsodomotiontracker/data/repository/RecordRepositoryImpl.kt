package com.akyuzg.rapsodomotiontracker.data.repository

import com.akyuzg.rapsodomotiontracker.data.local.RecordDao
import com.akyuzg.rapsodomotiontracker.domain.model.Position
import com.akyuzg.rapsodomotiontracker.domain.model.Record
import com.akyuzg.rapsodomotiontracker.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow

class RecordRepositoryImpl(
    private val dao: RecordDao
): RecordRepository {

    override  fun getRecords(): Flow<List<Record>> {
        return dao.getRecords()
    }

    override suspend fun getPositions(recordId: Int): Flow<List<Position>> {
        return dao.getPositions(recordId)
    }

    override suspend fun createRecord(record: Record) {
        return dao.createRecord(record)
    }

    override suspend fun insertPosition(position: Position) {
        return dao.insertPosition(position)
    }

}