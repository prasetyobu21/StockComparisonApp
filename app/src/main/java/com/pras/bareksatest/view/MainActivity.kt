package com.pras.bareksatest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import com.github.aachartmodel.aainfographics.aachartcreator.*
import com.google.android.material.tabs.TabLayoutMediator
import com.pras.bareksatest.R
import com.pras.bareksatest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //    lateinit var lineList: ArrayList<Entry>
//    lateinit var lineDataSet: LineDataSet
//    lateinit var lineData: LineData
    lateinit var binding: ActivityMainBinding
//    private var aaChartModel: AAChartModel()
//    private var aaChartView: AAChartView? = null

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.mainTab_text_1,
            R.string.mainTab_text_2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = Bundle()
        val mainPagerAdapter = MainPagerAdapter(this, bundle)
        binding.mainPager.adapter = mainPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.mainPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

    }
}