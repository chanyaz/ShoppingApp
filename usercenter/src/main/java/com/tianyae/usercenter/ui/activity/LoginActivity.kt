package com.tianyae.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.kotlin.user.utils.UserPrefsUtils
import com.tianyae.baselibrary.ext.enable
import com.tianyae.baselibrary.ext.onClick
import com.tianyae.baselibrary.ui.activity.BaseMvpActivity
import com.tianyae.usercenter.R
import com.tianyae.usercenter.data.protocol.UserInfo
import com.tianyae.usercenter.injection.component.DaggerUserComponent
import com.tianyae.usercenter.injection.module.UserModule
import com.tianyae.usercenter.presenter.LoginPresenter
import com.tianyae.usercenter.presenter.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


/*
    登陆界面
*/

class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }


    /*
        初始化视图
     */
    private fun initView() {

        mLoginBtn.enable(mMobileEt, { isBtnEnable() })
        mLoginBtn.enable(mPwdEt, { isBtnEnable() })

        mLoginBtn.onClick(this)
        mForgetPwdTv.onClick(this)

        mHeaderBar.getRightView().onClick(this)
    }


    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.mLoginBtn -> {
                mPresenter.login(mMobileEt.text.toString(), "", mPwdEt.text.toString())
            }
            R.id.mRightTv -> {
                startActivity<RegisterActivity>()
            }
            R.id.mForgetPwdTv -> {
                startActivity<ForgetPwdActivity>()
            }
        }
    }

    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mPwdEt.text.isNullOrEmpty().not()
    }


    override fun onLoginResult(result: UserInfo) {
        toast("登陆成功")
        UserPrefsUtils.putUserInfo(result)

        startActivity<UserInfoActivity>()

    }


}