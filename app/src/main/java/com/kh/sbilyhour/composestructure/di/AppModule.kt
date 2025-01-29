package com.kh.sbilyhour.composestructure.di

import android.content.Context
import dagger.Module
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kh.sbilyhour.composestructure.BuildConfig
import com.kh.sbilyhour.composestructure.utils.NetworkUtils
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Provides Gson
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideNetworkUtils(@ApplicationContext context: Context): NetworkUtils {
        return NetworkUtils(context)
    }

    // Provides OkHttp client with conditional logging
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        // Add headers using an Interceptor
        val headerInterceptor = { chain: okhttp3.Interceptor.Chain ->
            val originalRequest = chain.request()
            val modifiedRequest = originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer XXX XXX XXX") // Example: Authorization header
                .addHeader("Accept", "application/json") // Example: Accept header
                .build()
            chain.proceed(modifiedRequest)
        }

        builder.addInterceptor(headerInterceptor)
        // Add logging interceptor only in DEBUG mode (dev variant)
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

    // Provides Retrofit instance
    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)  // Use the OkHttp client with conditional logging
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}
