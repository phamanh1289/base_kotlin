package com.appcyclone.basekotlin.ui.seminar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.appcyclone.basekotlin.R
import kotlinx.android.synthetic.main.activity_seminar.*

class SeminarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seminar)
        init()
    }

    private fun init() {
        tvLocalFun.text = localFunction("Kotlin")
    }

    //Local Function
    private fun localFunction(a: String): String {
        fun childFun(): String {
            return "Hello $a"
        }
        return childFun()
    }

    //let

    //apply

    //with

    //lazy

    //infix

    //inline

    //Operator
}
