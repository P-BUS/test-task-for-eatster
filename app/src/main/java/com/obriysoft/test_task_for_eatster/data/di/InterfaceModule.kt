package com.obriysoft.test_task_for_eatster.data.di

import com.example.testtaskfore.data.network.SlidesRemoteDataSource
import com.example.testtaskfore.data.network.SlidesRemoteDataSourceImpl
import com.example.testtaskfore.data.repository.SlidesRepository
import com.example.testtaskfore.data.repository.SlidesRepositoryImpl
import com.obriysoft.test_task_for_eatster.data.local.database.SlidesLocalDataSource
import com.obriysoft.test_task_for_eatster.data.local.database.SlidesLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class InterfacesModule {
    @Binds
    @Singleton
    abstract fun bindSlidesLocalDataSource(
        slidesLocalDataSourceImpl: SlidesLocalDataSourceImpl,
    ): SlidesLocalDataSource

    @Binds
    @Singleton
    abstract fun bindSlidesRemoteDataSource(
        slidesRemoteDataSourceImpl: SlidesRemoteDataSourceImpl,
    ): SlidesRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindSlidesRepository(
        slidesRepositoryImpl: SlidesRepositoryImpl,
    ): SlidesRepository

}