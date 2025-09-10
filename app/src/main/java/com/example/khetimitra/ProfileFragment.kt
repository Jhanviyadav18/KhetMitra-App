package com.example.khetimitra

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    private lateinit var tvName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvPhone: TextView
    private lateinit var tvAge: TextView
    private lateinit var tvState: TextView
    private lateinit var tvDistrict: TextView
    private lateinit var tvCrops: TextView
    private lateinit var btnEditProfile: Button
    private lateinit var btnFeedback: Button
    private lateinit var btnLogout: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ✅ Set all EditText / input fields text color to black
        (requireActivity().application as KhetiMitraApp).setEditTextColors(this)

        tvName = view.findViewById(R.id.tvName)
        tvEmail = view.findViewById(R.id.tvEmail)
        tvPhone = view.findViewById(R.id.tvPhone)
        tvAge = view.findViewById(R.id.tvAge)
        tvState = view.findViewById(R.id.tvState)
        tvDistrict = view.findViewById(R.id.tvDistrict)
        tvCrops = view.findViewById(R.id.tvCrops)
        btnEditProfile = view.findViewById(R.id.btnEditProfile)
        btnFeedback = view.findViewById(R.id.btnFeedback)
        btnLogout = view.findViewById(R.id.btnLogout)

        loadProfileData()

        btnEditProfile.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, EditProfileFragment())
                .addToBackStack(null)
                .commit()
        }

        btnFeedback.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, FeedbackFragment())
                .addToBackStack(null)
                .commit()
        }

        val helpText = view.findViewById<TextView>(R.id.helpText)
        helpText.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, HelpFragment())
                .addToBackStack(null)
                .commit()
        }

        btnLogout.setOnClickListener {
            val sharedPref = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE)
            sharedPref.edit().putBoolean("isLoggedIn", false).apply()

            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
        }
    }

    private fun loadProfileData() {
        val loginPref = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val profilePref = requireActivity().getSharedPreferences("userProfile", Context.MODE_PRIVATE)

        // Name can be edited; show saved profile name if exists, else login name
        tvName.text = profilePref.getString("fullName", loginPref.getString("fullName", "Your Name"))

        // Email from login info, never editable
        tvEmail.text = loginPref.getString("email", "Your Email")

        // Editable fields from profile info
        tvPhone.text = profilePref.getString("phone", "")
        tvAge.text = profilePref.getString("age", "")
        tvState.text = profilePref.getString("state", "")
        tvDistrict.text = profilePref.getString("district", "")
        tvCrops.text = profilePref.getString("crops", "")
    }

    override fun onResume() {
        super.onResume()
        loadProfileData()
    }
}
