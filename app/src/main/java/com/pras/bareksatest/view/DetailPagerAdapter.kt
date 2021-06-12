package com.pras.bareksatest.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailPagerAdapter(activity: AppCompatActivity, data:Bundle): FragmentStateAdapter(activity) {
    private var fragmentBundle: Bundle = data
    override fun getItemCount(): Int {
        return 7
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null

        when(position){
            0 -> fragment = DetailFragment()
            1 -> fragment = DetailFragment()
            2 -> fragment = DetailFragment()
            3 -> fragment = DetailFragment()
            4 -> fragment = DetailFragment()
            5 -> fragment = DetailFragment()
            6 -> fragment = DetailFragment()
        }
        fragment?.arguments = this.fragmentBundle
        return fragment as Fragment
    }

}