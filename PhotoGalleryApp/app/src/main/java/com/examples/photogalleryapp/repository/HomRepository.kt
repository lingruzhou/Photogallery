package com.examples.photogalleryapp.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.examples.photogalleryapp.data.api.ApiInterface
import com.examples.photogalleryapp.data.paging.ImagesPagingSource

import javax.inject.Inject
import javax.inject.Singleton

class HomRepository constructor( private val apiInterface: ApiInterface) {
    fun getSearchResult(query: String, category:String, editorChoice: Boolean)=
        Pager(config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false
        ),
            pagingSourceFactory = { ImagesPagingSource(apiInterface,query,category,editorChoice) }
        ).liveData
}