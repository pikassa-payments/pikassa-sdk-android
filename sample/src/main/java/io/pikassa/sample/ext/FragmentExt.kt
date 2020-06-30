package io.pikassa.sample.ext

import androidx.fragment.app.Fragment

/**
Created by Denis Chornyy on 30,Июнь,2020
All rights received.
 */

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}