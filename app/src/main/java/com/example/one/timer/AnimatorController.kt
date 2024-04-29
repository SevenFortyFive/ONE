package com.example.one.timer

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator
import com.example.one.data.SharedPreferences.BALANCE
import com.example.one.data.SharedPreferences.BREATH_TIME_KEY
import com.example.one.data.SharedPreferences.SharedPreferencesHelper
import com.example.one.timer.TimerStatus.PausedStatus
import com.example.one.timer.TimerStatus.StartedStatus
import com.example.one.timer.TimerStatus.NotStartedStatus
import com.example.one.timer.TimerStatus.CompletedStatus
import com.example.one.vm.HotMapViewModel
import com.example.one.vm.TimerViewModel
import kotlin.math.ceil

const val SPEED = 100

object AnimatorController{
    private var timerViewModel:TimerViewModel? = null
    private var hotMapViewModel:HotMapViewModel? = null

    fun init(timerViewModel: TimerViewModel,hotMapViewModel: HotMapViewModel){
        this.timerViewModel = timerViewModel
        this.hotMapViewModel = hotMapViewModel

    }

    private var valueAnimator: ValueAnimator? = null
    // 记录这次的value
    private var thistimevalue = -1
    fun start() {
        if(timerViewModel != null)
        {
            if (timerViewModel!!.totalTime == 0L) return
            if (valueAnimator == null) {
                // Animator: totalTime -> 0
                valueAnimator = ValueAnimator.ofInt(timerViewModel!!.totalTime.toInt() * SPEED, 0)
                valueAnimator?.interpolator = LinearInterpolator()
                this.thistimevalue = timerViewModel!!.totalTime.toInt()
                // Update timeLeft in ViewModel
                valueAnimator?.addUpdateListener {
                    timerViewModel!!.animValue = (it.animatedValue as Int) / SPEED.toFloat()
                    timerViewModel!!.timeLeft = ceil(it.animatedValue as Int / SPEED.toFloat()).toLong()
                }
                valueAnimator?.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                        complete()
                    }
                })
            } else {
                valueAnimator?.setIntValues(timerViewModel!!.totalTime.toInt() * SPEED, 0)
            }
            // (LinearInterpolator + duration) aim to set the interval as 1 second.
            valueAnimator?.duration = timerViewModel!!.totalTime * 1000L
            valueAnimator?.start()
            timerViewModel!!.status = StartedStatus(timerViewModel!!)
        }

    }

    fun pause() {
        if(timerViewModel != null)
        {
            valueAnimator?.pause()
            timerViewModel!!.status = PausedStatus(timerViewModel!!)
        }
    }

    fun resume() {
        if(timerViewModel != null)
        {
            valueAnimator?.resume()
            timerViewModel!!.status = StartedStatus(timerViewModel!!)
        }
    }

    fun stop() {
        if(timerViewModel != null)
        {
            valueAnimator?.cancel()
            timerViewModel!!.timeLeft = 0
            timerViewModel!!.status = NotStartedStatus(timerViewModel!!)
        }
    }

    /**
     * 计数完成时调用
     */
    fun complete() {
        if(timerViewModel != null)
        {
            timerViewModel!!.totalTime = 0
            timerViewModel!!.status = CompletedStatus(timerViewModel!!)
            // 调用热图viewmodel修改数据
            hotMapViewModel!!.update(1)
            SharedPreferencesHelper.add(BREATH_TIME_KEY, this.thistimevalue)
            SharedPreferencesHelper.add(BALANCE,this.thistimevalue)
        }
    }
}
