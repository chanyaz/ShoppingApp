package com.tianyae.goodscenter.ext

import android.app.Activity
import android.widget.EditText
import com.tianyae.goodscenter.R
import org.jetbrains.anko.find
import ren.qinc.numberbutton.NumberButton

/*
    三方控件扩展
 */
fun NumberButton.getEditText(): EditText {
    return find(R.id.text_count)
}

fun Activity.setBackgroundAlpha(bgAlpha:Float) {
    val lp = this.window.attributes
    lp.alpha = bgAlpha
    this.window.attributes = lp
}