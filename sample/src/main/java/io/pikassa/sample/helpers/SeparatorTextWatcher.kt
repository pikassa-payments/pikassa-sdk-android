package io.pikassa.sample.helpers

import android.text.Editable
import android.text.TextWatcher

/**
Created by Denis Chornyy on 14,Июль,2020
All rights received.
 */

abstract class SeparatorTextWatcher(
    private val separator: Char,
    private val interval: Int
) : TextWatcher {

    private var dirty = false
    private var isDelete = false

    override fun afterTextChanged(editable: Editable?) {
        if (dirty) return

        dirty = true
        val text = editable.toString().handleSeparator()
        onAfterTextChanged(text)
        dirty = false
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // Empty
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        isDelete = before != 0
    }

    private fun String.handleSeparator(): String {
        val stringBuilder = StringBuilder(this)

        if (length > 0 && length.rem(interval + 1) == 0) {
            if (isDelete) {
                stringBuilder.deleteCharAt(length - 1)
            } else {
                stringBuilder.insert(length - 1, separator)
            }
        }

        return stringBuilder.toString()
    }

    /**
     * Subclasses must implement this method to get the formatted text.
     */
    abstract fun onAfterTextChanged(text: String)
}