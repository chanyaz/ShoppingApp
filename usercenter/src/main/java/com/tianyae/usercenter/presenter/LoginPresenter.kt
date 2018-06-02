package com.tianyae.usercenter.presenter

import com.tianyae.baselibrary.data.protocol.BaseResponse
import com.tianyae.baselibrary.ext.execute
import com.tianyae.baselibrary.presenter.BasePresenter
import com.tianyae.baselibrary.rx.BaseObserver
import com.tianyae.usercenter.data.protocol.UserInfo
import com.tianyae.usercenter.presenter.service.UserService
import com.tianyae.usercenter.presenter.view.LoginView
import com.tianyae.usercenter.presenter.view.RegisterView
import javax.inject.Inject
import javax.inject.Named

class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {

    @Inject
    @field:[Named("service")]
    lateinit var userService: UserService


    fun login(mobile: String, pushId: String, pwd: String) {
        /*
        业务逻辑
         */
        if (!checkNetWork()) {

            println("网络不可用")
            return
        }

        mView.showLoading()
        userService.login(mobile, pushId, pwd)
                .execute(object : BaseObserver<String>(mView) {
                    override fun onNext(t: String) {
                        mView.onLoginResult(t)
                    }

                    override fun onComplete() {
                        mView.hideLoading()

                    }
                }, lifecycleProvider)
    }


}