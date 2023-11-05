package com.divyank.coroutinesdemo

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var countertext :TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        countertext = findViewById(R.id.counter)
        Log.d(TAG,"${Thread.currentThread().name}")
    }
    fun updatecounter(view: View) {
        countertext.text = "${countertext.text.toString().toInt()+1}"
    }
    fun longprocess(view :View){
        Log.d(TAG,"process")
        Log.d(TAG,"${Thread.currentThread().name}")
        for(i in 0..10000000L){
        }
    }
}