package com.fake.dueminus

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SettingsActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    private lateinit var languageSpinner: Spinner
    private lateinit var notificationSwitch: Switch

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_settings
        )

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        languageSpinner =
            findViewById(R.id.spinnerLanguage)

        notificationSwitch =
            findViewById(R.id.switchNotifications)

        val saveButton =
            findViewById<Button>(
                R.id.btnSaveSettings
            )

        val languages =
            arrayOf(
                "English",
                "Afrikaans",
                "isiXhosa"
            )

        val adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                languages
            )

        languageSpinner.adapter =
            adapter

        loadSettings()

        saveButton.setOnClickListener {
            saveSettings()
        }
    }

    private fun loadSettings() {

        val user =
            auth.currentUser ?: return

        db.collection("users")
            .document(user.uid)
            .get()
            .addOnSuccessListener { document ->

                if (!document.exists()) {
                    return@addOnSuccessListener
                }

                val language =
                    document.getString(
                        "language"
                    )

                val notifications =
                    document.getBoolean(
                        "notificationsEnabled"
                    )

                if (language != null) {

                    val position =
                        when (language) {
                            "English" -> 0
                            "Afrikaans" -> 1
                            "isiXhosa" -> 2
                            else -> 0
                        }

                    languageSpinner.setSelection(
                        position
                    )
                }

                if (notifications != null) {

                    notificationSwitch.isChecked =
                        notifications
                }
            }
    }

    private fun saveSettings() {

        val user =
            auth.currentUser ?: return

        val language =
            languageSpinner.selectedItem.toString()

        val notifications =
            notificationSwitch.isChecked

        val settings =
            hashMapOf(
                "language" to language,
                "notificationsEnabled" to notifications
            )

        db.collection("users")
            .document(user.uid)
            .set(
                settings,
                com.google.firebase.firestore.SetOptions.merge()
            )
            .addOnSuccessListener {

                Toast.makeText(
                    this,
                    "Settings saved!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener { error ->

                Toast.makeText(
                    this,
                    "Could not save settings: ${error.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
    }
}