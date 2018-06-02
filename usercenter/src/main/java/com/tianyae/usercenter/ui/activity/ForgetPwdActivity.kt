package com.tianyae.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.tianyae.baselibrary.ext.enable
import com.tianyae.baselibrary.ext.onClick
import com.tianyae.baselibrary.ui.activity.BaseMvpActivity
import com.tianyae.usercenter.R
import com.tianyae.usercenter.injection.component.DaggerUserComponent
import com.tianyae.usercenter.injection.module.UserModule
import com.tianyae.usercenter.presenter.ForgetPwdPresenter
import com.tianyae.usercenter.presenter.view.ForgetPwdView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


/*
    忘记密码界面
*/

class ForgetPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(), ForgetPwdView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)
        initView()
    }


    /*
        初始化视图
     */
    private fun initView() {

        mNextBtn.enable(mMobileEt, { isBtnEnable() })
        mNextBtn.enable(mVerifyCodeEt, { isBtnEnable() })

        mVerifyCodeBtn.onClick(this)
        mNextBtn.onClick(this)
    }


    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送验证码成功")
            }
            R.id.mNextBtn -> {
                mPresenter.forgetPwd(mMobileEt.text.toString(), mVerifyCodeEt.text.toString())
            }
        }
    }

    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mVerifyCodeEt.text.isNullOrEmpty().not()
    }

    override fun onForgetPwdResult(result: String) {
        toast(result)
        startActivity<ResetPwdActivity>("mobile"  to mMobileEt.text.toString())
    }



}