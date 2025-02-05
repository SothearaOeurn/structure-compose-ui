package com.kh.sbilyhour.composestructure.di

import android.content.Context
import androidx.activity.result.ActivityResultCaller
import com.google.gson.Gson
import com.kh.sbilyhour.composestructure.data.mapper.LoginMapper
import com.kh.sbilyhour.composestructure.data.api.AuthApi
import com.kh.sbilyhour.composestructure.data.repository.LoginRepositoryImpl
import com.kh.sbilyhour.composestructure.domain.repository.LoginRepository
import com.kh.sbilyhour.composestructure.domain.usecase.login.LoginUseCase
import com.kh.sbilyhour.composestructure.domain.usecase.login.LoginValidation
import com.kh.sbilyhour.composestructure.data.error.ApiErrorHandler
import com.kh.sbilyhour.composestructure.core.utils.NetworkUtils
import com.kh.sbilyhour.composestructure.core.utils.PermissionHandler
import com.kh.sbilyhour.composestructure.data.datasource.local.datastore.UserPreferencesDataSource
import com.kh.sbilyhour.composestructure.data.datasource.local.datastore.UserPreferencesDataSourceImpl
import com.kh.sbilyhour.composestructure.data.datasource.remote.login.LoginRemoteDataSource
import com.kh.sbilyhour.composestructure.data.datasource.remote.login.LoginRemoteDataSourceImpl
import com.kh.sbilyhour.composestructure.data.datasource.remote.register.RegisterRemoteDataSource
import com.kh.sbilyhour.composestructure.data.datasource.remote.register.RegisterRemoteDataSourceImpl
import com.kh.sbilyhour.composestructure.data.mapper.RegisterMapper
import com.kh.sbilyhour.composestructure.data.repository.RegisterRepositoryImpl
import com.kh.sbilyhour.composestructure.domain.repository.RegisterRepository
import com.kh.sbilyhour.composestructure.domain.usecase.register.RegisterUseCase
import com.kh.sbilyhour.composestructure.domain.usecase.register.RegisterValidation
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
    fun provideLoginApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideLoginRepository(
        mapper: LoginMapper,
        apiErrorHandler: ApiErrorHandler,
        remoteDataSource: LoginRemoteDataSource,
        userPreferences: UserPreferencesDataSource
    ): LoginRepository =
        LoginRepositoryImpl(mapper, apiErrorHandler, remoteDataSource, userPreferences)

    @Provides
    @Singleton
    fun provideLoginUseCase(
        repository: LoginRepository,
        loginValidation: LoginValidation
    ): LoginUseCase = LoginUseCase(repository, loginValidation)

    @Provides
    @Singleton
    fun provideApiErrorHandler(
        @ApplicationContext context: Context,
        gson: Gson,
        networkUtils: NetworkUtils
    ): ApiErrorHandler =
        ApiErrorHandler(context, gson, networkUtils)

    @Provides
    @Singleton
    fun provideLoginMapper(): LoginMapper {
        return LoginMapper()
    }

    @Provides
    @Singleton
    fun provideLoginValidation(@ApplicationContext context: Context): LoginValidation =
        LoginValidation(context)

    @Provides
    @Singleton
    fun provideLoginRemoteDataSource(api: AuthApi): LoginRemoteDataSource {
        return LoginRemoteDataSourceImpl(api)
    }

    @Provides
    @Singleton
    fun provideUserPreferenceDataSource(@ApplicationContext context: Context): UserPreferencesDataSource {
        return UserPreferencesDataSourceImpl(context)
    }

    @Provides
    @Singleton
    fun provideRegisterValidation(@ApplicationContext context: Context): RegisterValidation =
        RegisterValidation(context)

    @Provides
    @Singleton
    fun provideRegisterRemoteDataSource(api: AuthApi): RegisterRemoteDataSource {
        return RegisterRemoteDataSourceImpl(api)
    }

    @Provides
    @Singleton
    fun provideRegisterMapper(): RegisterMapper {
        return RegisterMapper()
    }

    @Provides
    @Singleton
    fun provideRegisterUseCase(
        repository: RegisterRepository,
        loginValidation: RegisterValidation
    ): RegisterUseCase = RegisterUseCase(repository, loginValidation)

    @Provides
    @Singleton
    fun provideRegisterRepository(
        mapper: RegisterMapper,
        apiErrorHandler: ApiErrorHandler,
        remoteDataSource: RegisterRemoteDataSource,
        userPreferences: UserPreferencesDataSource
    ): RegisterRepository =
        RegisterRepositoryImpl(mapper, apiErrorHandler, remoteDataSource, userPreferences)

    @Provides
    fun providePermissionHandler(
        @ApplicationContext context: Context,
    ): PermissionHandler {
        return PermissionHandler(context)
    }
}