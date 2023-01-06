package com.example.guseynapp300promaxultra.presentation.ui.slot

import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.guseynapp300promaxultra.R
import com.example.guseynapp300promaxultra.databinding.FragmentSlotBinding
import dev.chrisbanes.insetter.applyInsetter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class SlotFragment : Fragment() {

    private lateinit var binding: FragmentSlotBinding

    private var spinCount = 0
    private var bankCount = 1000

    private val slotsImages = listOf(
        R.drawable.slot_item1,
        R.drawable.slot_item2,
        R.drawable.slot_item3,
        R.drawable.slot_item4,
        R.drawable.slot_item5,
        R.drawable.slot_item6,
        R.drawable.slot_item7,
        R.drawable.slot_item8,
        R.drawable.slot_item9,
        R.drawable.slot_item10,
        R.drawable.slot_item11,
        R.drawable.slot_item12,
        R.drawable.slot_item13
    )

    private var animSlot1: AnimationDrawable? = null
    private var animSlot2: AnimationDrawable? = null
    private var animSlot3: AnimationDrawable? = null

    private var spinMusicPlayer: MediaPlayer? = null
    private var winMusicPlayer: MediaPlayer? = null

    private var jackpotText: Spanned? = null
    private var plus150PointsText: Spanned? = null
    private var loseText: Spanned? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSlotBinding.inflate(inflater, container, false)

        jackpotText = Html.fromHtml(getString(R.string.jackpot))
        plus150PointsText = Html.fromHtml(getString(R.string.points_150_plus))
        loseText = Html.fromHtml(getString(R.string.lose))

        with(binding) {
            textBank.text = bankCount.toString()
            buttonSpin.setOnClickListener { spin() }
            textPrise.text = Html.fromHtml(getString(R.string.press_roll))

            root.applyInsetter {
                type(statusBars = true, navigationBars = true) { padding() }
            }
        }

        spinMusicPlayer = MediaPlayer.create(requireContext(), R.raw.re__sound1)
        winMusicPlayer = MediaPlayer.create(requireContext(), R.raw.re__sound2)

        animSlot1 = AppCompatResources.getDrawable(
            requireContext(),
            R.drawable.slots_anim
        ) as AnimationDrawable

        animSlot2 = AppCompatResources.getDrawable(
            requireContext(),
            R.drawable.slots_anim
        ) as AnimationDrawable

        animSlot3 = AppCompatResources.getDrawable(
            requireContext(),
            R.drawable.slots_anim
        ) as AnimationDrawable

        return binding.root
    }

    private fun spin() {
        spinCount++

        bankCount -= 100

        winMusicPlayer?.stop()
        spinMusicPlayer?.start()

        with(binding) {
            textBank.text = bankCount.toString()
            textPrise.text = Html.fromHtml(getString(R.string.points_100_minus))
            buttonSpin.isEnabled = false

            imageSlot1.setImageDrawable(animSlot1)
            imageSlot2.setImageDrawable(animSlot2)
            imageSlot3.setImageDrawable(animSlot3)
        }

        lifecycleScope.launch {
            animSlot1?.start()

            animSlot2?.start()

            animSlot3?.start()

            delay(Random.nextLong(1000, 3000))

            if (spinCount > 5) jackpot() else stopSpin()
        }
    }

    private fun stopSpin() {
        val first = slotsImages[Random.nextInt(0, 12)]
        val second = slotsImages[Random.nextInt(0, 12)]
        val third = slotsImages[Random.nextInt(0, 12)]

        lifecycleScope.launch {
            delay(Random.nextLong(100, 1300))

            binding.imageSlot1.setImageDrawable(
                AppCompatResources.getDrawable(
                    requireContext(),
                    first
                )
            )

            delay(Random.nextLong(150, 1300))

            binding.imageSlot2.setImageDrawable(
                AppCompatResources.getDrawable(
                    requireContext(),
                    second
                )
            )

            delay(Random.nextLong(150, 1300))

            binding.imageSlot3.setImageDrawable(
                AppCompatResources.getDrawable(
                    requireContext(),
                    third
                )
            )

            spinMusicPlayer?.stop()

            if (first == second && second == third) {
                showWin(winPoints = 500, winText = jackpotText)
            } else if (first == third) {
                showWin(winPoints = 150, winText = plus150PointsText)
            } else if (second == third) {
                showWin(winPoints = 150, winText = plus150PointsText)
            } else if (first == second) {
                showWin(winPoints = 150, winText = plus150PointsText)
            } else {
                showLose(loseText = loseText)
            }

            binding.textBank.text = bankCount.toString()
            binding.buttonSpin.isEnabled = true
        }
    }

    private fun jackpot() {
        lifecycleScope.launch {
            delay(Random.nextLong(100, 1300))

            binding.imageSlot1.setImageDrawable(
                AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.slot_item2
                )
            )

            delay(Random.nextLong(100, 1300))

            binding.imageSlot2.setImageDrawable(
                AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.slot_item2
                )
            )

            delay(Random.nextLong(100, 1300))

            binding.imageSlot3.setImageDrawable(
                AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.slot_item2
                )
            )

            binding.textPrise.text = jackpotText

            delay(1000)

            spinMusicPlayer?.stop()
        }
    }

    private fun showWin(winPoints: Int, winText: Spanned?) {
        winMusicPlayer?.start()
        bankCount += winPoints
        binding.textPrise.text = winText
    }

    private fun showLose(loseText: Spanned?) {
        binding.textPrise.text = loseText
    }

    override fun onDestroyView() {
        winMusicPlayer?.stop()
        spinMusicPlayer?.stop()
        binding.unbind()
        super.onDestroyView()
    }
}