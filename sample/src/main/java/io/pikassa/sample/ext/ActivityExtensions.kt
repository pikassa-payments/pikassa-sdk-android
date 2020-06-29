package io.pikassa.sample.ext

import android.app.Activity
import android.widget.Toast

/**
Created by Denis Chornyy on 29,Июнь,2020
All rights received.
 */

fun Activity.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity.longToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}