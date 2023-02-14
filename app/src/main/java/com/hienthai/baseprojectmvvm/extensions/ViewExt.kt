package com.hienthai.baseprojectmvvm.extensions

import android.os.SystemClock
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.hienthai.baseprojectmvvm.R

inline fun View.setSafeClickListener(interval: Int = 500, crossinline onSafeClick: (View) -> Unit) {
    setOnClickListener(object : View.OnClickListener {
        private var lastTimeClicked: Long = 0
        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastTimeClicked < interval) {
                return
            }
            lastTimeClicked = SystemClock.elapsedRealtime()
            onSafeClick(v)
        }
    })
}

fun TextView.setFocusChange(hasFocus: Boolean) {
    if (hasFocus) {
        setTextColor(ContextCompat.getColor(context, R.color.teal_200))
    } else {
        setTextColor(ContextCompat.getColor(context, R.color.teal_700))
    }
}