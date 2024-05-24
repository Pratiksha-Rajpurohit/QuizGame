package com.pratiksha.quizgame.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.HtmlCompat
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.pratiksha.quizgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var firebaseAuth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "QuizGame"

//        firebase initialization
        FirebaseApp.initializeApp(this)
        firebaseAuth = Firebase.auth

//        startQuiz

        binding.startBtn.setOnClickListener {

            startActivity(Intent(this, LevelsActivity::class.java))
            Toast.makeText(this, " the Quiz has been Started", Toast.LENGTH_SHORT).show()
        }

        // Your API response string
        val apiResponse = "Your API response containing &#039;"

        // Decode HTML entities
        val decodedText = HtmlCompat.fromHtml(apiResponse, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()

        println("**********($decodedText)************")

//        logout

        binding.logOutBtn.setOnClickListener {
            firebaseAuth.signOut()

            startActivity(Intent(this, LoginActivity::class.java))

            Toast.makeText(this, "Logged out successful",Toast.LENGTH_SHORT).show()

            finish()
        }


    }
}