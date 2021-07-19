package com.sm.dongelek.utils

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

fun ViewPager2.removeOverScroll(){
    (getChildAt(0) as RecyclerView).removeOverScroll()
}

fun RecyclerView.removeOverScroll(){
    overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    isVerticalScrollBarEnabled = false
    isHorizontalScrollBarEnabled = false
}

fun Fragment.setUpPagesAdapter(vp: ViewPager2, pages: List<Fragment>, tabs: Pair<List<String>, TabLayout>? = null){
    vp.removeOverScroll()
    vp.adapter = ScreenSlidePagerAdapter(this, pages)
    tabs?.let {
        TabLayoutMediator(tabs.second, vp, true) { tab: TabLayout.Tab, position: Int ->
            tab.text = tabs.first[position]
        }.attach()
    }
}

private class ScreenSlidePagerAdapter(fa: Fragment, private val pages: List<Fragment>) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = pages.size

    override fun createFragment(position: Int): Fragment {
        return pages[position]
    }

}