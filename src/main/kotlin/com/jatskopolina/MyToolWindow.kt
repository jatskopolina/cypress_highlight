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
        // TODO add input values check
        val notification = Notification("Time notification saver", "Time saved successfully",
            String.format("You wanna save time %s with message %s", inputTimeTextField!!.getText(), inputTextTextField!!.getText()), NotificationType.INFORMATION)
        // TODO save input values

        /*
            TODO use delayed task
            docs: https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ScheduledExecutorService.html#scheduleAtFixedRate(java.lang.Runnable,%20long,%20long,%20java.util.concurrent.TimeUnit)
         */
        toolWindow.hide(null)
        notification.notify(null)
    }
}
