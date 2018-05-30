package com.tianyae.baselibrary.ui.activity

import com.tianyae.baselibrary.presenter.BasePresenter
import com.tianyae.baselibrary.presenter.view.BaseView

open class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {
    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    lateinit var mPresenter: T
}