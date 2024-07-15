package com.pratiksha.quizgame

import com.pratiksha.quizgame.db.QuizInterface
import java.time.temporal.TemporalAmount

class Repo(
    private val quizInterface: QuizInterface
) {

    fun getQuiz( amount : Int, category : Int, difficulty : String)  = quizInterface.getQuiz(amount , category , difficulty)

}