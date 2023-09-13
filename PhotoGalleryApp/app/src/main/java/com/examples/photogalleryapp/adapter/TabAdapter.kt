package com.examples.photogalleryapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.examples.photogalleryapp.ui.Home.ImageListingFragment

class TabAdapter(fragmentActivity: FragmentActivity, category: List<String>) :
    FragmentStateAdapter(fragmentActivity) {
    var size1: Int = 0
    var categores = category
    override fun getItemCount(): Int {
        return size1
    }

    override fun createFragment(position: Int): Fragment {
        return ImageListingFragment.newInstance(categores[position].lowercase())
    }

    fun setSize(size: Int) {
        this.size1 = size
    }
}