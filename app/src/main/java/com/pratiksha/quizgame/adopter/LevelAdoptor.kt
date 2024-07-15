package com.pratiksha.quizgame.adopter

import android.content.ContentValues
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.pratiksha.quizgame.R
import com.pratiksha.quizgame.activity.QuizActivity
import com.pratiksha.quizgame.db.Levels
import com.pratiksha.quizgame.db.PreviousLevels


class LevelAdoptor(
    private val listOfLevel : List<Levels>,
    private  var context : Context,
//    private val totalCorrectAns : Int?,
    private val dataOfAllLevels : List<PreviousLevels>,
    private val index : Int
): RecyclerView.Adapter<LevelAdoptor.LevelViewHolder>() {


    class LevelViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val levelTv : TextView = view.findViewById(R.id.levelTv)
        val itemsCardView : CardView = view.findViewById(R.id.itemsCardView)
        val star1IV : ImageView = view.findViewById(R.id.star1IV)
        val star2IV : ImageView = view.findViewById(R.id.star2IV)
        val star3IV : ImageView = view.findViewById(R.id.star3IV)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_rv_item, parent ,false)

        return LevelViewHolder(view)
    }

    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {


        holder.levelTv.text = listOfLevel[position].level.toString()

        var correctAns = fetchData(holder,position).toString()

            holder.itemsCardView.setOnClickListener {
//                if(listOfLevel[position].level.toString() == "1"  || correctAns.toInt() > 1) {
                    val level = holder.levelTv.text.toString()

                    val levelInt = level.toInt()
                    moveToAnotherActivity(levelInt)

//                }else {
////            Toast.makeText(this, "first complete level ${listOfLevel[position].level.toString()}",Toast.LENGTH_SHORT).show()
//                    println("---------first complete level ${listOfLevel[position].level.toInt() - 1}.....................................")
//                }

            }






//
//        for (i in 0..index){
////            println("((((_____PLevel__((((${previousLevel}++++++++++++++++++++++++++++++")
//            if(listOfLevel[position].level.toString() == dataOfAllLevels[i].levelNo){
//
//                when(dataOfAllLevels[i].correctAnswer){
//                    1 -> holder.star1IV.setImageResource(R.drawable.star_14441715)
//
//                    2 ->{holder.star1IV.setImageResource(R.drawable.star_14441715)
//                        holder.star2IV.setImageResource(R.drawable.star_14441715)}
//
//                    3 -> {holder.star1IV.setImageResource(R.drawable.star_14441715)
//                        holder.star2IV.setImageResource(R.drawable.star_14441715)
//                        holder.star3IV.setImageResource(R.drawable.star_14441715)}
//
//                    else -> {holder.star1IV.setImageResource(R.drawable.star_2956792)
//                        holder.star2IV.setImageResource(R.drawable.star_2956792)
//                        holder.star3IV.setImageResource(R.drawable.star_2956792)}
//
//                }
//
////                when(totalCorrectAns){
////                    1 -> holder.star1IV.setImageRes ource(R.drawable.star_14441715)
////                    2 -> holder.star2IV.setImageResource(R.drawable.star_14441715)
////                    3 -> holder.star3IV.setImageResource(R.drawable.star_14441715)
////
////                    else -> holder.star3IV.setImageResource(R.drawable.star_2956792)
////
////                }
//            }
//        }

    }

    override fun getItemCount(): Int {
        return listOfLevel.size
    }

    private  fun moveToAnotherActivity(levelInt : Int  ){
        val intent = Intent(context , QuizActivity::class.java)
        intent.putExtra("level",levelInt)
        intent.putParcelableArrayListExtra("list", ArrayList(listOfLevel))

        println("${levelInt} __________________________mila kys level }}}}}}}}}}}}}}")
        context.startActivity(intent)
    }

    private fun fetchData(holder: LevelViewHolder , i: Int) : Any?{

        FirebaseApp.initializeApp(context)
        val db = Firebase.firestore
        val userId = Firebase.auth.currentUser?.uid
        var correctAns : Any? = 0

        println("1234 Mic test")

//        val userId = "7zFJzsVs9sjOiccH0yzV"
        if(userId != null) {
            println(" ___________user id is Not null $userId __________________")
            val userRef = db.collection("users").document(userId)

            userRef.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {


                    val existingData = documentSnapshot.data ?: hashMapOf()
                    println("1234 Mic test")
//
//                    println(">>>>>>>>>>>>>>>>>>>>>${existingData["0"]}")
//                    println(">>>>>>>>>>>>>>>>>>>>>>>>>${documentSnapshot.data}")


//                    println(">>>>>${existingData.size}____________________________________________________________")
//                for (i in 0..existingData.size){
                    val levelData = existingData["$i"] as? Map<String, Any>?
//            println("((((_____PLevel__((((${previousLevel}++++++++++++++++++++++++++++++")
//                    if(listOfLevel[position].level.toString() == ){
                    if (levelData != null) {
//                        println(">>>>>>${i}__>>>>>>>${levelData["correctAns"]}")

                         correctAns = levelData["correctAns"]

//                        println("____________Correct--------->${correctAns}")

                        when (correctAns) {
                            "1" -> holder.star1IV.setImageResource(R.drawable.star_14441715)

                            "2" -> {
                                holder.star1IV.setImageResource(R.drawable.star_14441715)
                                holder.star2IV.setImageResource(R.drawable.star_14441715)
                                println("?????/$i true")
                            }

                            "3" -> {
                                holder.star1IV.setImageResource(R.drawable.star_14441715)
                                holder.star2IV.setImageResource(R.drawable.star_14441715)
                                holder.star3IV.setImageResource(R.drawable.star_14441715)
                            }

                            else -> {
                                holder.star1IV.setImageResource(R.drawable.star_2956792)
                                holder.star2IV.setImageResource(R.drawable.star_2956792)
                                holder.star3IV.setImageResource(R.drawable.star_2956792)
                                println("????? false")
                            }


//                        }

//
//                    }
                        }
                    } else {
                        println("List is null or not of expected type")
                    }


//                for(i in existingData.size .. listOfLevel.size ){
//                    holder.star1IV.setImageResource(R.drawable.star_2956792)
//                    holder.star2IV.setImageResource(R.drawable.star_2956792)
//                    holder.star3IV.setImageResource(R.drawable.star_2956792)
//                    println("?????/$i out")
//                }

//                println(">>>>>>>>>>>>>>>>>>>>>>>>>${hashMapOf()}")

                    // Add dataOfAllLevel to the existing data

                } else {
                    Log.d(ContentValues.TAG, "Document does not exist")
                }
            }.addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error getting document", e)
            }
        }else{
            println(" ___________user id is null __________________")
                    holder.star1IV.setImageResource(R.drawable.star_2956792)
                    holder.star2IV.setImageResource(R.drawable.star_2956792)
                    holder.star3IV.setImageResource(R.drawable.star_2956792)
        }


       return correctAns
    }


}