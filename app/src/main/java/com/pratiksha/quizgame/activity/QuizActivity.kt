package com.pratiksha.quizgame.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import com.pratiksha.quizgame.Repo
import com.pratiksha.quizgame.ViewModel
import com.pratiksha.quizgame.ViewModelFactory
import com.pratiksha.quizgame.db.Levels
import com.pratiksha.quizgame.databinding.ActivityQuizBinding
import com.pratiksha.quizgame.db.QuizStructure
import com.pratiksha.quizgame.db.RetrofitBuilder
import retrofit2.Response

const val BASE_URL = "https://opentdb.com/"

class QuizActivity : AppCompatActivity() {
    private lateinit var binding : ActivityQuizBinding
    private lateinit var questionTV : TextView
    private lateinit var option1Btn : Button
    private lateinit var option2Btn : Button
    private lateinit var option3Btn : Button
    private lateinit var option4Btn : Button
    private lateinit var nextBtn : Button
    private lateinit var repo : Repo
    private lateinit var viewModel : ViewModel
    private lateinit var viewModelFactory: ViewModelFactory

    private var index = 0
    var totalCorrectAnswer = 0
    private val questionSize = 3
    var categoryNo = 32
    var level : Int? = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        questionTV = binding.questionTV
        option1Btn = binding.option1Btn
        option2Btn = binding.option2Btn
        option3Btn = binding.option3Btn
        option4Btn = binding.option4Btn
        nextBtn = binding.nextBtn


        val bundle: Bundle? = intent.extras
         level = bundle?.getInt("level")
        Log.i("Level1", " ---. ab check kr level $level")
        println("---++++++++-----------$level------------------------")
        val list = intent.getParcelableArrayListExtra<Levels>("list")
        println("---++++++++$list------------------------")

        val listIndex : Int? = level
        categoryNo = listIndex?.let { list?.get(listIndex-1)?.categoryNo } ?: 32

        println("---++++++++$categoryNo ------------------------")

        supportActionBar?.title = listIndex?.let { "${list?.get(listIndex)?.category}" } ?: "QuizGame"


//        fetchData(index)
        fetchDataMVVM()


