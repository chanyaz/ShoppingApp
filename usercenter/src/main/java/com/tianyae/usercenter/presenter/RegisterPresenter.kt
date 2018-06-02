package com.tianyae.usercenter.presenter

import com.tianyae.baselibrary.ext.execute
import com.tianyae.baselibrary.presenter.BasePresenter
import com.tianyae.baselibrary.rx.BaseObserver
import com.tianyae.usercenter.presenter.service.UserService
import com.tianyae.usercenter.presenter.view.RegisterView
import javax.inject.Inject
import javax.inject.Named

class RegisterPresenter @Inject constructor() : BasePresenter<RegisterView>() {

    @Inject
    @field:[Named("service")]
    lateinit var userService: UserService


      fun register(mobile: String, verifyCode: String, pwd: String) {
        /*
        业务逻辑
         */
        if(!checkNetWork()) {

            println("网络不可用")
            return
        }

        mView.showLoading()
        userService.register(mobile, verifyCode, pwd)
                .execute(object : BaseObserver<String>(mView) {
                    override fun onNext(t: String) {
                        mView.onRegisterResult(t)
                    }

                    override fun onComplete() {
                        mView.hideLoading()

                    }
                }, lifecycleProvider)
    }



}