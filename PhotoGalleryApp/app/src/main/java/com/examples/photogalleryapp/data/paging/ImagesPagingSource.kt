package com.examples.photogalleryapp.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.examples.photogalleryapp.data.api.ApiClient
import com.examples.photogalleryapp.data.api.ApiInterface
import com.examples.photogalleryapp.data.model.Image
import com.examples.photogalleryapp.data.model.ImageResponse
import okio.IOException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

private const val PAGE_INDEX = 1

class ImagesPagingSource(
    private val apiInterface: ApiInterface,
    private val query: String,
    private val category: String,
    private val editorChoice: Boolean
) : PagingSource<Int, Image>() {
    private val TAG = "ImagesPagingSource"
    override fun getRefreshKey(state: PagingState<Int, Image>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image> {
        val position = params.key ?: PAGE_INDEX

      return  try {
            val response = apiInterface.getImages(ApiClient.API_KYE,position, "", category, false)
            LoadResult.Page(data = response.body()?.hits!!, prevKey = if (position == PAGE_INDEX) null else position - 1,
                nextKey = if (response.body()?.hits?.isEmpty()!!)null else position+1)
        }catch (exceeption:IOException){
            LoadResult.Error(exceeption)
        }catch (exceeption:HttpException){
            LoadResult.Error(exceeption)
        }
    }
    fun imageList(call:Call<ImageResponse>):List<Image>{
         var list = mutableListOf<Image>()
        call.enqueue(object :Callback<ImageResponse> {
            override fun onResponse(call: Call<ImageResponse>, response: Response<ImageResponse>) {
                Log.e(TAG, "load: "+response.code() )
                if (response.code() == 200) {
                    list = (response.body()?.hits as MutableList<Image>?)!!
                }
            }

            override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: " + t.message)
            }

        })
        return list
    }

}