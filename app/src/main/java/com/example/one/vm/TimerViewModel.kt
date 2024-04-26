package com.example.one.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.one.timer.AnimatorController
import com.example.one.timer.TimerStatus.CompletedStatus
import com.example.one.timer.TimerStatus.IStatus
import com.example.one.timer.TimerStatus.NotStartedStatus

const val MAX_INPUT_LENGTH = 5

class TimerViewModel : ViewModel() {

    /**
     * Total time user set in seconds
     */
    var totalTime: Long by mutableStateOf(0)

    /**
     * Time left during countdown in seconds
     */
    var timeLeft: Long by mutableStateOf(0)

    var animatorController = AnimatorController

    var status: IStatus by mutableStateOf(NotStartedStatus(this))

    /**
     * Temp value when anim is active
     */
    var animValue: Float by mutableStateOf(0.0f)

    /**
     * Update value when EditText content changed
     * @param text new content in EditText
     */
    fun updateValue(text: String) {
        // Just in case the number is too big
        if (text.length > MAX_INPUT_LENGTH) return
        // Remove non-numeric elements
        var value = text.replace("\\D".toRegex(), "")
        // Zero cannot appear in the first place
        if (value.startsWith("0")) value = value.substring(1)
        // Set a default value to prevent NumberFormatException
        if (value.isBlank()) value = "0"
        totalTime = value.toLong()
        timeLeft = value.toLong()
        // After user clicks EditText, CompletedStatus turns to NotStartedStatus.
        if (status is CompletedStatus) status = NotStartedStatus(this)
    }

    fun update(){
        
    }
}
