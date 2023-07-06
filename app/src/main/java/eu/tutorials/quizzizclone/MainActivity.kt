package eu.tutorials.quizzizclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private var edtCode: EditText? = null
    private var btnStart: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtCode = findViewById(R.id.edit_code)
        btnStart = findViewById(R.id.btn_start)
        btnStart?.setOnClickListener {
            val code = edtCode?.text?.toString();
            if(!code.isNullOrEmpty()){
                val intent = Intent(this, QuizzizQuestionActivity::class.java)
                intent.putExtra(Constants.CODE,"${edtCode?.text}")
                startActivity(intent)
                finish()
            }else{
                Snackbar.make(it,"Vui lòng điền đầy đủ các thông tin vào", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}