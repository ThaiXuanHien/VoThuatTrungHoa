package com.hienthai.baseprojectmvvm.presentation.insets

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.hienthai.baseprojectmvvm.R

class InsetsConstraintLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {
    init {
        val array = context.obtainStyledAttributes(attrs, R.styleable.InsetsLayout)
        val type = array.getInt(R.styleable.InsetsLayout_insetsType, -1)
        val padding = array.getInt(R.styleable.InsetsLayout_insetsPadding, -1)
        if (type != -1 && padding != -1) {
            applySystemWindowInsetsPadding(
                applyLeft = padding and PADDING_LEFT == PADDING_LEFT,
                applyTop = padding and PADDING_TOP == PADDING_TOP,
                applyRight = padding and PADDING_RIGHT == PADDING_RIGHT,
                applyBottom = padding and PADDING_BOTTOM == PADDING_BOTTOM,
                type = type
            )
        }
        array.recycle()
    }
}