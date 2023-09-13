package com.examples.photogalleryapp.module

import android.content.Context
import com.examples.photogalleryapp.BuildConfig
import com.examples.photogalleryapp.data.api.ApiClient
import com.examples.photogalleryapp.data.api.ApiInterface
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module

object AppModule {
    @Provides
    @Singleton
    fun provideApiInterface(): ApiInterface {
        
     return Retrofit.Builder().baseUrl(ApiInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build().create(ApiInterface::class.java)
    }


    private fun okHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.HEADERS)
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        return OkHttpClient.Builder()
            .connectTimeout(ApiClient.TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(ApiClient.TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(ApiClient.TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .addInterceptor(OkHttpProfilerInterceptor())
            .build()
    }
}
