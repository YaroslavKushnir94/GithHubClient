package com.kushnir.githhubclient.view.base

import android.annotation.SuppressLint
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

@SuppressLint("Registered")
open class BaseActivity:AppCompatActivity() {

    fun displayMessage(message: String) {
         Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
    }
}