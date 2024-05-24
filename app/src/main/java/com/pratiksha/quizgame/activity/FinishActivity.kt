package com.pratiksha.quizgame.activity

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.pratiksha.quizgame.R
import com.pratiksha.quizgame.classes.Levels
import com.pratiksha.quizgame.classes.PreviousLevels
import com.pratiksha.quizgame.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFinishBinding
    private var dataOfAllLevel = mutableListOf<PreviousLevels>()
    private var i = 0
    private lateinit var firebaseAuth: FirebaseAuth

    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)
        val db = Firebase.firestore


        val bundle: Bundle? = intent.extras
        val totalCorrectAns = bundle?.getInt("correctAnswer")
        val totalQuestion = bundle?.getInt("questionSize")
        val level = bundle?.getInt("level")

        println("+++++++++_____FinPLevel__((((${level}++++++++++++++++++++++++++++++")

        println("(((((((((((((${totalCorrectAns}/${totalQuestion}")

        binding.score.text = "${totalCorrectAns}/${totalQuestion}"

        binding.star1IV.setImageResource(R.drawable.star_14441715)

        if (totalQuestion != null && level != null) {
            storeData(totalCorrectAns!!, level)
//            fetchData()
        } else {
            storeData(-1, -1)
        }


        when (totalCorrectAns.toString()) {
            "1" -> binding.star1IV.setImageResource(R.drawable.star_14441715)

            "2" -> {
                binding.star1IV.setImageResource(R.drawable.star_14441715)
                binding.star2IV.setImageResource(R.drawable.star_14441715)
                println("?????/$i true")
            }

            "3" -> {
                binding.star1IV.setImageResource(R.drawable.star_14441715)
                binding.star2IV.setImageResource(R.drawable.star_14441715)
                binding.star3IV.setImageResource(R.drawable.star_14441715)
            }

            else -> {
                binding.star1IV.setImageResource(R.drawable.star_2956792)
                binding.star2IV.setImageResource(R.drawable.star_2956792)
                binding.star3IV.setImageResource(R.drawable.star_2956792)
                println("????? false")
            }

        }

        binding.quitBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))

            finish()
        }

        binding.nextBtn.setOnClickListener {

            println("__________NextLevel________________________________________")
            val intent = Intent(this, LevelsActivity::class.java)
            intent.putExtra("totalCorrectAns", totalCorrectAns)
            intent.putExtra("level", level.toString())
            startActivity(intent)
            finish()
        }

        binding.restartBtn.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
            finish()
        }


    }

    private fun storeData(totalCorrectAns: Int, previousLevel: Int) {

        firebaseAuth = Firebase.auth
        val emailId: String? = firebaseAuth.currentUser?.email
        val userId = firebaseAuth.currentUser?.uid

        i = previousLevel - 1

//        dataOfAllLevel.add(
//            PreviousLevels(
//                totalCorrectAns,
//                previousLevel.toString()
//            )
//        )

        // Create a new user with a first and last name
        val user = hashMapOf(
            "levelNo" to previousLevel,
            "correctAns" to totalCorrectAns.toString()
        )
//
//        // Add a new document with a generated ID
//        Firebase.firestore.collection("users")
//            .add(user)
//            .addOnSuccessListener { documentReference ->
//                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//            }
//            .addOnFailureListener { e ->
//                Log.w(TAG, "Error adding document", e)
//            }


//        val userId = "7zFJzsVs9sjOiccH0yzV"
        if (userId != null) {

            println("______user id is not null_> ${userId} _________________")
            val userRef = db.collection("users").document(userId)
// Get the existing data from Firestore
            userRef.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {

                    println("_______wooow we document exixt_____________________")

                    val existingData = documentSnapshot.data ?: hashMapOf()

                    // Add dataOfAllLevel to the existing data
                    existingData["$i"] = user

                    // Update the document with the merged data
                    userRef.update(existingData)
                        .addOnSuccessListener {
                            Log.d(TAG, "Document updated successfully")
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error updating document", e)
                        }
                } else {
                    Log.d(TAG, "Document does not exist")

                    println("tha document of userId doesn't exist")

                    val newUser = user
                    db.collection("users").document(userId).set(newUser)
                        .addOnSuccessListener {
                            println("new User Document Created with the recipe ID....")


                              db.collection("users").document(userId)
                                  .get().addOnSuccessListener { documentSnapshot ->
                                      if (documentSnapshot.exists()) {

                                          println("_______wooow we document exixt_____________________")

                                          val existingData = documentSnapshot.data ?: hashMapOf()

                                          // Add dataOfAllLevel to the existing data
                                          existingData["$i"] = user

                                          // Update the document with the merged data
                                          userRef.update(existingData)
                                              .addOnSuccessListener {
                                                  Log.d(TAG, "Document updated successfully")
                                              }
                                              .addOnFailureListener { e ->
                                                  Log.w(TAG, "Error updating document", e)
                                              }
                                      }
                                  }

                        }.addOnFailureListener {
                            println("fail to create New user Document $it....")
                        }
                }

            }.addOnFailureListener { e ->
                Log.w(TAG, "Error getting document", e)
            }



                } else {
                    println("___________OOps user id is null____> ${userId}______________________")
                }


            }



            fun fetchData() {

                println("1234 Mic test")

                val userId = "7zFJzsVs9sjOiccH0yzV"
                val userRef = db.collection("users").document(userId)

                userRef.get().addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {


                        val existingData = documentSnapshot.data ?: hashMapOf()
                        println("1234 Mic test")

                        println(">>>>>>>>>>>>>>>>>>>>>${existingData["0"]}")
                        println(">>>>>>>>_______%%%>>>>>>>${documentSnapshot.data}")

                        var list = existingData["0"] as? Map<String, Any>?
//
//                println("#######${list["levelNo"]}")
//
//                var list = existingData[i] as? List<Any> // Cast the value to a List<Any> or the appropriate type

                        if (list != null) {
                            // Access elements from the list
                            println("#######${list["levelNo"]}") // Access the first element of the list
//                    println("#######${list[1]}") // Access the second element of the list
                            // You can also access elements by key if the list is a map
                            // println("#######${list["levelNo"]}")
                        } else {
                            println("List is null or not of expected type")
                        }
//                var some = list[0]


//                println(">>>>>>>>>>>>>>>>>>>>>>>>>${hashMapOf()}")

                        // Add dataOfAllLevel to the existing data

                    } else {
                        Log.d(TAG, "Document does not exist")
                    }
                }.addOnFailureListener { e ->
                    Log.w(TAG, "Error getting document", e)
                }

            }

        }
