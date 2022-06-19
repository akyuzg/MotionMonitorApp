package com.akyuzg.rapsodomotiontracker.app.di

import android.app.Application
import androidx.room.Room
import com.akyuzg.rapsodomotiontracker.data.local.RapsodoDatabase
import com.akyuzg.rapsodomotiontracker.data.repository.RecordRepositoryImpl
import com.akyuzg.rapsodomotiontracker.domain.repository.RecordRepository
import com.akyuzg.rapsodomotiontracker.domain.usecase.*
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
    fun provideRecordRepository(db: RapsodoDatabase): RecordRepository {
        return RecordRepositoryImpl(db.recordDao)
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

}