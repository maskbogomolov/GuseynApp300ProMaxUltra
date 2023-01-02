package com.example.guseynapp300promaxultra.presentation.ui.onboarding

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.guseynapp300promaxultra.presentation.ui.onboarding.pages.PageOneFragment
import com.example.guseynapp300promaxultra.presentation.ui.onboarding.pages.PageThreeFragment
import com.example.guseynapp300promaxultra.presentation.ui.onboarding.pages.PageTwoFragment


const val PAGE_1 = 0
const val PAGE_2 = 1
const val PAGE_3 = 2

class OnboardPageAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        PAGE_1 to { PageOneFragment() },
        PAGE_2 to { PageTwoFragment() },
        PAGE_3 to { PageThreeFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}