package com.fake.dueminus

import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DeadlineListActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var container: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_deadline_list
        )

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        container =
            findViewById(R.id.deadlineContainer)

        loadDeadlines()
    }

    private fun loadDeadlines() {

        val user = auth.currentUser

        if (user == null) {

            finish()
            return
        }

        db.collection("users")
            .document(user.uid)
            .collection("deadlines")
            .get()
            .addOnSuccessListener { documents ->

                if (documents.isEmpty) {

                    val emptyText =
                        TextView(this)

                    emptyText.text =
                        "You have no deadlines yet."

                    emptyText.textSize = 18f

                    emptyText.setPadding(
                        0,
                        20,
                        0,
                        20
                    )

                    container.addView(
                        emptyText
                    )

                    return@addOnSuccessListener
                }

                for (document in documents) {

                    val title =
                        document.getString("title")
                            ?: "Untitled"

                    val category =
                        document.getString("category")
                            ?: "Other"

                    val description =
                        document.getString("description")
                            ?: ""

                    val priority =
                        document.getString("priority")
                            ?: "Medium"

                    val dueDateTime =
                        document.getLong(
                            "dueDateTime"
                        ) ?: 0L

                    val completed =
                        document.getBoolean(
                            "completed"
                        ) ?: false

                    val dateFormat =
                        SimpleDateFormat(
                            "dd MMM yyyy, HH:mm",
                            Locale.getDefault()
                        )

                    val deadlineText =
                        TextView(this)

                    deadlineText.text =
                        "$title\n" +
                                "Category: $category\n" +
                                "Description: $description\n" +
                                "Priority: $priority\n" +
                                "Due: ${
                                    dateFormat.format(
                                        Date(dueDateTime)
                                    )
                                }\n" +
                                "Status: ${
                                    if (completed)
                                        "Completed"
                                    else
                                        "Not Completed"
                                }"

                    deadlineText.textSize = 17f

                    deadlineText.setTypeface(
                        null,
                        Typeface.BOLD
                    )

                    deadlineText.setPadding(
                        0,
                        20,
                        0,
                        10
                    )

                    container.addView(
                        deadlineText
                    )

                    val completeButton =
                        Button(this)

                    completeButton.text =
                        if (completed)
                            "Mark Incomplete"
                        else
                            "Mark Completed"

                    completeButton.setOnClickListener {

                        document.reference
                            .update(
                                "completed",
                                !completed
                            )
                            .addOnSuccessListener {

                                Toast.makeText(
                                    this,
                                    "Deadline updated",
                                    Toast.LENGTH_SHORT
                                ).show()

                                recreate()
                            }
                    }

                    container.addView(
                        completeButton
                    )

                    val deleteButton =
                        Button(this)

                    deleteButton.text =
                        "Delete"

                    deleteButton.setOnClickListener {

                        document.reference
                            .delete()
                            .addOnSuccessListener {

                                Toast.makeText(
                                    this,
                                    "Deadline deleted",
                                    Toast.LENGTH_SHORT
                                ).show()

                                recreate()
                            }
                            .addOnFailureListener {

                                Toast.makeText(
                                    this,
                                    "Could not delete deadline",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    }

                    container.addView(
                        deleteButton
                    )
                }
            }
            .addOnFailureListener {

                Toast.makeText(
                    this,
                    "Could not load deadlines",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}