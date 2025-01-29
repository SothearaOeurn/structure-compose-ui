package com.kh.sbilyhour.composestructure.di

import android.content.Context
import com.google.gson.Gson
import com.kh.sbilyhour.composestructure.data.mapper.LoginMapper
import com.kh.sbilyhour.composestructure.data.remote.api.LoginApi
import com.kh.sbilyhour.composestructure.data.repository.LoginRepositoryImpl
import com.kh.sbilyhour.composestructure.domain.repository.LoginRepository
import com.kh.sbilyhour.composestructure.domain.usecase.login.LoginUseCase
import com.kh.sbilyhour.composestructure.domain.usecase.login.LoginValidation
import com.kh.sbilyhour.composestructure.utils.ApiErrorHandler
import com.kh.sbilyhour.composestructure.utils.NetworkUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun provideLoginApi(retrofit: Retrofit): LoginApi = retrofit.create(LoginApi::class.java)

    @Provides
    @Singleton
    fun provideLoginRepository(
        api: LoginApi,
        mapper: LoginMapper,
        apiErrorHandler: ApiErrorHandler
    ): LoginRepository =
        LoginRepositoryImpl(api, mapper, apiErrorHandler)

    @Provides
    @Singleton
    fun provideLoginUseCase(
        repository: LoginRepository,
        loginValidation: LoginValidation
    ): LoginUseCase = LoginUseCase(repository, loginValidation)

    @Provides
    @Singleton
    fun provideApiErrorHandler(gson: Gson, networkUtils: NetworkUtils): ApiErrorHandler =
        ApiErrorHandler(gson, networkUtils)

    @Provides
    @Singleton
    fun provideLoginMapper(): LoginMapper {
        return LoginMapper()
    }

    @Provides
    @Singleton
    fun provideLoginValidation(@ApplicationContext context: Context): LoginValidation =
        LoginValidation(context)
}