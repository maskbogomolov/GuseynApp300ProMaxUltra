package com.example.guseynapp300promaxultra.presentation.ui.onboarding.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.guseynapp300promaxultra.databinding.FragmentPageThreeBinding
import com.example.guseynapp300promaxultra.presentation.ui.onboarding.OnboardFragmentDirections
import dev.chrisbanes.insetter.applyInsetter

class PageThreeFragment : Fragment() {

    private lateinit var binding: FragmentPageThreeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPageThreeBinding.inflate(inflater, container, false)

        binding.buttonPlay.setOnClickListener {
            findNavController().navigate(OnboardFragmentDirections.toSlotFragment())
        }

        return binding.root
    }
}