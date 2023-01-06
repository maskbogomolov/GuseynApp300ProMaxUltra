package com.example.guseynapp300promaxultra.presentation.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.guseynapp300promaxultra.databinding.FragmentOnboardBinding
import dev.chrisbanes.insetter.applyInsetter

class OnboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOnboardBinding.inflate(inflater).apply {
            pager.adapter = OnboardPageAdapter(this@OnboardFragment)
        }

        binding.root.applyInsetter {
            type(statusBars = true, navigationBars = true) { padding() }
        }

        return binding.root
    }

}