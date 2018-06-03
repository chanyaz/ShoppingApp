package com.tianyae.usercenter.presenter

import com.tianyae.baselibrary.ext.execute
import com.tianyae.baselibrary.presenter.BasePresenter
import com.tianyae.baselibrary.rx.BaseObserver
import com.tianyae.usercenter.presenter.service.UploadService
import com.tianyae.usercenter.presenter.view.UserInfoView
import javax.inject.Inject

class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {

    @Inject
    private
    lateinit var uploadService: UploadService

    fun getUploadToken() {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        uploadService.getUploadToken().execute(object : BaseObserver<String>(mView) {
            override fun onNext(t: String) {
                mView.onGetUploadTokenResult(t)
            }
        }, lifecycleProvider)
    }

}