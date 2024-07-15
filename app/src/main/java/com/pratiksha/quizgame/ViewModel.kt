package com.pratiksha.quizgame


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pratiksha.quizgame.db.QuizStructure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ViewModel(
    private val repo : Repo
): ViewModel() {

//        val quizLiveData = MutableLiveData<QuizStructure>()
//
//        fun getQuiz(amount : Int , category : Int , difficulty : String) {
//            val response = repo.getQuiz(amount , category , difficulty)
//
//            if (response.isSuccessful){
//                quizLiveData.postValue(response.body())
//
//            }
//        }

    private val _quizLiveData = MutableLiveData<QuizStructure>()
    val quizLiveData: LiveData<QuizStructure> get() = _quizLiveData

     fun getQuiz(amount: Int, category: Int, difficulty: String) {


             try {
                 val call = repo.getQuiz(amount, category, difficulty)
                 call.enqueue(object : Callback<QuizStructure> {
                     override fun onResponse(
                         call: Call<QuizStructure>,
                         response: Response<QuizStructure>
                     ) {
                         if (response.isSuccessful) {
                             response.body()?.let {
                                 Log.i("responseAAYA", "Ha ${it}")
                                 _quizLiveData.postValue(it)
                             } ?: run {
                                 Log.i("responseAAYA", "Response body is null")
                             }
                         } else {
                             Log.i(
                                 "responseAAYA",
                                 "Response not successful: ${response.errorBody()?.string()}"
                             )
                         }
                     }

                     override fun onFailure(call: Call<QuizStructure>, t: Throwable) {
                         Log.e("responseAAYA", "Failed to get quiz data", t)
                     }
                 })
             } catch (e: Exception) {
                 Log.e("responseAAYA", "Exception occurred while getting quiz", e)
             }
         }

     }
