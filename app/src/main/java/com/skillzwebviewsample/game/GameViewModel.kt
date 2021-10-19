package com.skillzwebviewsample.game

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.skillz.Skillz
import com.skillz.SkillzScoreCallback
import com.skillzwebviewsample.base.BaseViewModel
import com.skillzwebviewsample.databinding.ActivityGameBinding
import com.skillzwebviewsample.databinding.ActivityMainBinding
import com.skillzwebviewsample.main.MainViewModel
import kotlinx.coroutines.Dispatchers
import java.math.BigDecimal
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Created by enisademov on 19.10.21.
 */

class GameViewModel : BaseViewModel() {
    private val coroutineContext = viewModelScope.coroutineContext + Dispatchers.IO

    fun getAppInfo() =
        liveData(coroutineContext) {

            val remoteConfiguration = getRemoteConfigurations()

            emit(remoteConfiguration)
        }

    fun submitScore(score: Int, activity: Activity) {
        if (Skillz.isMatchInProgress()) {
            val matchInfo = Skillz.getMatchInfo(activity)
            matchInfo?.let {
                Skillz.submitScore(
                    activity,
                    BigDecimal(score),
                    it.id,
                    object : SkillzScoreCallback {
                        override fun failure(error: Exception) {

                        }

                        override fun success() {

                        }
                    })
            }
        }
    }

    private fun getRemoteConfigurations(): MutableMap<String, String> {
        return Skillz.getMatchRules()
    }
}