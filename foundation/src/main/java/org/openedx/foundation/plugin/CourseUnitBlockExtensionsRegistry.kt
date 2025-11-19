package org.openedx.foundation.plugin

/**
 * Registry for course unit block extensions.
 * Allows modules to register custom block handlers without modifying the container adapter.
 */
object CourseUnitBlockExtensionsRegistry {

    private val extensions = mutableListOf<CourseUnitBlockExtension>()
    private val lock = Any()

    /**
     * Register (or replace) a block extension.
     */
    fun register(extension: CourseUnitBlockExtension) {
        synchronized(lock) {
            extensions.removeAll { it.id == extension.id }
            extensions.add(extension)
        }
    }

    /**
     * Remove a previously registered extension by id.
     */
    fun unregister(id: String) {
        synchronized(lock) {
            extensions.removeAll { it.id == id }
        }
    }

    /**
     * Find the first extension that can handle the provided context.
     */
    fun resolve(context: CourseUnitBlockContext): CourseUnitBlockExtension? {
        synchronized(lock) {
            return extensions.firstOrNull { it.predicate(context) }
        }
    }

    /**
     * Clear all registered extensions (mainly for testing).
     */
    fun clearExtensions() {
        synchronized(lock) {
            extensions.clear()
        }
    }
}
