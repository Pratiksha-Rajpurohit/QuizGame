package com.pratiksha.quizgame.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.pratiksha.quizgame.adopter.LevelAdoptor
import com.pratiksha.quizgame.db.Levels
import com.pratiksha.quizgame.db.PreviousLevels
import com.pratiksha.quizgame.databinding.ActivityLevelsBinding


class LevelsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLevelsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLevelsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Levels"


        val rv : RecyclerView = binding.rv

        val listOfLevel = mutableListOf<Levels>()
        val dataOfAllLevel = mutableListOf<PreviousLevels>()

        val entertainment = listOf<String>(
            "Anime","Cartoon","Books","Film","Music","Television","VideoGames", "BoardGames"
        )

        val categoryNo = listOf<Int>(
            31,32,10,11,12,14,15,16
        )


        for (i in 1..entertainment.size){

            listOfLevel.add(Levels(i,entertainment[i-1],categoryNo[i-1]))
        }

        println(listOfLevel)

        val bundle: Bundle? = intent.extras
        val totalCorrectAns = bundle?.getInt("totalCorrectAns")
        val previousLevel = bundle?.getString("level")
        println("((((_____ActPLevel__((((${previousLevel}++++++++++++++++++++++++++++++")
        println("((((_____TotalC__((((${totalCorrectAns}++++++++++++++++++++++++++++++")

        var index = 0

        if(totalCorrectAns != null && previousLevel != null){

            dataOfAllLevel.add(
                PreviousLevels(
                    totalCorrectAns!!,
                    previousLevel
                )
            )
            index = previousLevel.toInt()-1
        }else{
            dataOfAllLevel.add(
                PreviousLevels(
                    -1,
                    "-1"
                )
            )
        }

        val db = Firebase.firestore


        val levelAdapter = LevelAdoptor(listOfLevel,this, dataOfAllLevel,index)
        rv.layoutManager =  GridLayoutManager(this,2)
        rv.adapter = levelAdapter





        }
}