package com.akyuzg.rapsodomotiontracker.data.repository

import com.akyuzg.rapsodomotiontracker.data.local.RecordDao
import com.akyuzg.rapsodomotiontracker.data.mapper.PositionMapper
import com.akyuzg.rapsodomotiontracker.domain.model.Position
import com.akyuzg.rapsodomotiontracker.data.local.dto.Coordinate
import com.akyuzg.rapsodomotiontracker.data.local.dto.Record
import com.akyuzg.rapsodomotiontracker.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow

class RecordRepositoryImpl(
    private val dao: RecordDao,
    private val mapper: PositionMapper
): RecordRepository {

    override  fun getRecords(): Flow<List<Record>> {
        return dao.getRecords()
    }

    override fun getPositions(recordId: Int): Flow<List<Coordinate>> {
        return dao.getPositions(recordId)
    }

    override suspend fun createRecord(record: Record) {
        return dao.createRecord(record)
    }

    override suspend fun insertPosition(point: Position) {
        return dao.insertPosition(mapper.fromModel(point))
    }

}