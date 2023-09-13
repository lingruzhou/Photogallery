package com.examples.photogalleryapp.Util

import android.app.Activity
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.util.Random

object Common {
    private val vibrantLightColorList = mutableListOf(
        ColorDrawable(android.graphics.Color.parseColor("#ffeead")),
        ColorDrawable(android.graphics.Color.parseColor("#93cfb3")),
        ColorDrawable(android.graphics.Color.parseColor("#fd7a7a")),
        ColorDrawable(android.graphics.Color.parseColor("#faca5f")),
        ColorDrawable(android.graphics.Color.parseColor("#1ba798")),
        ColorDrawable(android.graphics.Color.parseColor("#6aa9ae")),
        ColorDrawable(android.graphics.Color.parseColor("#ffbf27")),
        ColorDrawable(android.graphics.Color.parseColor("#d93947"))
    )
    fun hideSoftKeyboard(activity: Activity, view: View) {
        val imm: InputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.applicationWindowToken, 0)
    }

  fun getRandomDrawableColor():ColorDrawable{
      return vibrantLightColorList[Random().nextInt(vibrantLightColorList.size)]
  }
}