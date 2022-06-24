package com.akyuzg.rapsodomotiontracker.app.di

import android.app.Application
import android.hardware.SensorManager
import androidx.room.Room
import com.akyuzg.rapsodomotiontracker.data.local.RapsodoDatabase
import com.akyuzg.rapsodomotiontracker.data.repository.RecordRepositoryImpl
import com.akyuzg.rapsodomotiontracker.data.mapper.PositionMapper
import com.akyuzg.rapsodomotiontracker.domain.repository.RecordRepository
import com.akyuzg.rapsodomotiontracker.domain.usecase.*
import com.akyuzg.rapsodomotiontracker.domain.usecase.sensor_change.ISensorChangeManager
import com.akyuzg.rapsodomotiontracker.domain.usecase.sensor_change.SensorChangeManager
import com.akyuzg.rapsodomotiontracker.domain.utils.RandomStringGenerator
import com.akyuzg.rapsodomotiontracker.domain.utils.RecordFaker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRapsodoDatabase(app: Application): RapsodoDatabase{
        return Room.databaseBuilder(
            app,
            RapsodoDatabase::class.java,
            RapsodoDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providePositionMapper(): PositionMapper {
        return PositionMapper()
    }

    @Provides
    @Singleton
    fun provideRecordRepository(db: RapsodoDatabase, mapper: PositionMapper): RecordRepository {
        return RecordRepositoryImpl(db.recordDao, mapper)
    }

    @Provides
    @Singleton
    fun provideRecordUsecases(repository: RecordRepository): RecordUseCases{
        return RecordUseCases(
            getRecords = GetRecords(repository),
            getPositions = GetPositions(repository),
            createRecord = CreateRecord(repository),
            insertPosition = InsertPosition(repository)
        )
    }


    @Provides
    @Singleton
    fun provideSensorChangeManager(): ISensorChangeManager{
        return SensorChangeManager()
    }

    @Provides
    @Singleton
    fun provideRandomStringGenerator(): RandomStringGenerator {
        return RandomStringGenerator()
    }

    @Provides
    @Singleton
    fun provideRecordFaker(randomStringGenerator: RandomStringGenerator): RecordFaker {
        return RecordFaker(randomStringGenerator)
    }

}