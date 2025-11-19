package org.openedx.foundation.plugin

/**
 * Context provided to block extensions so they can decide whether to handle a block
 * and create the appropriate fragment.
 *
 * @param block The block instance from the consuming application
 * @param provider Provider for context data
 * @param downloadedModel The download model instance from the consuming application (nullable)
 * @param offlineUrl URL for offline access
 * @param hasNetworkConnection Whether network is available
 */
data class CourseUnitBlockContext(
    val block: Any, // Block type from consuming app
    val provider: CourseUnitBlockContextProvider,
    val downloadedModel: Any?, // DownloadModel type from consuming app
    val offlineUrl: String,
    val hasNetworkConnection: Boolean,
) {
    val noNetwork: Boolean get() = !hasNetworkConnection
    val courseId: String get() = provider.courseId
}



