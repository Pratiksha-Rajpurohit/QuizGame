package com.pratiksha.quizgame.db

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