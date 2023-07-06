package eu.tutorials.quizzizclone

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import eu.tutorials.quizzizclone.model.Question
import java.lang.Exception

class QuizzizQuestionActivity : AppCompatActivity(), OnClickListener {
    private var mQuestion: TextView? = null;
    private var mOptionOne: TextView? = null;
    private var mOptionTwo: TextView? = null;
    private var mOptionThree: TextView? = null;
    private var mOptionFour: TextView? = null;
    private var tv_Number: TextView? = null;
    private var mCurrentOption = 1;
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedQuestion: Int? = null
    private var mLayoutMain: LinearLayout? = null
    private var mCode: String? = null
    private var correctAnswer: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizziz_question)
        mCode = intent.getStringExtra(Constants.CODE)
        anhXa()
        mQuestionList = Constants.getQuestions();
        getQuestion()
        defaultOption()
    }


    private fun anhXa() {
        mQuestion = findViewById(R.id.question)
        mOptionOne = findViewById(R.id.questionOne)
        mOptionTwo = findViewById(R.id.questionTwo)
        mOptionThree = findViewById(R.id.questionThree)
        mOptionFour = findViewById(R.id.questionFour)
        mLayoutMain = findViewById(R.id.LayoutMain)
        tv_Number = findViewById(R.id.tv_Number)

        mOptionOne?.setOnClickListener(this)
        mOptionTwo?.setOnClickListener(this)
        mOptionThree?.setOnClickListener(this)
        mOptionFour?.setOnClickListener(this)
    }

    private fun getQuestion() {
        defaultOption()
        val question = mQuestionList!![mCurrentOption - 1];
        mQuestion?.text = question.question;
        mOptionOne?.text = question.optionOne;
        mOptionTwo?.text = question.optionTwo;
        mOptionThree?.text = question.optionThree;
        mOptionFour?.text = question.optionFour;
    }

    private fun defaultOption() {
        val arrayOptions = ArrayList<TextView>();
        mOptionOne?.let {
            arrayOptions.add(0, it);
        }
        mOptionTwo?.let {
            arrayOptions.add(1, it);
        }
        mOptionThree?.let {
            arrayOptions.add(2, it);
        }
        mOptionFour?.let {
            arrayOptions.add(3, it);
        }
        val drawableList = ArrayList<Int>()
        drawableList.add(R.drawable.bg_option_one)
        drawableList.add(R.drawable.bg_option_two)
        drawableList.add(R.drawable.bg_option_three)
        drawableList.add(R.drawable.bg_option_four)

        for (value in arrayOptions.indices) {
            arrayOptions[value].setTextColor(Color.parseColor("#F8F8F8"))
            arrayOptions[value].typeface = Typeface.DEFAULT
            arrayOptions[value].background = ContextCompat.getDrawable(this, drawableList[value])
            arrayOptions[value].visibility = View.VISIBLE
        }
    }

    private fun selectedOptionView(value: Map<Int, TextView>, selected: Int) {
        mSelectedQuestion = selected
        for (index in value.keys) {
            if (index != selected) {
                value[index]?.visibility = View.INVISIBLE
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            answer(value)
        }, 1300)
    }

    private fun answer(value: Map<Int, TextView>) {
        val answer = mQuestionList?.get(mCurrentOption - 1)
        if (mSelectedQuestion == answer?.correctAnswer) {
            correctAnswer++
            value[mSelectedQuestion]?.background =
                ContextCompat.getDrawable(this, R.drawable.bg_select_correct_option)
            Handler(Looper.getMainLooper()).postDelayed({
                val animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.move_left)
                mLayoutMain?.startAnimation(animationFadeIn)
            }, 550)
            Handler(Looper.getMainLooper()).postDelayed({
                tv_Number?.text = "${mCurrentOption + 1}"
                tv_Number?.visibility = View.VISIBLE
                val animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
                tv_Number?.startAnimation(animationFadeIn)
            }, 300)
        } else {
            value[mSelectedQuestion]?.background =
                ContextCompat.getDrawable(this, R.drawable.bg_select_wrong_option)
            value[answer?.correctAnswer]?.visibility = View.VISIBLE
            value[answer?.correctAnswer]?.background =
                ContextCompat.getDrawable(this, R.drawable.bg_correct_option)
            Handler(Looper.getMainLooper()).postDelayed({
                val animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.move_left)
                mLayoutMain?.startAnimation(animationFadeIn)
            }, 550)

            Handler(Looper.getMainLooper()).postDelayed({
                tv_Number?.text = "${mCurrentOption + 1}"
                tv_Number?.visibility = View.VISIBLE
                val animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
                tv_Number?.startAnimation(animationFadeIn)
            }, 300)

        }
        Handler(Looper.getMainLooper()).postDelayed({
            mCurrentOption++;
            when {
                mCurrentOption <= mQuestionList!!.size -> {
                    getQuestion();
                    Handler(Looper.getMainLooper()).postDelayed({
                        val animationMoveRight = AnimationUtils.loadAnimation(this, R.anim.move_right)
                        mLayoutMain?.startAnimation(animationMoveRight)
                    }, 300)
                }

                else -> {
                    val intent = Intent(this, CongratulationActivity::class.java)
                    intent.putExtra(Constants.CODE, mCode)
                    intent.putExtra(Constants.CORRECT_ANSWER, correctAnswer)
                    startActivity(intent);
                }
            }
        }, 700)
    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.questionOne -> {
                selectedOptionView(getListTextView(), 1)
            }

            R.id.questionTwo -> {
                selectedOptionView(getListTextView(), 2)
            }

            R.id.questionThree -> {
                selectedOptionView(getListTextView(), 3)
            }

            R.id.questionFour -> {
                selectedOptionView(getListTextView(), 4)

            }
        }
    }

    private fun getListTextView(): Map<Int, TextView> {
        val map = mutableMapOf<Int, TextView>()
        mOptionOne?.let {
            map.put(1, it);
        }
        mOptionTwo?.let {
            map.put(2, it);
        }
        mOptionThree?.let {
            map.put(3, it);
        }
        mOptionFour?.let {
            map.put(4, it);
        }
        return map
    }

    private fun loadAnimation(textView: TextView) {
        val animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        textView.startAnimation(animationFadeIn)
    }
}