package com.example.khetimitra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.khetimitra.R

class HelpFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ✅ Set all EditText text color to black globally if any added in future
        (requireActivity().application as KhetiMitraApp).setEditTextColors(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the correct Help layout
        return inflater.inflate(R.layout.fragment_help, container, false)
    }
}
