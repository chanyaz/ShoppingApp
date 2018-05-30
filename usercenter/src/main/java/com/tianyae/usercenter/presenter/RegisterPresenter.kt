package com.tianyae.usercenter.presenter

import com.tianyae.baselibrary.ext.execute
import com.tianyae.baselibrary.presenter.BasePresenter
import com.tianyae.baselibrary.rx.BaseObserver
import com.tianyae.usercenter.presenter.service.impl.UserServiceImpl
import com.tianyae.usercenter.presenter.view.RegisterView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RegisterPresenter : BasePresenter<RegisterView>() {

    fun register(mobile: String, verifyCode: String, pwd: String) {
        /*
        业务逻辑
         */
        val userService = UserServiceImpl()


        userService.register(mobile, verifyCode, pwd)
                .execute(object :BaseObserver<Boolean>() {
                    override fun onNext(t: Boolean) {
                        mView.onRegisterResult(t)
                    }
                })

    }

}