package com.tianyae.usercenter.presenter

import com.tianyae.baselibrary.ext.execute
import com.tianyae.baselibrary.presenter.BasePresenter
import com.tianyae.baselibrary.rx.BaseObserver
import com.tianyae.usercenter.presenter.service.UserService
import com.tianyae.usercenter.presenter.view.ForgetPwdView
import com.tianyae.usercenter.presenter.view.RegisterView
import com.tianyae.usercenter.presenter.view.ResetPwdView
import javax.inject.Inject
import javax.inject.Named

class ResetPwdPresenter @Inject constructor() : BasePresenter<ResetPwdView>() {

    @Inject
    lateinit var userService: UserService


      fun resetPwd(mobile: String, pwd: String) {
        /*
        业务逻辑
         */
        if(!checkNetWork()) {

            println("网络不可用")
            return
        }

        mView.showLoading()
        userService.resetPwd(mobile, pwd)
                .execute(object : BaseObserver<String>(mView) {
                    override fun onNext(t: String) {
                        mView.onResetPwdResult(t)
                    }

                    override fun onComplete() {
                        mView.hideLoading()

                    }
                }, lifecycleProvider)
    }



}