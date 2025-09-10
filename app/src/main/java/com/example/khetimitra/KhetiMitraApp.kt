package com.example.khetimitra

import android.app.Application
import android.graphics.Color
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class KhetiMitraApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Optionally do global init here
    }

    fun setEditTextColors(fragment: Fragment) {
        val rootView = fragment.view ?: return
        setColorsRecursively(rootView)
    }

    private fun setColorsRecursively(view: android.view.View) {
        if (view is EditText) {
            view.setTextColor(Color.BLACK)
            view.setHintTextColor(Color.GRAY)
        } else if (view is android.view.ViewGroup) {
            for (i in 0 until view.childCount) {
                setColorsRecursively(view.getChildAt(i))
            }
        }
    }
}
