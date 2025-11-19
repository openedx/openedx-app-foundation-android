package org.openedx.foundation.plugin

import androidx.fragment.app.Fragment

/**
 * Represents an extension that can render a course unit block.
 * @param id unique identifier to allow overriding registrations
 * @param predicate determines whether the extension can handle the provided context
 * @param fragmentFactory creates the fragment for the block
 */
data class CourseUnitBlockExtension(
    val id: String,
    val predicate: (CourseUnitBlockContext) -> Boolean,
    val fragmentFactory: (CourseUnitBlockContext) -> Fragment,
)
