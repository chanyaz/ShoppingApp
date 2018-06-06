package com.tianyae.baselibrary.presenter

import android.content.Context
import com.kotlin.base.utils.NetWorkUtils
import com.tianyae.baselibrary.presenter.view.BaseView
import com.tianyae.baselibrary.rx.BaseException
import com.trello.rxlifecycle2.LifecycleProvider
import javax.inject.Inject

open class BasePresenter<T : BaseView> {

    lateinit var mView: T

    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>

    @Inject
    lateinit var context: Context

    fun checkNetWork():Boolean {

        if(NetWorkUtils.isNetWorkAvailable(context)) {
            return true
        }
        mView.onError(BaseException(3,"网络不可用"))
        return false
    }
}