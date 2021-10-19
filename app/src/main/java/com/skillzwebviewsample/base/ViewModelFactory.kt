package com.skillzwebviewsample.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skillzwebviewsample.game.GameViewModel
import com.skillzwebviewsample.main.MainViewModel
import java.lang.IllegalArgumentException

/**
 * Created by enisademov on 19.10.21.
 */

@Suppress("UNCHECKED_CAST")
class ViewModelFactory() : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel() as T
            modelClass.isAssignableFrom(GameViewModel::class.java) ->
                GameViewModel() as T
            else -> throw IllegalArgumentException("ViewModel class not found")
        }
    }
}