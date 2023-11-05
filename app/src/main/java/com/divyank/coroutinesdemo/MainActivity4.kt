package com.divyank.coroutinesdemo

import android.content.ContentUris
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// async function demo
class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        CoroutineScope(Dispatchers.IO).launch {
            printfollowers()
        }
    }


    private suspend fun  printfollowers2(){
        val fb = CoroutineScope(Dispatchers.IO).async {
            getfbfollower()
        }
        val insta = CoroutineScope(Dispatchers.IO).async {
            getinstafollower()
        }
        Log.d(TAG,"FB - ${fb.await()}, Insta - ${insta.await()}")

    }

    private suspend fun printfollowers3(){
        CoroutineScope(Dispatchers.IO).launch {
            var fb = getfbfollower()   // jab tak data ni ata aage execute ni hoga dono ek ek second lagayege
            var insta = getinstafollower()  // 2 sec lage dono mila kr
            Log.d(TAG,"FB - ${fb}, Insta - ${insta}")
        }
    }
    // to improve performance since getfb and getinsta are independent ----use async
    private suspend fun printfollowers() {
        CoroutineScope(Dispatchers.IO).launch {
        var fb = async {  getfbfollower()}
        var insta = async {  getinstafollower()}  //inse lagbhag 1 second hi lagega
            Log.d(TAG,"FB - ${fb.await()}, Insta - ${insta.await()}")
    }
    }
    private suspend fun getfbfollower():Int{
        delay(1000)
        return 54
    }
    private suspend fun getinstafollower():Int{
        delay(1000)
        return 418
    }
}