        nextBtn.setOnClickListener {

//            index++

//            if(index < questionSize) {
//                resetOPtionBackground()
//                fetchDataMVVM(index)
//            }else{

            if(index > questionSize){
                val intent = Intent(this, FinishActivity::class.java )
                intent.putExtra("correctAnswer",totalCorrectAnswer)
                intent.putExtra("questionSize", questionSize)
                intent.putExtra("level", level)

                startActivity(intent)
            }

        }

    }

    private fun fetchData(index : Int){
//        val retrofit = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(BASE_URL)
//            .build()
//            .create(QuizInterface::class.java)
//
//        val response = retrofit.getQuiz(questionSize,32,"easy")
//        response.enqueue(object : Callback<QuizStructure> {
//            override fun onResponse(call: Call<QuizStructure>, response: Response<QuizStructure>) {
//                val responseBody = response.body()
//
//                if(response.isSuccessful && responseBody != null){
//
//                    if(responseBody.results[index].incorrect_answers.size < 3){
//                        println("____________OOps (-_-) option size is less then 3")
//                        fetchData(index)
//                    }else {
//
//                        val question = responseBody.results[index].question
//
//                        val decodedQuestion = HtmlCompat.fromHtml(question, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
//
//                        questionTV.text = decodedQuestion
//
//
//                        var optionsArray: Array<String> = arrayOf(
//                            responseBody.results[index].incorrect_answers[0],
//                            responseBody.results[index].incorrect_answers[1],
//                            responseBody.results[index].incorrect_answers[2],
//                            responseBody.results[index].correct_answer
//                        )
//
////                    optionsArray[0] = responseBody.results[index].incorrect_answers[0]
////                    optionsArray[1] = responseBody.results[index].incorrect_answers[1]
////                    optionsArray[2] = responseBody.results[index].incorrect_answers[2]
////                    optionsArray[3] = responseBody.results[index].correct_answer
//
//                        optionsArray.shuffle()
//
//                        option1Btn.text = HtmlCompat.fromHtml(optionsArray[0], HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
//                        option2Btn.text = HtmlCompat.fromHtml(optionsArray[1], HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
//                        option3Btn.text = HtmlCompat.fromHtml(optionsArray[2], HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
//                        option4Btn.text = HtmlCompat.fromHtml(optionsArray[3], HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
//
//                        println("__________________Shuffled Array: ${optionsArray.contentToString()}")
//
//                        setOnoptionListener(option1Btn, responseBody.results[index].correct_answer)
//                        setOnoptionListener(option2Btn, responseBody.results[index].correct_answer)
//                        setOnoptionListener(option3Btn, responseBody.results[index].correct_answer)
//                        setOnoptionListener(option4Btn, responseBody.results[index].correct_answer)
//
//
//                        println("---------------------${questionTV.text}")
//                        println("---------------------${responseBody.results}")
//                    }
//
//                }
//
//            }
//
//            override fun onFailure(call: Call<QuizStructure>, t: Throwable) {
//                println("OOOOOOOoooooppppppppssssssss its Show error ")
//            }
//        })


    }

     fun fetchDataMVVM() {

         viewModel.getQuiz(questionSize, categoryNo, "easy")
         viewModel.quizLiveData.observe(this) {

             val responseBody = it
             Log.i("mila", it.toString())

             if (responseBody != null) {


                 if (index == 0) {
                     Log.i("responseAAYA", "$index -------> ${responseBody.results[index].question} ")
                     chal(responseBody)

                     index++

                 }



                 nextBtn.setOnClickListener {

                    if(index < questionSize) {
                        Log.i(
                            "responseAAYA",
                            "$index -------> ${responseBody.results[index].question} "
                        )
                        if (index < questionSize) {
                            resetOPtionBackground()
                            chal(responseBody)
                        }
                        index++

                    }else {
                         val intent = Intent(this, FinishActivity::class.java )
                         intent.putExtra("correctAnswer",totalCorrectAnswer)
                         intent.putExtra("questionSize", questionSize)
                         intent.putExtra("level", level)

                         startActivity(intent)
                     }






                 }

             }
         }
     }



    fun chal(responseBody :  QuizStructure){

        if (responseBody.results[index].incorrect_answers.size < 3 || responseBody.response_code == 5) {
            println("____________OOps (-_-) option size is less then 3")
            fetchDataMVVM()
        } else {

            val question = responseBody.results[index].question

            val decodedQuestion =
                HtmlCompat.fromHtml(question, HtmlCompat.FROM_HTML_MODE_LEGACY)
                    .toString()

            questionTV.text = decodedQuestion


            var optionsArray: Array<String> = arrayOf(
                responseBody.results[index].incorrect_answers[0],
                responseBody.results[index].incorrect_answers[1],
                responseBody.results[index].incorrect_answers[2],
                responseBody.results[index].correct_answer
            )

//                    optionsArray[0] = responseBody.results[index].incorrect_answers[0]
//                    optionsArray[1] = responseBody.results[index].incorrect_answers[1]
//                    optionsArray[2] = responseBody.results[index].incorrect_answers[2]
//                    optionsArray[3] = responseBody.results[index].correct_answer

            optionsArray.shuffle()

            option1Btn.text =
                HtmlCompat.fromHtml(optionsArray[0], HtmlCompat.FROM_HTML_MODE_LEGACY)
                    .toString()
            option2Btn.text =
                HtmlCompat.fromHtml(optionsArray[1], HtmlCompat.FROM_HTML_MODE_LEGACY)
                    .toString()
            option3Btn.text =
                HtmlCompat.fromHtml(optionsArray[2], HtmlCompat.FROM_HTML_MODE_LEGACY)
                    .toString()
            option4Btn.text =
                HtmlCompat.fromHtml(optionsArray[3], HtmlCompat.FROM_HTML_MODE_LEGACY)
                    .toString()

            println("__________________Shuffled Array: ${optionsArray.contentToString()}")

            setOnoptionListener(option1Btn, responseBody.results[index].correct_answer)
            setOnoptionListener(option2Btn, responseBody.results[index].correct_answer)
            setOnoptionListener(option3Btn, responseBody.results[index].correct_answer)
            setOnoptionListener(option4Btn, responseBody.results[index].correct_answer)


            println("---------------------${questionTV.text}")
            println("---------------------${responseBody.results}")
        }
    }







    private fun setOnoptionListener(option: Button, correct_answer: String){

        option.setOnClickListener {
            val selectedAns = option.text.toString()



            if(selectedAns == correct_answer){
                option.setBackgroundColor(Color.GREEN)
                totalCorrectAnswer++

                println("${totalCorrectAnswer}___________${selectedAns == correct_answer}")

            }else{
                option.setBackgroundColor(Color.RED)

                highlightCorrectAnswer(correct_answer)
            }

            disableOptionClickListener()

        }
    }

    private fun highlightCorrectAnswer(correctAnswer: String){

        val options = listOf(option1Btn,option2Btn,option3Btn,option4Btn)

        for (optionText in options){
            if(optionText.text.toString() == correctAnswer){
                optionText.setBackgroundColor(Color.GREEN)
            }
        }
    }

    private fun disableOptionClickListener(){
        option1Btn.isClickable = false
        option2Btn.isClickable = false
        option3Btn.isClickable = false
        option4Btn.isClickable = false
    }

    private fun resetOPtionBackground(){
        val options = listOf(option1Btn,option2Btn,option3Btn,option4Btn)

        for (optionText in options){
            optionText.setBackgroundColor(Color.WHITE)
        }
    }


    fun init(){

        repo = Repo(RetrofitBuilder.getInstace())
        viewModelFactory = ViewModelFactory(repo)
        viewModel = ViewModelProvider(this , viewModelFactory).get(ViewModel::class.java)
    }
}