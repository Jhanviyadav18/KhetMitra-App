package com.example.khetimitra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val headingTextView = view.findViewById<TextView>(R.id.tvAboutHeading)
        val descriptionTextView = view.findViewById<TextView>(R.id.tvAboutDescription)
        val feedbackButton = view.findViewById<Button>(R.id.btnFeedback)

        headingTextView.text = "About Us - KhetMitra🌾"
        descriptionTextView.text = "KhetMitra empowers farmers with digital solutions for smart farming. " +
                "Our platform provides real-time insights, expert advice, and precision farming tools " +
                "for enhanced productivity and profitability."

        feedbackButton.setOnClickListener {
            Toast.makeText(requireContext(), "Feedback clicked", Toast.LENGTH_SHORT).show()
            // You can redirect this to a FeedbackFragment or Activity later
        }
    }
}
