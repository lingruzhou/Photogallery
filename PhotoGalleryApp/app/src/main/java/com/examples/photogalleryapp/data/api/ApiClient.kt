package com.examples.photogalleryapp.data.api

import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    val BASE_URL = "https://pixabay.com/"
    val API_KYE = "30396408-465b69fbdd388f93350dbde98"

    var TIMEOUT: Long = 60 * 1.toLong()
    private val headerInterceptor = Interceptor { chain ->
        val original = chain.request()
        val originalHttpUrl = original.url
        val url =originalHttpUrl.newBuilder()
            .addQueryParameter("key", API_KYE)
            .build()
        val requestBuilder=original.newBuilder().url(url)
        val request=requestBuilder.build()
        val response =chain.proceed(request)
        response
    }
    val getClient: ApiInterface
        get() {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
            val httpClint=OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)

            httpClint.addInterceptor(logging).addInterceptor(headerInterceptor)
            val gson =GsonBuilder().setLenient().create()

            val retrofit=Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(ApiInterface::class.java)

        }



}