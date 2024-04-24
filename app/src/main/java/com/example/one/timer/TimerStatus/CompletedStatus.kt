package com.example.one.timer.TimerStatus

import com.example.one.vm.TimerViewModel

class CompletedStatus(private val viewModel: TimerViewModel) : IStatus {
    override fun startButtonDisplayString() = "Start"

    override fun clickStartButton() = viewModel.animatorController.start()

    override fun stopButtonEnabled() = false

    override fun clickStopButton() {}

    override fun showEditText() = true

    override fun progressSweepAngle() = 0f

    override fun completedString() = "Completed!"
}