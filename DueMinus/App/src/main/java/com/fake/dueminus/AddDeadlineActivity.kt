package com.fake.dueminus

import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar

class AddDeadlineActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    private lateinit var titleInput: EditText
    private lateinit var categoryInput: EditText
    private lateinit var descriptionInput: EditText
    private lateinit var priorityInput: EditText
    private lateinit var datePicker: DatePicker
    private lateinit var timePicker: TimePicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_deadline)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        titleInput =
            findViewById(R.id.etDeadlineTitle)

        categoryInput =
            findViewById(R.id.etDeadlineCategory)

        descriptionInput =
            findViewById(R.id.etDeadlineDescription)

        priorityInput =
            findViewById(R.id.etDeadlinePriority)

        datePicker =
            findViewById(R.id.datePickerDeadline)

        timePicker =
            findViewById(R.id.timePickerDeadline)

        val saveButton =
            findViewById<Button>(R.id.btnSaveDeadline)

        saveButton.setOnClickListener {
            saveDeadline()
        }
    }

    private fun saveDeadline() {

        val user = auth.currentUser

        if (user == null) {

            Toast.makeText(
                this,
                "Please login first",
                Toast.LENGTH_SHORT
            ).show()

            finish()
            return
        }

        val title =
            titleInput.text.toString().trim()

        val category =
            categoryInput.text.toString().trim()

        val description =
            descriptionInput.text.toString().trim()

        val priority =
            priorityInput.text.toString().trim()

        if (title.isEmpty()) {

            titleInput.error =
                "Enter a deadline title"

            titleInput.requestFocus()
            return
        }

        if (category.isEmpty()) {

            categoryInput.error =
                "Enter a category"

            categoryInput.requestFocus()
            return
        }

        if (priority.isEmpty()) {

            priorityInput.error =
                "Enter Low, Medium or High"

            priorityInput.requestFocus()
            return
        }

        val validPriorities =
            listOf("low", "medium", "high")

        if (
            priority.lowercase()
            !in validPriorities
        ) {

            priorityInput.error =
                "Priority must be Low, Medium or High"

            priorityInput.requestFocus()
            return
        }

        val calendar =
            Calendar.getInstance()

        calendar.set(
            datePicker.year,
            datePicker.month,
            datePicker.dayOfMonth,
            timePicker.hour,
            timePicker.minute,
            0
        )

        val dueDateTime =
            calendar.timeInMillis

        if (dueDateTime <= System.currentTimeMillis()) {

            Toast.makeText(
                this,
                "Please select a future date and time",
                Toast.LENGTH_LONG
            ).show()

            return
        }

        val deadline =
            hashMapOf(
                "title" to title,
                "category" to category,
                "description" to description,
                "priority" to priority.replaceFirstChar {
                    it.uppercase()
                },
                "dueDateTime" to dueDateTime,
                "completed" to false,
                "createdAt" to System.currentTimeMillis()
            )

        db.collection("users")
            .document(user.uid)
            .collection("deadlines")
            .add(deadline)
            .addOnSuccessListener {

                Toast.makeText(
                    this,
                    "Deadline saved!",
                    Toast.LENGTH_SHORT
                ).show()

                finish()
            }
            .addOnFailureListener { error ->

                Toast.makeText(
                    this,
                    "Failed to save deadline: ${error.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
    }
}