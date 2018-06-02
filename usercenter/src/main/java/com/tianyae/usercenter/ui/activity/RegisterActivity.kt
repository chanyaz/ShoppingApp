package com.tianyae.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.tianyae.baselibrary.common.AppManager
import com.tianyae.baselibrary.ext.enable
import com.tianyae.baselibrary.ext.onClick
import com.tianyae.baselibrary.ui.activity.BaseMvpActivity
import com.tianyae.usercenter.R
import com.tianyae.usercenter.injection.component.DaggerUserComponent
import com.tianyae.usercenter.injection.module.UserModule
import com.tianyae.usercenter.presenter.RegisterPresenter
import com.tianyae.usercenter.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast


    /*
        注册界面
    */

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView, View.OnClickListener {

    private var pressTime: Long = 0

    override fun onRegisterResult(result: String) {
        toast(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initView()


    }


    /*
        初始化视图
     */
    private fun initView() {

        mRegisterBtn.enable(mMobileEt, { isBtnEnable() })
        mRegisterBtn.enable(mVerifyCodeEt, { isBtnEnable() })
        mRegisterBtn.enable(mPwdEt, { isBtnEnable() })
        mRegisterBtn.enable(mPwdConfirmEt, { isBtnEnable() })


        mRegisterBtn.onClick(this)
        mVerifyCodeBtn.onClick(this)
    }


    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onBackPressed() {

        val time = System.currentTimeMillis()

        if (time - pressTime > 2000) {
            toast("再按一次退出程序")
            pressTime = time
        } else {
            AppManager.instance.exitApp(this)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送验证码成功")
            }
            R.id.mRegisterBtn -> {
                mPresenter.register(mMobileEt.text.toString(), mVerifyCodeEt.text.toString(), mPwdEt.text.toString())
            }
        }
    }

    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mVerifyCodeEt.text.isNullOrEmpty().not() &&
                mPwdEt.text.isNullOrEmpty().not() &&
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }

}