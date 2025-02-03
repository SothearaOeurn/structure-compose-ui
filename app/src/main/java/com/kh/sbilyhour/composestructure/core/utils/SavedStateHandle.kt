package com.kh.sbilyhour.composestructure.core.utils

import androidx.lifecycle.SavedStateHandle

/**
 * Retrieves the state of type [T] from the SavedStateHandle.
 *
 * @return The retrieved state of type [T]. If the state does not exist in the SavedStateHandle, a new instance is created and stored.
 */
inline fun <reified T : Any> SavedStateHandle.getState(): T {
    val clazz = T::class.java
    val key = clazz.simpleName
    val constructors = clazz.constructors
    return this[key] ?: (constructors.first().newInstance() as T).also { this[key] = it }
}

/**
 * Sets the provided state of type [T] into the SavedStateHandle.
 *
 * @param state The state to be stored in the SavedStateHandle.
 */
inline fun <reified T : Any> SavedStateHandle.saveState(state: T) {
    val clazz = T::class.java
    this[clazz.simpleName] = state
}