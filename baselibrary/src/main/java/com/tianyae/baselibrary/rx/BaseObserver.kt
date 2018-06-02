package com.tianyae.baselibrary.rx

import com.tianyae.baselibrary.presenter.view.BaseView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

open class BaseObserver<T>(private val baseView:BaseView) : Observer<T> {
    override fun onComplete() {
        baseView.hideLoading()
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
        baseView.hideLoading()
        if (e is BaseException) {
            baseView.onError(e.msg)
        }
    }

}