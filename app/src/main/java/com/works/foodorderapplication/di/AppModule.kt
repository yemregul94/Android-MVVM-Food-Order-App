package com.works.foodorderapplication.di

import com.google.firebase.auth.FirebaseAuth
import com.works.foodorderapplication.data.datasource.FoodsDataSource
import com.works.foodorderapplication.data.datasource.UserDataSource
import com.works.foodorderapplication.data.repo.FoodsRepository
import com.works.foodorderapplication.data.repo.UserRepository
import com.works.foodorderapplication.data.retrofit.ApiUtils
import com.works.foodorderapplication.data.retrofit.FoodsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideFoodsRepository(fds: FoodsDataSource) : FoodsRepository {
        return FoodsRepository(fds)
    }

    @Provides
    @Singleton
    fun provideFoodsDataSource(fdao: FoodsDao) : FoodsDataSource {
        return FoodsDataSource(fdao)
    }

    @Provides
    @Singleton
    fun provideFoodsDao() : FoodsDao {
        return ApiUtils.getFoodsDao()
    }

    @Provides
    @Singleton
    fun provideUserRepository(uds: UserDataSource) : UserRepository {
        return UserRepository(uds)
    }

    @Provides
    @Singleton
    fun provideUserDataSource() = UserDataSource()

}