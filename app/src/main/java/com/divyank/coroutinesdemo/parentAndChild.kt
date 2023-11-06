package com.divyank.coroutinesdemo

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.helper.widget.MotionEffect.TAG
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class parentAndChild : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_and_child)
        CoroutineScope(Dispatchers.Main).launch{
            execute5()
        }
    }

    // to simple understand execution of parent and child
    private suspend fun execute1(){
        val parentjob = CoroutineScope(Dispatchers.Main).launch {
            Log.d(TAG,"Parent job started")
            val childjob = launch(Dispatchers.IO) {     //child job takes scope of parent job
                Log.d(TAG,"Child job started")
                delay(5000)
                Log.d(TAG,"Child job ended")
            }
            delay(3000)
            Log.d(TAG,"Parent job ended")
        }
        parentjob.join()
        Log.d(TAG,"PARENT JOB COMPLETED")
    }


    // to understand cancel
    private suspend fun execute2(){
        val parentjob = CoroutineScope(Dispatchers.Main).launch {
            Log.d(TAG,"Parent job started")
            val childjob = launch(Dispatchers.IO) {     //child job takes scope of parent job
                Log.d(TAG,"Child job started")
                delay(5000)
                Log.d(TAG,"Child job ended")
            }
            delay(3000)  //ye 3 sec lega child job 5 tu end hone se pehle cancel hojayega
            childjob.cancel()
            Log.d(TAG,"Parent job ended")
        }
        parentjob.join()
        Log.d(TAG,"PARENT JOB COMPLETED")
    }


    // we can add try and catch to handle such cancel of coroutines
    private suspend fun execute3(){
        val parentjob = CoroutineScope(Dispatchers.Main).launch {
            Log.d(TAG,"Parent job started")
            val childjob = launch(Dispatchers.IO) {
                try{//child job takes scope of parent job
                Log.d(TAG,"Child job started")
                delay(5000)
                Log.d(TAG,"Child job ended")
            }
                catch(e:CancellationException) {
                    Log.d(TAG,"child job canceled")

                }
            }
            delay(3000)  //ye 3 sec lega child job 5 tu end hone se pehle cancel hojayega
            childjob.cancel()       //if parentjob canceled child job automatically get canceled
            Log.d(TAG,"Parent job ended")
        }
        parentjob.join()
        Log.d(TAG,"PARENT JOB COMPLETED")
    }

    //part2 -



    private suspend fun execute4(){
        val parentjob = CoroutineScope(Dispatchers.IO).launch {
            for(j in 1..1000){
                executelongrunningtask()
                Log.d(TAG,j.toString())
            }
        }
        delay(50)
        Log.d(TAG,"Parent job ended") //parent job end hone k bawajood bhi j ki value print hoti rehegi  to coroutine me check karna padega ki active hai ya ni
        parentjob.cancel()
        parentjob.join()
        Log.d(TAG,"parent job completed")
    }
    //coroutines cancel karne per bhi chalta reh  sakta to check lagana jaruri hai

    private suspend fun execute5(){
        val parentjob = CoroutineScope(Dispatchers.IO).launch {
                    //to check ki kahi coroutine cancel to ni hogi
                for (j in 1..1000) {
                    if(isActive) {         // inactive hone k baad bhi kuch chala deta hai
                        executelongrunningtask()
                        Log.d(TAG, j.toString())
                    }
                }

        }
        delay(20)
        Log.d(TAG,"Parent job canceled") //parent job end hone k bawajood bhi j ki value print hoti rehegi  to coroutine me check karna padega ki active hai ya ni
        parentjob.cancel()
        parentjob.join()
        Log.d(TAG,"parent job completed")
    }
    private fun executelongrunningtask(){

        for(i in 1..1000000){

        }
    }
}
