package com.akyuzg.rapsodomotiontracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.akyuzg.rapsodomotiontracker.domain.model.Position
import com.akyuzg.rapsodomotiontracker.domain.model.Record

@Database(
    entities = [Record::class, Position::class],
    version = 1

)
abstract class RapsodoDatabase: RoomDatabase() {
    abstract val recordDao: RecordDao
}