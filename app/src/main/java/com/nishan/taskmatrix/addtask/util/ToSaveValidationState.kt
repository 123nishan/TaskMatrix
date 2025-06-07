package com.nishan.taskmatrix.addtask.util

data class ToSaveValidationState(
    val hasTitle: Boolean = false,
    val hasDescription: Boolean = false
) {
    val isValidSave: Boolean
        get() = hasTitle  && hasDescription
}