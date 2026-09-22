package com.fake.dueminus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date
import java.text.SimpleDateFormat
import java.util.Locale

class DashboardActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    private lateinit var welcomeUser: TextView
    private lateinit var nextDeadline: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dashboard)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        welcomeUser = findViewById(R.id.tvWelcomeUser)
        nextDeadline = findViewById(R.id.tvNextDeadline)

        val addDeadline = findViewById<Button>(R.id.btnAddDeadline)
        val viewDeadlines = findViewById<Button>(R.id.btnViewDeadlines)
        val studyTimer = findViewById<Button>(R.id.btnStudyTimer)
        val settings = findViewById<Button>(R.id.btnSettings)
        val logout = findViewById<Button>(R.id.btnLogout)

        loadUser()
        loadNextDeadline()

        addDeadline.setOnClickListener {
            startActivity(
                Intent(this, AddDeadlineActivity::class.java)
            )
        }

        viewDeadlines.setOnClickListener {
            startActivity(
                Intent(this, DeadlineListActivity::class.java)
            )
        }

        studyTimer.setOnClickListener {
            startActivity(
                Intent(this, StudyTimerActivity::class.java)
            )
        }

        settings.setOnClickListener {
            startActivity(
                Intent(this, SettingsActivity::class.java)
            )
        }

        logout.setOnClickListener {

            auth.signOut()

            val intent = Intent(
                this,
                MainActivity::class.java
            )

            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
            finish()
        }
    }

    private fun loadUser() {

        val user = auth.currentUser

        if (user == null) {

            startActivity(
                Intent(this, LoginActivity::class.java)
            )

            finish()
            return
        }

        db.collection("users")
            .document(user.uid)
            .get()
            .addOnSuccessListener { document ->

                if (document.exists()) {

                    val name =
                        document.getString("name")

                    welcomeUser.text =
                        "Welcome, ${name ?: "Student"}!"

                } else {

                    welcomeUser.text =
                        "Welcome, Student!"
                }
            }
            .addOnFailureListener {

                welcomeUser.text =
                    "Welcome, Student!"
            }
    }

    private fun loadNextDeadline() {

        val user = auth.currentUser ?: return

        db.collection("users")
            .document(user.uid)
            .collection("deadlines")
            .get()
            .addOnSuccessListener { documents ->

                if (documents.isEmpty) {

                    nextDeadline.text =
                        "No upcoming deadlines"

                    return@addOnSuccessListener
                }

                val upcoming = documents.documents
                    .mapNotNull { document ->

                        val dueDateTime =
                            document.getLong("dueDateTime")
                                ?: return@mapNotNull null

                        if (dueDateTime > System.currentTimeMillis()) {
                            Pair(document, dueDateTime)
                        } else {
                            null
                        }
                    }
                    .minByOrNull { it.second }

                if (upcoming == null) {

                    nextDeadline.text =
                        "No upcoming deadlines"

                    return@addOnSuccessListener
                }

                val document = upcoming.first
                val dueDateTime = upcoming.second

                val title =
                    document.getString("title")
                        ?: "Untitled"

                val remaining =
                    dueDateTime - System.currentTimeMillis()

                val days =
                    remaining / (1000 * 60 * 60 * 24)

                val hours =
                    (remaining / (1000 * 60 * 60)) % 24

                val minutes =
                    (remaining / (1000 * 60)) % 60

                val dateFormat =
                    SimpleDateFormat(
                        "dd MMM yyyy, HH:mm",
                        Locale.getDefault()
                    )

                nextDeadline.text =
                    "Next Deadline\n\n" +
                            "$title\n\n" +
                            "$days days, $hours hours, $minutes minutes remaining\n\n" +
                            "Due: ${dateFormat.format(Date(dueDateTime))}"
            }
            .addOnFailureListener {

                Toast.makeText(
                    this,
                    "Could not load deadlines",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    override fun onResume() {

        super.onResume()

        if (auth.currentUser != null) {
            loadNextDeadline()
        }
    }
}