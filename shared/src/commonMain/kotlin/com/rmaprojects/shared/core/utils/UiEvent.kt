package com.rmaprojects.shared.core.utils

sealed class UiEvent {
    data class SnackbarMessage(val message: String): UiEvent()
    data object Success: UiEvent()
    data object Idle: UiEvent()
}