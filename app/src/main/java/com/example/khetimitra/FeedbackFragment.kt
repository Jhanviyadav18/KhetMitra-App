package com.example.khetimitra

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class FeedbackFragment : Fragment() {

    private lateinit var etFeedback: EditText
    private lateinit var btnSubmitFeedback: Button
    private lateinit var btnBack: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_feedback, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etFeedback = view.findViewById(R.id.etFeedback)
        btnSubmitFeedback = view.findViewById(R.id.btnSubmitFeedback)
        btnBack = view.findViewById(R.id.btnBack)

        // Back button click: return to ProfileFragment
        btnBack.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ProfileFragment())
                .commit()
        }

        // Submit feedback click
        btnSubmitFeedback.setOnClickListener {
            val feedback = etFeedback.text.toString().trim()

            if (feedback.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter your feedback", Toast.LENGTH_SHORT).show()
            } else {
                // Save feedback to SharedPreferences (or send to API)
                val sharedPref = requireActivity().getSharedPreferences("userFeedback", Context.MODE_PRIVATE)
                sharedPref.edit().putString("latestFeedback", feedback).apply()

                Toast.makeText(requireContext(), "Thank you for your feedback!", Toast.LENGTH_SHORT).show()
                etFeedback.text.clear()
            }
        }
    }
}
