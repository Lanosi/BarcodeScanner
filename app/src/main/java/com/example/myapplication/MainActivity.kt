package com.example.myapplication

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.BoringLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("My @log", "Hi")
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)


        bindingClass.btResult.setOnClickListener {
            if (bindingClass.userText.text.toString() != "") {
                val userText = bindingClass.userText.text.toString()

                var EvenAmount = 0
                var NotEvenAmount = 0
                var SumEvenAmount = 0
//тест ч


                for ((index, value) in userText.withIndex()) {
                    if (index % 2 == 0) {
                        EvenAmount += value.toString().toInt()
                    }
                }
                EvenAmount *= 3

                for ((index, value) in userText.withIndex()) {
                    if (index % 2 != 0 && index != userText.lastIndex) {//возможно не верно
                        NotEvenAmount += value.toString().toInt()
                    }
                }
                SumEvenAmount = (EvenAmount + NotEvenAmount) % 10

                val finalres = 10 - SumEvenAmount
                if (finalres.toString() == userText.last().toString() ){
                    bindingClass.tvResult.isVisible = true
                    bindingClass.tvResult.text = "Все в порядке это оригинал"
                }
                else {
                    bindingClass.tvResult.isVisible = true
                    bindingClass.tvResult.text = "Фальшифка"
                }
            }

            else {
                bindingClass.tvResult.isVisible = true
                bindingClass.tvResult.text = "Что-то введенно не верно"
            }
        }

    }

}
