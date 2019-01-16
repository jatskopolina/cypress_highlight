package com.jatskopolina

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import java.util.*

class MyTimerTask (private val message: String): TimerTask() {

    override fun run() {
        val notification = Notification(
            "Time notification saver",
            "Timer is out",
            message,
            NotificationType.ERROR
        )
        notification.notify(null)
    }
}
