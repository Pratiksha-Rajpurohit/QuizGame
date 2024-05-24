package com.pratiksha.quizgame.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.pratiksha.quizgame.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "SignIn"
        FirebaseApp.initializeApp(this)
        firebaseAuth = Firebase.auth

//  Login

        binding.loginBtn.setOnClickListener {

            val email = binding.emailET.text.toString()
            val pass = binding.passET.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            startActivity(Intent(this, MainActivity::class.java))

                            Toast.makeText(this, "user logged in successfully", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(this, "${it.exception}", Toast.LENGTH_SHORT).show()
                        }
                    }

            } else {

                if (email.isEmpty() && pass.isEmpty()) {

                    Toast.makeText(
                        this,
                        "Empty field are not allow \n Enter Email & Password",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {

                    if (email.isEmpty()) {
                        Toast.makeText(
                            this,
                            "Empty field are not allow \n Please Enter Email",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else
                        Toast.makeText(
                            this,
                            "Empty field are not allow \n Please Enter Password",
                            Toast.LENGTH_SHORT
                        ).show()
                }
            }
        }


//  goto SignUp activity
        binding.gotoSignUpTV.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

    }

    override  fun onStart(){
        super.onStart()

        val currentUser = firebaseAuth.currentUser

        if(currentUser != null){

            Toast.makeText(this, "SignIn as a ${currentUser.email}",Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, MainActivity::class.java))


        }else{
            Toast.makeText(this, "No User Signed In" , Toast.LENGTH_SHORT).show()
        }

    }


}