package com.tianyae.goodscenter.ui.activity

import android.os.Bundle

import com.tianyae.baselibrary.ui.activity.BaseActivity
import com.tianyae.goodscenter.R
import com.tianyae.goodscenter.ui.fragment.CartFragment


/*
    商品列表Activity
 */
class CartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_cart)
        (fragment as CartFragment).setBackVisible(true)
    }

}
