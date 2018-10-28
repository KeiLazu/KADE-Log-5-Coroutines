package com.github.trycoroutines

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // basic launch
        launch {
            longOp()
        }

        val income = async { amountOfIncome() }
        val capital = async { amountOfCapital() }

        /**
         * This one below is for canceling all async
         * This will clearly cancel all async
         *
         * For trying the isActive, try to uncomment all of this one by one from bottom
         * i try this from above
         */
//        if (income.isActive && capital.isActive) {
//            income.cancel()
//            capital.cancel()
//            println("Cancelled before async")
//        } // worked in 1st experiment (commented after 1st experiment)

        if (income.isCompleted && capital.isCompleted) {
            println("Clear Before Async!")
        }

        async {
            if (income.isCompleted && capital.isCompleted) {
                println("Is it clear before the await?")
            }

//            if (income.isActive && capital.isActive) {
//                income.cancel()
//                capital.cancel()
//                println("Cancelled before the await?")
//            } // worked in 2nd experiment (commented out after 2nd experiment)

            println("Your profit is ${income.await() - capital.await()} ")

            if (income.isCompleted && capital.isCompleted) {
                println("Clear!")
            } // somehow only this one works for isCompleted

//            if (income.isActive && capital.isActive) {
//                income.cancel()
//                capital.cancel()
//                println("Cancelled after the await")
//            } // not working, it's obvious isn't it?
        }

        if (income.isCompleted && capital.isCompleted) {
            println("Clear After Async!")
        }

//        if (income.isActive && capital.isActive) {
//            income.cancel()
//            capital.cancel()
//            println("Cancelled outside async after await")
//        } // somehow this worked in 2nd experiment after the cancelled before the await
        // extra notes: this worked in 3rd experiment when all of the above has been commented out
        // commented out after 3rd experiment

        /**
         * PS: you can clearly see how isActive and isCompleted works after running those
         */
    }

    private suspend fun longOp() {
        delay(2500)
        println("From Long Op Function")
    } // try this with launch{} instead

    private suspend fun amountOfCapital(): Int {
        delay(2500) // shortening the delay
        return 1000000
    }

    private suspend fun amountOfIncome(): Int {
        delay(2500) // shortening the delay
        return 1200000
    }

}
