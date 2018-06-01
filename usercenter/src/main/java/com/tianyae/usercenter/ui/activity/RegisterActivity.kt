package com.tianyae.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.tianyae.baselibrary.common.AppManager
import com.tianyae.baselibrary.ext.onClick
import com.tianyae.baselibrary.ui.activity.BaseMvpActivity
import com.tianyae.usercenter.R
import com.tianyae.usercenter.injection.component.DaggerUserComponent
import com.tianyae.usercenter.injection.module.UserModule
import com.tianyae.usercenter.presenter.RegisterPresenter
import com.tianyae.usercenter.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {

    private var pressTime : Long = 0

    override fun onRegisterResult(result: String) {
        toast(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mRegisterBtn.onClick {

            mPresenter.register(mMobileEt.text.toString(), mVerifyEt.text.toString(), mPwdEt.text.toString())

        }
        mGetVerifyCodeBtn.setOnClickListener({
            //            mPresenter.register2(mMobileEt.text.toString(), mVerifyEt.text.toString(), mPwdEt.text.toString())

        })
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

}