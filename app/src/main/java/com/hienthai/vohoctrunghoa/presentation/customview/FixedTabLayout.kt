package com.hienthai.vohoctrunghoa.presentation.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.view.updateLayoutParams
import com.google.android.material.tabs.TabLayout

class FixedTabLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : TabLayout(context, attrs) {

    override fun addTab(tab: Tab, position: Int, setSelected: Boolean) {
        super.addTab(tab, position, setSelected)
        tab.view.updateLayoutParams<LinearLayout.LayoutParams> {
            width = LinearLayout.LayoutParams.MATCH_PARENT
        }
    }
}