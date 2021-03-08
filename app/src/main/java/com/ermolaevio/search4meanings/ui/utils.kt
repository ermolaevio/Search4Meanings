package com.ermolaevio.search4meanings.ui

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.LayoutRes

fun EditText.addOnTextChanged(
    after: ((s: Editable) -> Unit)? = null,
    before: ((s: CharSequence?, start: Int, count: Int, after: Int) -> Unit)? = null,
    onText: ((s: CharSequence?, start: Int, before: Int, count: Int) -> Unit)? = null
) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable) {
            after?.invoke(s)
        }

        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) {
            before?.invoke(s, start, count, after)
        }

        override fun onTextChanged(
            s: CharSequence?,
            start: Int,
            before: Int,
            count: Int
        ) {
            onText?.invoke(s, start, before, count)
        }
    })
}

fun EditText.afterTextChanged(after: (s: Editable) -> Unit) {
    addOnTextChanged(after = after)
}