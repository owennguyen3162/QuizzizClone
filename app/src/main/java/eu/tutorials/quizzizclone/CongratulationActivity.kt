package eu.tutorials.quizzizclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class CongratulationActivity : AppCompatActivity() {
    private var correctAnswer: TextView? = null
    private var codeId: TextView? = null
    private var btnFinish: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_congratulation)

        correctAnswer = findViewById(R.id.correctAnswers)
        codeId = findViewById(R.id.codeId)
        btnFinish = findViewById(R.id.btn_finish)
        val correct: Int = intent.getIntExtra(Constants.CORRECT_ANSWER,0)
        correctAnswer?.text = "ĐIỂM CỦA BẠN ${correct}/7"
        codeId?.text = intent.getStringExtra(Constants.CODE)

        btnFinish?.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}