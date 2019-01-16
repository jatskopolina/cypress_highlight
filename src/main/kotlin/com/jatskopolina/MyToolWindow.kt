package com.jatskopolina

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.openapi.wm.ToolWindow

import javax.swing.*
import java.io.File

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
        val message: String = inputTextTextField!!.text
        if ("^[0-9]{2}:[0-9]{2}$".toRegex().matches(time) && isFormattedTimeCorrect(time)) {
            notification = Notification(
                "Time notification saver",
                "Time saved successfully",
                String.format(
                    "You wanna save the notification '%s' to show at %s",
                    message,
                    time
                ),
                NotificationType.INFORMATION
            )
            saveConfiguration(time, message)
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

    private fun saveConfiguration(time: String, message: String) {
        val file = File("D://timeNotificatorConfiguration.txt")
        file.createNewFile() //creates new file if there was no such file
        file.writeText(
            "HOURS: " + time.substring(0, 2) + "\r\n" +
             "MINUTES: " + time.substring(3, 5) + "\r\n" +
             "MESSAGE: " + message
        )

    }
}
