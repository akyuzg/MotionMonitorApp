package com.akyuzg.rapsodomotiontracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.akyuzg.rapsodomotiontracker.data.local.dto.Coordinate
import com.akyuzg.rapsodomotiontracker.data.local.dto.Record

@Database(
    entities = [Record::class, Coordinate::class],
    version = 1

)
abstract class RapsodoDatabase: RoomDatabase() {
    abstract val recordDao: RecordDao

    companion object{
        const val DATABASE_NAME = "rapsodo"
    }
}