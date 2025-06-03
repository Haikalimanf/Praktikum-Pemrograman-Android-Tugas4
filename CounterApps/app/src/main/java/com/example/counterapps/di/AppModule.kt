package com.example.counterapps.di

import com.example.counterapps.repository.CounterRepository
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
    fun provideCounterRepository(): CounterRepository {
        return CounterRepository()
    }


}