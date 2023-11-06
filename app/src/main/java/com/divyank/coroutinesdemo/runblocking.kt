package com.divyank.coroutinesdemo

import android.util.Log
import androidx.constraintlayout.helper.widget.MotionEffect.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
//    CoroutineScope(Dispatchers.IO).launch{
//        delay(1000)
//        println("Hello")
//    }
  //  println("World")     //check karega koi thread block to ni aur print karke process finish krdega sirf world print hoga

//  thread.sleep(2000) // world ko print karne keliye


   runBlocking {         //jabtak coroutines complete ni hojate tab tak thread ko rok kar rakhega
       launch {
           delay(1000)
           println("Hello")
       }
   }
    println("World")



}

// or
//fun main = runBlocking {
//    launch{
//        delay(1000)
//        Log.d(TAG,"Hello")
//    }
//    Log.d(TAG."World")
//}