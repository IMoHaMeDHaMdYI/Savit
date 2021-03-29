package com.savit.core.extension

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.use

@ColorInt
fun Context.getThemeColor(@AttrRes themeAttrId: Int): Int {
    return obtainStyledAttributes(intArrayOf(themeAttrId)).use {
        it.getColor(0, Color.MAGENTA)
    }
}

@ColorInt
fun Context.getColorCompact(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}

fun Context.toast(@StringRes textRes: Int) {
    Toast.makeText(this, getString(textRes), Toast.LENGTH_SHORT).show()
}

fun Context.showKeyboard(editText: EditText) {
    val inputMethodManager: InputMethodManager =
        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
}
