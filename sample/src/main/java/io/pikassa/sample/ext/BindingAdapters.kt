package io.pikassa.sample.ext

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import io.pikassa.sample.R
import io.pikassa.sample.entities.InvoiceStatus

/**
Created by Denis Chornyy on 14,Июль,2020
All rights received.
 */
@BindingAdapter("android:src")
fun setImagebyStatus(img: ImageView, status: InvoiceStatus?) {
    when (status) {
        InvoiceStatus.Payed -> img.setImageResource(R.drawable.ic_invoice_accepted)
        InvoiceStatus.Failed -> img.setImageResource(R.drawable.ic_invoice_cancelled)
        else -> img.setImageResource(android.R.color.transparent)
    }
}

@BindingAdapter("android:text")
fun setTextByStatus(tv: TextView, status: InvoiceStatus?) {
    when (status) {
        InvoiceStatus.Payed -> tv.setText(R.string.text_invoice_accepted)
        InvoiceStatus.Failed -> tv.setText(R.string.text_invoice_cancelled)
        else -> tv.setText(R.string.text_invoice_in_progress)
    }
}

@BindingAdapter("android:textColor")
fun setTextColorByStatus(tv: TextView, status: InvoiceStatus?) {
    when (status) {
        InvoiceStatus.Payed -> tv.setTextColor(
            ContextCompat.getColor(
                tv.context,
                R.color.statusAccepted
            )
        )
        InvoiceStatus.Failed -> tv.setTextColor(
            ContextCompat.getColor(
                tv.context,
                R.color.statusCancelled
            )
        )
        else -> tv.setTextColor(ContextCompat.getColor(tv.context, android.R.color.black))
    }
}