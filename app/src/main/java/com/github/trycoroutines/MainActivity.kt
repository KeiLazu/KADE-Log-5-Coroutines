package com.github.trycoroutines

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launch {
            delay (5000)
            println("Hai Coroutines :D")
        }
    }
}
