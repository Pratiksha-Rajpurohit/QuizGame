package com.pratiksha.quizgame.interfaces

import com.pratiksha.quizgame.classes.QuizStructure
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizInterface {
    @GET("api.php")
    fun getQuiz(
        @Query("amount")amount : Int,
        @Query("category")category : Int,
        @Query("difficulty")difficulty : String
    ): Call<QuizStructure>
}