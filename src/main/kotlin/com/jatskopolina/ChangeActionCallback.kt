package com.jatskopolina

import com.intellij.util.messages.Topic

interface ChangeActionCallback {
    fun doAction()

    companion object {
        val CHANGE_ACTION = Topic.create("Timer values set", ChangeActionCallback::class.java)
    }
}
