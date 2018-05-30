package com.tianyae.baselibrary.presenter

import com.tianyae.baselibrary.presenter.view.BaseView

open class BasePresenter<T : BaseView> {
    lateinit var mView: T
}