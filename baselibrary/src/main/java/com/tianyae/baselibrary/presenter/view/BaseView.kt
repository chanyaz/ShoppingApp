package com.tianyae.baselibrary.presenter.view

import com.tianyae.baselibrary.rx.BaseException

interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(exception: BaseException)
}