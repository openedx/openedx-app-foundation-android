package org.openedx.foundation.interfaces

interface Analytics {
    fun logScreenEvent(screenName: String, params: Map<String, Any?>)
    fun logEvent(eventName: String, params: Map<String, Any?>)
    fun logUserId(userId: Long)
}
