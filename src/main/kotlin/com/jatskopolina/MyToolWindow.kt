package com.jatskopolina

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.openapi.wm.ToolWindow

import javax.swing.*

class MyToolWindow(private val toolWindow: ToolWindow) {
    private var saveToolWindowButton: JButton? = null
    private var inputTimeTextField: JTextField? = null
    private var inputTextTextField: JTextField? = null
    var myToolWindowContent: JPanel? = null

    init {
        saveToolWindowButton!!.addActionListener { saveTime() }
    }


    private fun saveTime() {
        val notification: Notification?
        val time: String = inputTimeTextField!!.text
        if ("^[0-9]{2}:[0-9]{2}$".toRegex().matches(time) && isFormattedTimeCorrect(time)) {
            notification = Notification(
                "Time notification saver",
                "Time saved successfully",
                String.format(
                    "You wanna save the notification '%s' to show at %s",
                    inputTextTextField!!.text,
                    time
                ),
                NotificationType.INFORMATION
            )
            // TODO save input values

            /*
            TODO use delayed task
            docs: https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ScheduledExecutorService.html#scheduleAtFixedRate(java.lang.Runnable,%20long,%20long,%20java.util.concurrent.TimeUnit)
            */
            toolWindow.hide(null)
        } else {
            notification = Notification(
                "Time notification saver",
                "The time was not saved",
                "Your time input has bad format or incorrect. Please input the time as 'dd:dd'",
                NotificationType.WARNING
            )
        }
        notification.notify(null)
    }

    private fun isFormattedTimeCorrect(time: String): Boolean {
        if (time[0] > '2' || (time[0] == '2' && time[1] > '3') || time[3] > '5') {
            return false
        }
        return true
    }
}
