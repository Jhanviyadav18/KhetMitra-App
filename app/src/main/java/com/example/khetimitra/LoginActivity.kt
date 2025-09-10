package com.example.khetimitra

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        // ✅ Set all EditText text color to black programmatically
        setAllEditTextColors(findViewById(R.id.rootLayout))

        val sharedPref = getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)
        if (isLoggedIn) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        val rootLayout = findViewById<View>(R.id.rootLayout)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvRegister = findViewById<TextView>(R.id.tvRegister)

        hideKeyboardOnTouchOutside(rootLayout)

        // --- Clickable register text ---
        val text = "New user? Register here"
        val spannable = SpannableString(text)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
        }
        spannable.setSpan(clickableSpan, 10, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(ForegroundColorSpan(Color.BLUE), 10, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tvRegister.text = spannable
        tvRegister.movementMethod = LinkMovementMethod.getInstance()

        // --- Login logic ---
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val savedEmail = sharedPref.getString("email", "")?.trim()
            val savedPassword = sharedPref.getString("password", "")?.trim()

            if (email.isNotEmpty() && password.isNotEmpty() && email == savedEmail && password == savedPassword) {
                sharedPref.edit().putBoolean("isLoggedIn", true).apply()
                Toast.makeText(this, "Login Successful 🌱", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                })
                finish()
            } else {
                Toast.makeText(this, "Invalid credentials! Please register first.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // --- Recursively set EditText colors ---
    private fun setAllEditTextColors(view: View) {
        if (view is EditText) {
            view.setTextColor(Color.BLACK)
            view.setHintTextColor(Color.GRAY)
        } else if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                setAllEditTextColors(view.getChildAt(i))
            }
        }
    }

    // --- Hide keyboard logic ---
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
