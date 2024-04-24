package com.example.one.timer

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator
import com.example.one.timer.TimerStatus.PausedStatus
import com.example.one.timer.TimerStatus.StartedStatus
import com.example.one.timer.TimerStatus.NotStartedStatus
import com.example.one.timer.TimerStatus.CompletedStatus
import com.example.one.vm.TimerViewModel
import kotlin.math.ceil

const val SPEED = 100

class AnimatorController(private val viewModel: TimerViewModel) {

    private var valueAnimator: ValueAnimator? = null

    fun start() {
        if (viewModel.totalTime == 0L) return
        if (valueAnimator == null) {
            // Animator: totalTime -> 0
            valueAnimator = ValueAnimator.ofInt(viewModel.totalTime.toInt() * SPEED, 0)
            valueAnimator?.interpolator = LinearInterpolator()
            // Update timeLeft in ViewModel
            valueAnimator?.addUpdateListener {
                viewModel.animValue = (it.animatedValue as Int) / SPEED.toFloat()
                viewModel.timeLeft = ceil(it.animatedValue as Int / SPEED.toFloat()).toLong()
            }
            valueAnimator?.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    complete()
                }
            })
        } else {
            valueAnimator?.setIntValues(viewModel.totalTime.toInt() * SPEED, 0)
        }
        // (LinearInterpolator + duration) aim to set the interval as 1 second.
        valueAnimator?.duration = viewModel.totalTime * 1000L
        valueAnimator?.start()
        viewModel.status = StartedStatus(viewModel)
    }

    fun pause() {
        valueAnimator?.pause()
        viewModel.status = PausedStatus(viewModel)
    }

    fun resume() {
        valueAnimator?.resume()
        viewModel.status = StartedStatus(viewModel)
    }

    fun stop() {
        valueAnimator?.cancel()
        viewModel.timeLeft = 0
        viewModel.status = NotStartedStatus(viewModel)
    }

    fun complete() {
        viewModel.totalTime = 0
        viewModel.status = CompletedStatus(viewModel)
    }
}
