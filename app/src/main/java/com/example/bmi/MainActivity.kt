package com.example.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weight = findViewById<EditText>(R.id.etWeight)
        val height = findViewById<EditText>(R.id.etHeight)
        val calculate = findViewById<Button>(R.id.btnCalculate)

        calculate.setOnClickListener {
            val kg = weight.text.toString()
            val cm = height.text.toString()
            if (validateInput(kg,cm)) {
                val answer = kg.toFloat() / ((cm.toFloat() / 100) * (cm.toFloat() / 100))
                val answer2D = String.format("%.2f", answer).toFloat()
                displayResult((answer2D))
            }
        }
    }

    private fun validateInput(kg:String?,cm:String?):Boolean{
        return when{
            kg.isNullOrEmpty()->{
                Toast.makeText(this@MainActivity,"Please Provide Weight",Toast.LENGTH_LONG).show()
                return false
            }
            cm.isNullOrEmpty()->{
                Toast.makeText(this@MainActivity,"Please Provide Height",Toast.LENGTH_LONG).show()
                return false
            }
            else->return true
        }
    }

    private fun displayResult(answer2D: Float) {
        val result = findViewById<TextView>(R.id.tvResult)
        val detail = findViewById<TextView>(R.id.tvDetails)
        val info = findViewById<TextView>(R.id.tvInfo)

        info.text = "Normal range from 18.50-24.99"
        result.text = answer2D.toString()
        var comment =""
        var color = 0

        when{
            answer2D<18.50->{
                comment = "underweight"
                color = R.color.under
            }
            answer2D in 18.50..24.99->{
                comment = "Normal"
                color = R.color.normal
            }
            answer2D in 25.00..29.99->{
                comment = "overweight"
                color = R.color.over
            }
            answer2D>29.99->{
                comment = "obese"
                color = R.color.obese
            }
        }
        detail.text =comment
       detail.setTextColor(ContextCompat.getColor(this,color))
    }
}