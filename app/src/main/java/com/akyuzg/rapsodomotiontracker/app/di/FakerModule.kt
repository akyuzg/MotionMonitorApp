package com.akyuzg.rapsodomotiontracker.app.di

import com.akyuzg.rapsodomotiontracker.domain.utils.RandomStringGenerator
import com.akyuzg.rapsodomotiontracker.domain.utils.RecordFaker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class FakerModule {

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