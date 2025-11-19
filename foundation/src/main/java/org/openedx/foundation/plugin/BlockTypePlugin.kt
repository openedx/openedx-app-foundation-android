package org.openedx.foundation.plugin

import org.koin.core.module.Module

/**
 * Plugin interface for block type extensions.
 * Implement this interface in your block type module to automatically register
 * new block types and their fragment handlers.
 *
 * The plugin's Koin module will be automatically discovered and loaded.
 */
interface BlockTypePlugin {
    /**
     * Returns the Koin module that registers the block type and its extension.
     * This module should use `createdAtStart = true` to ensure registration happens early.
     */
    fun getModule(): Module
}



