package com.fake.dueminus

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    private lateinit var nameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var confirmPasswordInput: EditText
    private lateinit var registerButton: Button
    private lateinit var loginLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        nameInput = findViewById(R.id.etName)
        emailInput = findViewById(R.id.etEmail)
        passwordInput = findViewById(R.id.etPassword)
        confirmPasswordInput = findViewById(R.id.etConfirmPassword)
        registerButton = findViewById(R.id.btnRegister)
        loginLink = findViewById(R.id.tvGoToLogin)

        registerButton.setOnClickListener {
            registerUser()
        }

        loginLink.setOnClickListener {
            finish()
        }
    }

    private fun registerUser() {

        val name = nameInput.text.toString().trim()
        val email = emailInput.text.toString().trim()
        val password = passwordInput.text.toString()
        val confirmPassword = confirmPasswordInput.text.toString()

        if (name.isEmpty()) {
            nameInput.error = "Please enter your name"
            nameInput.requestFocus()
            return
        }

        if (email.isEmpty()) {
            emailInput.error = "Please enter your email address"
            emailInput.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.error = "Please enter a valid email address"
            emailInput.requestFocus()
            return
        }

        if (password.isEmpty()) {
            passwordInput.error = "Please enter a password"
            passwordInput.requestFocus()
            return
        }

        if (password.length < 6) {
            passwordInput.error = "Password must contain at least 6 characters"
            passwordInput.requestFocus()
            return
        }

        if (confirmPassword != password) {
            confirmPasswordInput.error = "Passwords do not match"
            confirmPasswordInput.requestFocus()
            return
        }

        registerButton.isEnabled = false

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->

                if (task.isSuccessful) {

                    val user = auth.currentUser

                    if (user != null) {

                        val userData = hashMapOf(
                            "name" to name,
                            "email" to email,
                            "language" to "English",
                            "notificationsEnabled" to true
                        )

                        db.collection("users")
                            .document(user.uid)
                            .set(userData)
                            .addOnSuccessListener {

                                Toast.makeText(
                                    this,
                                    "Registration successful!",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val intent = android.content.Intent(
                                    this,
                                    DashboardActivity::class.java
                                )

                                intent.flags =
                                    android.content.Intent.FLAG_ACTIVITY_NEW_TASK or
                                            android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK

                                startActivity(intent)
                                finish()
                            }
                            .addOnFailureListener { error ->

                                registerButton.isEnabled = true

                                Toast.makeText(
                                    this,
                                    "Account created, but profile failed: ${error.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                    }

                } else {

                    registerButton.isEnabled = true

                    Toast.makeText(
                        this,
                        "Registration failed: ${task.exception?.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}