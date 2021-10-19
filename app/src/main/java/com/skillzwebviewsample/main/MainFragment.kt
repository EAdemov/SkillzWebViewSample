package com.skillzwebviewsample.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import com.skillz.Skillz
import com.skillzwebviewsample.base.BaseFragment
import com.skillzwebviewsample.databinding.FragmentGameBinding

/**
 * Created by enisademov on 19.10.21.
 */

class MainFragment : BaseFragment<MainViewModel, FragmentGameBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStart.setOnClickListener {
            Skillz.launch(activity)
        }
    }

    override fun getViewModel() = MainViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentGameBinding.inflate(inflater, container, false)
}