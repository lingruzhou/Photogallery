package com.examples.photogalleryapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImageResponse(val total: Int?, val hits: List<Image>)