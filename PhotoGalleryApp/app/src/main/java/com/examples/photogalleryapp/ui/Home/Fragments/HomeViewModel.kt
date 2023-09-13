package com.examples.photogalleryapp.ui.Home.Fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.examples.photogalleryapp.Util.Constans.KEY_NAME
import com.examples.photogalleryapp.Util.Constans.KEY_NAME_QUERY
import com.examples.photogalleryapp.data.api.ApiInterface
import com.examples.photogalleryapp.data.paging.ImagesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val apiInterface: ApiInterface) : ViewModel() {

    private val defaulter: HashMap<String, String> = HashMap()
    private val mutableQuery = MutableLiveData(defaulter)
    val images = mutableQuery.switchMap { data ->
        Pager(
            config = PagingConfig(pageSize = 20, maxSize = 100, enablePlaceholders = false),
            pagingSourceFactory = {
                ImagesPagingSource(
                    apiInterface,
                    data[KEY_NAME_QUERY] ?: "",
                    data[KEY_NAME] ?: "",
                    true
                )
            }
        ).liveData.cachedIn(viewModelScope)

    }

    fun search(query: String = "", category: String = "") {
        val hash: HashMap<String, String> = HashMap()
        hash[KEY_NAME_QUERY] = query
        hash[KEY_NAME] = category
        mutableQuery.postValue(hash)
        Log.e("TAG", "newInstance: ${mutableQuery.value}")
    }
}