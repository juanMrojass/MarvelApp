package com.manuelmarvelapp.ui.main.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visibleGone")
fun showOrHide(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}
