package com.example.bmicalculator.di

import com.example.bmicalculator.repository.BMIRepository
import com.example.bmicalculator.viewmodel.BmiViewModel
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
    fun provideBmiRepository(): BMIRepository {
        return BMIRepository()
    }

}