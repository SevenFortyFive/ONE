package com.example.one.timer.TimerStatus

import com.example.one.vm.TimerViewModel

class PausedStatus(private val viewModel: TimerViewModel) : IStatus {
    override fun startButtonDisplayString() = "Resume"

    override fun clickStartButton() = viewModel.animatorController.resume()

    override fun stopButtonEnabled() = true

    override fun clickStopButton() = viewModel.animatorController.stop()

    override fun showEditText() = false

    override fun progressSweepAngle() = viewModel.animValue / viewModel.totalTime * 360

    override fun completedString() = ""
}