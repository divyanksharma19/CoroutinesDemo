package com.divyank.coroutinesdemo

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show()
        Log.d(TAG,"hello")
        CoroutineScope(Dispatchers.Main).launch{
            task1()
        }
        CoroutineScope(Dispatchers.Main).launch{
            task2()
        }
    }

    //suspending function can only be called by suspending function or coroutine
    //task1 and task2 are cooperating functions
    suspend fun task1(){
        Log.d("This","Starting task 1")
        Toast.makeText(this, "task1", Toast.LENGTH_SHORT).show()
        yield()      // or delay(1000)  suspension point banane keliye,  function suspend kardega aur agar koi coroutine hoga to chala dega
        Log.d(TAG,"Ending task 1")
    }
    suspend fun task2(){
        Log.d(TAG,"Starting task 2")
        //yield()
        delay(1000)
        Log.d(TAG,"Ending task2 ")
    }

}
