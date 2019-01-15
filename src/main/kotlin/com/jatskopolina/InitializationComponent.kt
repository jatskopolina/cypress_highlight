package com.jatskopolina

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.openapi.components.ProjectComponent
import com.intellij.openapi.project.Project

class InitializationComponent (project: Project) : ProjectComponent {
    private val myProject: Project = project

    override fun projectOpened() {
        val notification = Notification("ProjectOpenNotification", "Project Opened Detected",
            String.format("You just opened %s", myProject.name), NotificationType.INFORMATION)
        notification.notify(myProject)
    }
}
