package com.divyank.coroutinesdemo

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



// Note - launch is used when we dont care about the result(Fire and Forget)  job ka instance milta hai
// async  -  is used when we expect result/output from coroutine  , return a deferred type object which is resolved in future
class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        CoroutineScope(Dispatchers.IO).launch{
       printfollowers()
   }

    }
    private suspend fun printfollowers(){
        var followers =0
        //CoroutineScope(Dispatchers.Main).launch{
//        followers = getfollowers()
//        }

        //when log value without using join value of followers will be 0 only
        val job = CoroutineScope(Dispatchers.IO).launch{
            followers = getfollowers()
        }
        job.join()   //dont proceed further in code until all coroutines are completed
        Log.d(TAG,followers.toString())
    }

    //async / await - async/await simplifies asynchronous code, making it easier to read and maintain.
    // Coroutines execute long-running tasks without blocking the main thread or freezing the UI.
    // async launches a coroutine and returns a Deferred object of type of last return value; await retrieves the result.
    private suspend fun printfollowers1(){
        var followers =0;
        val job = CoroutineScope(Dispatchers.IO).async{
            getfollowers()
        }
        Log.d(TAG,job.await().toString())
    }
    suspend private fun getfollowers():Int{
        delay(1000)
            return 54
    }

}
