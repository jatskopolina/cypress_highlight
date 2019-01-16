package com.jatskopolina

import com.intellij.ide.util.PropertiesComponent
import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow

import javax.swing.*

class MyToolWindow(private val toolWindow: ToolWindow, project: Project) {
    private var saveToolWindowButton: JButton? = null
    private var inputTimeTextField: JTextField? = null
    private var inputTextTextField: JTextField? = null
    var myToolWindowContent: JPanel? = null
    private val component: PropertiesComponent = PropertiesComponent.getInstance()
    private val publisher: ChangeActionCallback = project.messageBus.syncPublisher(ChangeActionCallback.CHANGE_ACTION)

    init {
        saveToolWindowButton!!.addActionListener { checkDataAndPerformActions() }
    }

    private fun checkDataAndPerformActions() {
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
        component.setValue("time", time)
        component.setValue("message", message)
        publisher.doAction()
    }
}
