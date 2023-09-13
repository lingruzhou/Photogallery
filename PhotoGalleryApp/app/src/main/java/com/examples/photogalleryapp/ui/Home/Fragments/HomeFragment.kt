package com.examples.photogalleryapp.ui.Home.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.examples.photogalleryapp.R
import com.examples.photogalleryapp.adapter.TabAdapter
import com.examples.photogalleryapp.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private val TABS_TITLE_LIST = listOf(
        "Editor's Choice",
        "Backgrounds",
        "Fashion",
        "Nature",
        "Education",
        "Feelings",
        "Health",
        "People",
        "Religion",
        "Places",
        "Animals",
        "Industry",
        "Computer",
        "Food",
        "Sports",
        "Transportation",
        "Travel",
        "Buildings",
        "Business",
        "Music"
    )


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = createTabAdapter()

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, poss -> tab.text = TABS_TITLE_LIST[poss]
        }.attach()
//        activity?.findViewById<View>(R.id.headerLayout)?.visibility = View.VISIBLE
    }

    private fun createTabAdapter(): TabAdapter {
        val tabAdapter: TabAdapter = TabAdapter(requireActivity(), TABS_TITLE_LIST)
        tabAdapter.setSize(TABS_TITLE_LIST.size)
        return tabAdapter
    }


//    private fun setupViews() {
//       // requireActivity().findViewById<View>(R.id.profilePicture).visibility = View.VISIBLE
//        val search_bar = requireActivity().findViewById<EditText>(R.id.search_bar)
//        requireActivity().findViewById<View>(R.id.headerLayout).visibility = View.VISIBLE
//        search_bar.clearFocus()
//        search_bar.setText("")
//        requireActivity().findViewById<View>(R.id.backBtn).visibility = View.GONE
//    }
}