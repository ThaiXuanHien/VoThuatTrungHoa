package com.hienthai.baseprojectmvvm.extensions

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.os.SystemClock
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.hienthai.baseprojectmvvm.R
import java.util.*

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

fun View.hideKeyboard() {
    this.clearFocus()
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showKeyboard() {
    this.requestFocus()
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun Activity.hideKeyboard() {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}

fun Context.showAlertDialog(
    title: String? = null,
    msg: String = "",
    positiveButton: String = getString(R.string.yes),
    negativeButton: String = getString(R.string.no),
    onPositiveButtonClick: View.OnClickListener? = null,
    onNegativeButtonClick: View.OnClickListener? = null,
    isCanTouchOutside: Boolean = false,
    isCancelable: Boolean = true
) {
    val alertDialog: AlertDialog? = AlertDialog.Builder(this).create()
    alertDialog?.setTitle(title)
    alertDialog?.setMessage(msg)
    alertDialog?.setCanceledOnTouchOutside(isCanTouchOutside)
    alertDialog?.setCancelable(isCancelable)
    alertDialog?.setButton(
        AlertDialog.BUTTON_POSITIVE, positiveButton
    ) { dialog, _ ->
        run {
            dialog.dismiss()
            onPositiveButtonClick?.onClick(null)
        }
    }
    onNegativeButtonClick?.let {
        alertDialog?.setButton(
            AlertDialog.BUTTON_NEGATIVE, negativeButton
        ) { dialog, _ ->
            run {
                dialog.dismiss()
                it.onClick(null)
            }
        }
    }
    alertDialog?.show()
}

fun Context.pickDate(
    currentTime: Calendar?,
    minDate: Calendar? = null,
    maxDate: Calendar? = null,
    onPick: (calendar: Calendar) -> Unit
) {
    val datePicker =
        currentTime?.let { it ->
            DatePickerDialog(this, { _, year, month, dayOfMonth ->
                onPick.invoke(Calendar.getInstance().apply {
                    this.set(Calendar.YEAR, year)
                    this.set(Calendar.MONTH, month)
                    this.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                })
            }, it.get(Calendar.YEAR), it.get(Calendar.MONTH), it.get(Calendar.DAY_OF_MONTH))
                .apply {
                    minDate?.let { datePicker.minDate = it.timeInMillis }
                    maxDate?.let { datePicker.maxDate = it.timeInMillis }
                }
        }
    datePicker?.show()
}

fun Context.pickTime(context: Context?, defaultTime: Date? = null, listener: (Int, Int) -> Unit) {
    val calendar = Calendar.getInstance()
    defaultTime?.let { calendar.time = it }

    val defaultHour = calendar.get(Calendar.HOUR_OF_DAY)
    val defaultMinute = calendar.get(Calendar.MINUTE)

    context?.let {
        TimePickerDialog(it, { _, hourOfDay, minute ->
            listener(hourOfDay, minute)
        }, defaultHour, defaultMinute, true).show()
    }
}

fun Context.pickDateTimeClear(
    currentTime: Calendar?,
    minDate: Calendar? = null,
    maxDate: Calendar? = null,
    onPick: (calendar: Calendar) -> Unit,
    onClear: () -> Unit
) {
    val datePicker =
        currentTime?.let { it ->
            DatePickerDialog(this, { _, year, month, dayOfMonth ->
                onPick.invoke(Calendar.getInstance().apply {
                    this.set(Calendar.YEAR, year)
                    this.set(Calendar.MONTH, month)
                    this.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                })
            }, it.get(Calendar.YEAR), it.get(Calendar.MONTH), it.get(Calendar.DAY_OF_MONTH))
                .apply {
                    minDate?.let { datePicker.minDate = it.timeInMillis }
                    maxDate?.let { datePicker.maxDate = it.timeInMillis }
                }
        }

    datePicker?.setButton(
        DialogInterface.BUTTON_NEUTRAL,
        getString(R.string.clear_calendar)
    ) { _, _ ->
        onClear.invoke()
    }

    datePicker?.show()
}

fun EditText.validate(validator: (String) -> Boolean, messageError: String) {
    this.doAfterTextChanged {
        this.error = if (validator(it.toString())) null else messageError
    }
}
// use: etUser.validate({ s -> s.length >= 6 }, "Minimum length = 6")
fun String.isValidLengthString(length: Int): Boolean  = this.length >= length

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()