package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.BoringLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass : ActivityMainBinding


    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var  switchTheme:Switch
    private lateinit var textView:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("My @log", "Hi")
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        switchTheme = findViewById(R.id.switch_theme)
        textView=findViewById(R.id.textView)
        switchTheme.isChecked=getSavedThemeState()

        switchTheme.setOnCheckedChangeListener {_, isChecked ->
            if (isChecked){
                setDarkTheme()
            }
            else {
                setLightTheme()
            }
            saveThemeState(isChecked)
        }
        if(switchTheme.isChecked){
            setDarkTheme()
        }
        else{
            setLightTheme()
        }







        bindingClass.bottomNavigationView.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.home -> replaceFragment(Home())
                R.id.settings -> replaceFragment(Settings())

                else -> {

                }

            }
            true
        }

        bindingClass.btResult.setOnClickListener {
            if (bindingClass.userText.text.toString() != "") {
                val userText = bindingClass.userText.text.toString()

                var EvenAmount = 0
                var NotEvenAmount = 0
                var SumEvenAmount = 0



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
    private fun setLightTheme(){

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
    private fun setDarkTheme(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

    }
    private fun getSavedThemeState():Boolean{
        val sharedPreferences : SharedPreferences = getSharedPreferences("Theme Prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("IsDarkTheme", false)
    }
    private fun saveThemeState(IsDarkTheme:Boolean){
        val sharedPreferences : SharedPreferences = getSharedPreferences("Theme Prefs", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean("IsDarkTheme", IsDarkTheme)
        editor.apply()

    }

    override fun onResume() {
        super.onResume()
        val textColor : Int = if(switchTheme.isChecked)R.color.white else R.color.black
        textView.setTextColor(ContextCompat.getColor(this, textColor))
    }



    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()


    }

}
