package com.tianyae.baselibrary.ext

import android.view.View
import com.tianyae.baselibrary.data.protocol.BaseResponse
import com.tianyae.baselibrary.rx.BaseFuncString
import com.tianyae.baselibrary.rx.BaseObserver
import com.trello.rxlifecycle2.LifecycleProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.execute(baseObserver: BaseObserver<T>, lifecycleProvider: LifecycleProvider<*>) {
    this.observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribeOn(Schedulers.io())
            .subscribe(baseObserver)
}

fun <T> Observable<BaseResponse<T>>.convert(): Observable<String> {
    return this.flatMap(BaseFuncString())
}


fun View.onClick(listener: View.OnClickListener) {
    this.setOnClickListener(listener)
}

fun View.onClick(method: () -> Unit): View {
    setOnClickListener { method() }
    return this
}