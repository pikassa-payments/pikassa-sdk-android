package io.pikassa.sample.ext

import android.app.Activity
import android.view.View
import android.widget.Toast

/**
Created by pikassa, support@pikassa.io on 29,Июнь,2020
All rights received.
 */

fun Activity.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity.longToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}
