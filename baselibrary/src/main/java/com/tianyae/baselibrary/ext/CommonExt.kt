package com.tianyae.baselibrary.ext

import com.tianyae.baselibrary.rx.BaseObserver
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.execute(baseObserver: BaseObserver<T>) {
    this.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(baseObserver)
}