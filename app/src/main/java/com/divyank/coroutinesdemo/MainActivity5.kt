package com.divyank.coroutinesdemo

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

//parent and child jobs
class MainActivity5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)
    }
}



private suspend fun execute(){
    val parentjob = CoroutineScope(Dispatchers.Main).launch {
        Log.d(TAG,"Parent - $coroutineContext")
        val childjob = launch(Dispatchers.IO) {     //child job takes scope of parent job
            Log.d(TAG,"Parent - $coroutineContext")
        }
    }
}