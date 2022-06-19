package com.akyuzg.rapsodomotiontracker.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.akyuzg.rapsodomotiontracker.domain.model.Position
import com.akyuzg.rapsodomotiontracker.domain.model.Record
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {

    @Query("SELECT * FROM record")
    fun getRecords(): Flow<List<Record>>

    @Query("SELECT * FROM position WHERE position.recordId = :recordId")
    suspend fun getPositions(recordId: Int): Flow<List<Position>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createRecord(record: Record)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosition(position: Position)
}