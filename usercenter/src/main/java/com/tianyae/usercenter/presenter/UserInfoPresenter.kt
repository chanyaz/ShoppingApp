package com.tianyae.usercenter.presenter

import com.tianyae.baselibrary.ext.execute
import com.tianyae.baselibrary.presenter.BasePresenter
import com.tianyae.baselibrary.rx.BaseObserver
import com.tianyae.usercenter.data.protocol.UserInfo
import com.tianyae.usercenter.presenter.service.UploadService
import com.tianyae.usercenter.presenter.service.UserService
import com.tianyae.usercenter.presenter.view.UserInfoView
import javax.inject.Inject

class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {

    @Inject
    lateinit var userService: UserService

    @Inject
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

    fun editUser(userIcon: String, userName: String, userGender: String, userSign: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        userService.editUser(userIcon, userName, userGender, userSign).execute(object : BaseObserver<UserInfo>(mView) {

            override fun onNext(t: UserInfo) {
                mView.onEditUserResult(t)
            }
        }, lifecycleProvider)

    }

}