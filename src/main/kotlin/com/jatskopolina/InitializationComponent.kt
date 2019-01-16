package com.jatskopolina

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.components.ProjectComponent
import com.intellij.openapi.project.Project
import java.util.*
import java.time.LocalDateTime

class ChangeActionCallbackImplementation(private val component: PropertiesComponent) : ChangeActionCallback {
    private var timer: Timer? = null

    init {
        doAction()
    }

    override fun doAction() {
        if (timer != null) {
            timer!!.cancel()
        }
        val time = component.getValue("time")

        if (time != null) {
            val hours = Integer.parseInt(time.substring(0, 2))
            val minutes = Integer.parseInt(time.substring(3, 5))

            var message: String? = component.getValue("message")

            if (message == null) {
                message = ""
            }

            timer = Timer()
            val timerTask: TimerTask = MyTimerTask(message)

            timer!!.scheduleAtFixedRate(
                timerTask,
                Integer.toUnsignedLong(getDelayInMillis(hours, minutes)),
                1000 * 60 * 60 * 24
            )
        }
    }

    private fun getDelayInMillis (hours: Int, minutes: Int): Int {
        val now = LocalDateTime.now()
        val minutesToSignal: Int = hours * 60 + minutes
        val currentMinutes: Int = now.hour * 60 + now.minute
        var diff = minutesToSignal - currentMinutes
        if (minutesToSignal < currentMinutes) {
            diff += 24 * 60
        }
        return diff * 1000 * 60
    }
}

class InitializationComponent(project: Project) : ProjectComponent {
    private val myProject: Project = project

    override fun projectOpened() {
        myProject.messageBus.connect().subscribe(
            ChangeActionCallback.CHANGE_ACTION,
            ChangeActionCallbackImplementation(PropertiesComponent.getInstance())
        )
    }
}
