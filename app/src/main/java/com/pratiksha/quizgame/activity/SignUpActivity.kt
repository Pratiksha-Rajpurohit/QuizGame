package com.pratiksha.quizgame.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.pratiksha.quizgame.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "SignUp"

        FirebaseApp.initializeApp(this)
//        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth = Firebase.auth

//        signUp

        binding.signUpBtn.setOnClickListener {

            val email = binding.emailET.text.toString()
            val pass = binding.passET.text.toString()
            val confirmPass = binding.confirmPassET.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){

                if(pass == confirmPass){

                    firebaseAuth.createUserWithEmailAndPassword(email,pass)
                        .addOnCompleteListener {
                            if(it.isSuccessful){

                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)

                                Toast.makeText(this,"User registered successfully",Toast.LENGTH_SHORT).show()

                            }else{
                                Toast.makeText(this,"${it.exception}",Toast.LENGTH_SHORT).show()
                                println("----- Shit Man It Show Error :( ------------${it.exception}--------------------------------")
                            }
                        }

                }else{
                    Toast.makeText(this,"Password does not match", Toast.LENGTH_SHORT).show()
                }

            }else{
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

//        goto login page

        binding.gotoLoginTV.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }


    }
}