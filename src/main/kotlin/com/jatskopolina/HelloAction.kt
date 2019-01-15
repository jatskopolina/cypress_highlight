package com.jatskopolina

import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages

class HelloAction : AnAction("Hello") {
    override fun actionPerformed(event: AnActionEvent) {
        val project: Project? = event.getProject();
        Messages.showMessageDialog(project, "Hello world!", "Greetings", Messages.getInformationIcon())
    }
}
