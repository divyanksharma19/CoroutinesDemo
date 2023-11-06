package com.divyank.coroutinesdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.helper.widget.MotionEffect.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class withcontextandrunblocking : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_withcontextandrunblocking)
        CoroutineScope(Dispatchers.Main).launch {
            executetask()
        }
    }
    suspend fun executetask(){
        Log.d(TAG,"before")
//        GlobalScope.launch{/// block ni karega aage ka code execute hojayega ...launch coroutine builder non blocking nature ka hota hai
//            delay(1000)
//            Log.d(TAG,"Inside")
//        }

        withContext(Dispatchers.IO){ //block kardega aage k code --coroutine suspend kardega jabtak pura complete ni hojata
            delay(1000)
            Log.d(TAG,"Inside")
        }
        Log.d(TAG,"outside")
    }
}