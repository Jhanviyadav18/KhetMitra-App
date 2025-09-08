package com.example.khetimitra

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // --- Initialize views ---
        val rootLayout = findViewById<View>(R.id.rootLayout)
        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val tvBackToLogin = findViewById<TextView>(R.id.tvBackToLogin)

        // --- Hide keyboard when touch outside EditText ---
        hideKeyboardOnTouchOutside(rootLayout)

        // --- Make "Login here" clickable ---
        val text = "Already registered? Login here"
        val spannable = SpannableString(text)
        val clickableSpan = object : android.text.style.ClickableSpan() {
            override fun onClick(widget: View) {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                finish()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.parseColor("#1565C0") // Blue
                ds.isUnderlineText = true
                ds.isFakeBoldText = true
            }
        }

        spannable.setSpan(
            clickableSpan,
            text.indexOf("Login here"),
            text.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        tvBackToLogin.text = spannable
        tvBackToLogin.movementMethod = LinkMovementMethod.getInstance()

        // --- Register button logic ---
        btnRegister.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sharedPref = getSharedPreferences("UserData", Context.MODE_PRIVATE)
            sharedPref.edit()
                .putString("name", name)
                .putString("email", email)
                .putString("password", password)
                .apply()

            Toast.makeText(this, "Registration Successful 🎉", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    // --- Keyboard hide logic ---
    private fun hideKeyboardOnTouchOutside(view: View) {
        if (view !is EditText) {
            view.setOnTouchListener { _, _ ->
                hideKeyboard()
                false
            }
        }

        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                hideKeyboardOnTouchOutside(view.getChildAt(i))
            }
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusView = currentFocus ?: View(this)
        imm.hideSoftInputFromWindow(currentFocusView.windowToken, 0)
    }
}
