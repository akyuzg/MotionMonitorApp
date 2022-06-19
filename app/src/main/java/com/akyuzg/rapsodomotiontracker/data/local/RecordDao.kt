package com.akyuzg.rapsodomotiontracker.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.akyuzg.rapsodomotiontracker.data.local.dto.Coordinate
import com.akyuzg.rapsodomotiontracker.data.local.dto.Record
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {

    @Query("SELECT * FROM record")
    fun getRecords(): Flow<List<Record>>

    @Query("SELECT * FROM coordinate WHERE coordinate.recordId = :recordId")
    fun getPositions(recordId: Int): Flow<List<Coordinate>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createRecord(record: Record)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosition(position: Coordinate)
}