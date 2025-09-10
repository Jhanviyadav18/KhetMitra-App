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

class EditProfileFragment : Fragment() {

    private lateinit var etFullName: EditText
    private lateinit var etPhoneNumber: EditText
    private lateinit var etAge: EditText
    private lateinit var etEmail: EditText
    private lateinit var etState: EditText
    private lateinit var etDistrict: EditText
    private lateinit var etCropsGrowing: EditText
    private lateinit var btnSaveProfile: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ✅ Set all EditText / input fields text color to black
        (requireActivity().application as KhetiMitraApp).setEditTextColors(this)

        etFullName = view.findViewById(R.id.etFullName)
        etPhoneNumber = view.findViewById(R.id.etPhoneNumber)
        etAge = view.findViewById(R.id.etAge)
        etEmail = view.findViewById(R.id.etEmail)
        etState = view.findViewById(R.id.etState)
        etDistrict = view.findViewById(R.id.etDistrict)
        etCropsGrowing = view.findViewById(R.id.etCropsGrowing)
        btnSaveProfile = view.findViewById(R.id.btnSaveProfile)

        // Pre-fill only with existing SharedPreferences values
        loadExistingData()

        btnSaveProfile.setOnClickListener {
            saveProfileData()
        }
    }

    private fun loadExistingData() {
        val sharedPref = requireActivity().getSharedPreferences("userProfile", Context.MODE_PRIVATE)
        etFullName.setText(sharedPref.getString("fullName", ""))
        etPhoneNumber.setText(sharedPref.getString("phone", ""))
        etAge.setText(sharedPref.getString("age", ""))
        etEmail.setText(sharedPref.getString("email", ""))
        etState.setText(sharedPref.getString("state", ""))
        etDistrict.setText(sharedPref.getString("district", ""))
        etCropsGrowing.setText(sharedPref.getString("crops", ""))
    }

    private fun saveProfileData() {
        val fullName = etFullName.text.toString().trim()
        val phone = etPhoneNumber.text.toString().trim()
        val age = etAge.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val state = etState.text.toString().trim()
        val district = etDistrict.text.toString().trim()
        val crops = etCropsGrowing.text.toString().trim()

        if (fullName.isEmpty() || phone.isEmpty() || age.isEmpty() || email.isEmpty() ||
            state.isEmpty() || district.isEmpty() || crops.isEmpty()
        ) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val sharedPref = requireActivity().getSharedPreferences("userProfile", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("fullName", fullName)
            putString("phone", phone)
            putString("age", age)
            putString("email", email)
            putString("state", state)
            putString("district", district)
            putString("crops", crops)
            apply()
        }

        Toast.makeText(requireContext(), "Profile Saved Successfully!", Toast.LENGTH_SHORT).show()
        parentFragmentManager.popBackStack() // Return to ProfileFragment
    }
}
