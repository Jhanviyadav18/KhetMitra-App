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

        // Minimal safe layout load first
        try {
            setContentView(R.layout.activity_login)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Layout loading failed!", Toast.LENGTH_SHORT).show()
            return
        }

        // ✅ Set all EditText text color to black programmatically
        (application as KhetiMitraApp).setEditTextColors(this)

        // Safe SharedPreferences check
        try {
            val sharedPref = getSharedPreferences("UserData", Context.MODE_PRIVATE)
            val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)
            if (isLoggedIn) {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
                return
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Safe view references
        val rootLayout = findViewById<View>(R.id.rootLayout)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvRegister = findViewById<TextView>(R.id.tvRegister)

        hideKeyboardOnTouchOutside(rootLayout)

        // Safe clickable text
        try {
            val text = "New user? Register here"
            val spannable = SpannableString(text)
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    try {
                        startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            spannable.setSpan(clickableSpan, 10, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannable.setSpan(ForegroundColorSpan(Color.BLUE), 10, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            tvRegister.text = spannable
            tvRegister.movementMethod = LinkMovementMethod.getInstance()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Safe login logic
        btnLogin.setOnClickListener {
            try {
                val email = etEmail.text.toString().trim()
                val password = etPassword.text.toString().trim()
                val sharedPref = getSharedPreferences("UserData", Context.MODE_PRIVATE)
                val savedEmail = sharedPref.getString("email", "")?.trim()
                val savedPassword = sharedPref.getString("password", "")?.trim()

                if (email.isNotEmpty() && password.isNotEmpty() && email == savedEmail && password == savedPassword) {
                    sharedPref.edit().putBoolean("isLoggedIn", true).apply()
                    Toast.makeText(this@LoginActivity, "Login Successful 🌱", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "Invalid credentials! Please register first.", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Login failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

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
