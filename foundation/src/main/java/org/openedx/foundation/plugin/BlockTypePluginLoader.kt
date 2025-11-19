package org.openedx.foundation.plugin

import org.koin.core.module.Module
import java.util.ServiceLoader

/**
 * Loader for block type plugins.
 * Discovers and loads plugins using ServiceLoader pattern.
 */
object BlockTypePluginLoader {
    /**
     * Load all available block type plugins.
     * Uses ServiceLoader to discover implementations of BlockTypePlugin.
     *
     * @return List of Koin modules from all discovered plugins
     */
    fun loadPlugins(): List<Module> {
        return try {
            val serviceLoader = ServiceLoader.load(BlockTypePlugin::class.java)
            serviceLoader.map { it.getModule() }
        } catch (e: Exception) {
            // ServiceLoader not available or no plugins found
            emptyList()
        }
    }
}



