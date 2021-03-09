package com.ermolaevio.search4meanings.ui

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ermolaevio.search4meanings.databinding.EmptyViewBinding

class EmptyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private val bi: EmptyViewBinding

    init {
        bi = EmptyViewBinding.inflate(LayoutInflater.from(context), this)
        orientation = VERTICAL
        gravity = Gravity.CENTER
    }
}