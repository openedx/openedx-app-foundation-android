package org.openedx.foundation.plugin

/**
 * Interface to provide context data for course unit block extensions.
 * This abstraction allows the registry to be in foundation while the implementation
 * stays in the course module.
 * 
 * The consuming application (e.g., white-label) must provide implementations
 * for Block and DownloadModel types.
 */
interface CourseUnitBlockContextProvider {
    val courseId: String
    val hasNetworkConnection: Boolean
    fun getDownloadModelById(blockId: String): Any? // DownloadModel type from consuming app
}



