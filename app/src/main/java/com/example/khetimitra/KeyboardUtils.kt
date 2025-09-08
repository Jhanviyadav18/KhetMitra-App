package com.example.khetimitra

import android.app.Activity
import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun Activity.setupKeyboardHider(view: View) {
    // Set touch listener for all views except EditText
    if (view !is EditText) {
        view.setOnTouchListener { _, _ ->
            hideKeyboard()
            false
        }
    }

    // If ViewGroup, recursively apply to children
    if (view is ViewGroup) {
        for (i in 0 until view.childCount) {
            setupKeyboardHider(view.getChildAt(i))
        }
    }
}

fun Activity.hideKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val currentFocusView = currentFocus ?: View(this)
    imm.hideSoftInputFromWindow(currentFocusView.windowToken, 0)
}
