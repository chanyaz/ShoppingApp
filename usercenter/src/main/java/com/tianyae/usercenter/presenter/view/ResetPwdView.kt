package com.tianyae.usercenter.presenter.view

import com.tianyae.baselibrary.presenter.view.BaseView
import com.tianyae.usercenter.data.protocol.UserInfo

interface ResetPwdView: BaseView {
    fun onResetPwdResult(result:String)
}