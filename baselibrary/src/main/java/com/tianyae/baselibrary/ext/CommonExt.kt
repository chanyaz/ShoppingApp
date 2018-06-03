package com.tianyae.baselibrary.ext

import android.Manifest
import android.app.Activity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.tbruyelle.rxpermissions2.RxPermissions
import com.tianyae.baselibrary.data.protocol.BaseResponse
import com.tianyae.baselibrary.rx.BaseFunc
import com.tianyae.baselibrary.rx.BaseFuncString
import com.tianyae.baselibrary.rx.BaseObserver
import com.tianyae.baselibrary.utils.DefaultTextWatcher
import com.tianyae.baselibrary.utils.GlideUtils
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

fun <T> Observable<BaseResponse<T>>.convertString(): Observable<String> {
    return this.flatMap(BaseFuncString())
}

fun <T> Observable<BaseResponse<T>>.convert(): Observable<T> {
    return this.flatMap(BaseFunc())
}


fun View.onClick(listener: View.OnClickListener) {
    this.setOnClickListener(listener)
}

fun View.onClick(method: () -> Unit): View {
    setOnClickListener { method() }
    return this
}


fun Button.enable(et: EditText, method: () -> Boolean) {
    val btn = this

    et.addTextChangedListener(object : DefaultTextWatcher() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled = method()
        }
    })
}

fun requestRxPermissions(activity: Activity) {
    val rxPermission = RxPermissions(activity)
    rxPermission
            .requestEach(Manifest.permission.CAMERA)
            .subscribe({ // will emit 2 Permission objects
                permission ->
                when {
                    permission.granted -> {
                        // `permission.name` is granted !
                    }
                    permission.shouldShowRequestPermissionRationale -> {
                        // Denied permission without ask never again
                    }
                    else -> {
                        // Denied permission with ask never again
                        // Need to go to the settings
                    }
                }
            })
}

/*
    ImageView加载网络图片
 */
fun ImageView.loadUrl(url: String) {
    GlideUtils.loadUrlImage(context, url, this)
}


