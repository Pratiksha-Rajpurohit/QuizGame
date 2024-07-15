package com.pratiksha.quizgame.db

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {

    companion object{
        var quizInterface : QuizInterface?  = null

        fun getInstace() : QuizInterface {
            if (quizInterface == null) {

                 quizInterface = Retrofit.Builder()
                    .baseUrl("https://opentdb.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(QuizInterface::class.java)
            }

            return quizInterface!!
        }


    }


}