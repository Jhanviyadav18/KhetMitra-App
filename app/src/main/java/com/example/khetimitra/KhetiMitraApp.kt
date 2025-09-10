package com.example.khetimitra

import android.app.Application
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment

class KhetiMitraApp : Application() {

    override fun onCreate() {
        super.onCreate()
        // Optional global initialization
    }

    // --- Set colors for all EditTexts inside a fragment ---
    fun setEditTextColors(fragment: Fragment) {
        val rootView = fragment.view ?: return
        setEditTextColors(rootView)
    }

    // --- Recursive function to set EditText colors in any view hierarchy ---
    fun setEditTextColors(view: View) {
        if (view is EditText) {
            view.setTextColor(Color.BLACK)
            view.setHintTextColor(Color.GRAY)
        } else if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                setEditTextColors(view.getChildAt(i))
            }
        }
    }
}
