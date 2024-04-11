package com.hienthai.vohoctrunghoa.presentation.customview

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.WindowInsetsCompat
import com.hienthai.vohoctrunghoa.extensions.hideKeyboard

class KeyboardAvoidingLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val gestureDetector: GestureDetectorCompat

    init {
        gestureDetector = GestureDetectorCompat(context, object : SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                checkHideKeyboard(e)
                return super.onSingleTapUp(e)
            }
        })
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(ev)
        return super.dispatchTouchEvent(ev)
    }

    private fun checkHideKeyboard(e: MotionEvent) {
        val isShowKeyboard =
            WindowInsetsCompat.toWindowInsetsCompat(rootWindowInsets).isVisible(WindowInsetsCompat.Type.ime())
        if (isShowKeyboard.not()) return
        val parentLocation = IntArray(2)
        getLocationInWindow(parentLocation)
        val editText = findEditTextFocus(this, e.x + parentLocation[0], e.y + parentLocation[1])
        if (editText == null) {
            hideKeyboard()
        }
    }

    private fun findEditTextFocus(view: View, x: Float, y: Float): EditText? {
        if (view.isShown && view.isViewInBounds(x, y)) {
            if (view is ViewGroup) {
                for (i in 0 until view.childCount) {
                    val v = findEditTextFocus(view.getChildAt(i), x, y)
                    if (v is EditText) {
                        return v
                    }
                }
            } else if (view is EditText) {
                return view
            }
        }
        return null
    }

    private val location = IntArray(2)
    private fun View.isViewInBounds(x: Float, y: Float): Boolean {
        getLocationInWindow(location)
        return x >= location[0] && x <= location[0] + width
                && y >= location[1] && y <= location[1] + height
    }
}