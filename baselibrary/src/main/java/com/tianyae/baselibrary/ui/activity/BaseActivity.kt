package com.tianyae.baselibrary.ui.activity

import android.os.Bundle
import com.tianyae.baselibrary.common.AppManager
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

open class BaseActivity: RxAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instance.addActivity(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }
}