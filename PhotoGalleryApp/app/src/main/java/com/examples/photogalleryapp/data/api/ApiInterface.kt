package com.examples.photogalleryapp.data.api

import retrofit2.Call
import com.examples.photogalleryapp.data.model.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
     companion object{
         val BASE_URL = "https://pixabay.com/"
         val API_KYE = "30396408-465b69fbdd388f93350dbde98"
         var TIMEOUT: Long = 60 * 1.toLong()
     }

    @GET("api/")
    suspend fun getImages(
        @Query("key") key:String,
        @Query("page") page: Int,
        @Query("q") query: String,
        @Query("q") category: String,
        @Query("editors_choice") editorChoice: Boolean
    ):Response<ImageResponse>

    @GET("api/")
    fun getImageById(@Query("id") id:Long):Call<ImageResponse>

    @GET("api/")
    fun getSimilarImages (@Query("q") keyword:String , @Query("page") page: Int)
}