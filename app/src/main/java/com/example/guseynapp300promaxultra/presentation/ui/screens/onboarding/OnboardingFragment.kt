package com.example.guseynapp300promaxultra.presentation.ui.screens.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.guseynapp300promaxultra.R
import com.example.guseynapp300promaxultra.databinding.FragmentOnboardingBinding


class OnboardingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOnboardingBinding.inflate(inflater).apply {
            pager.adapter = OnboardingPageAdapter(this@OnboardingFragment)
        }
        return binding.root
    }

}