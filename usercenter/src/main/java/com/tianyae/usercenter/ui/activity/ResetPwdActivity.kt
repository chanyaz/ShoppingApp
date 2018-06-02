package com.tianyae.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.tianyae.baselibrary.ext.enable
import com.tianyae.baselibrary.ext.onClick
import com.tianyae.baselibrary.ui.activity.BaseMvpActivity
import com.tianyae.usercenter.R
import com.tianyae.usercenter.injection.component.DaggerUserComponent
import com.tianyae.usercenter.injection.module.UserModule
import com.tianyae.usercenter.presenter.ResetPwdPresenter
import com.tianyae.usercenter.presenter.view.ResetPwdView
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.*


/*
    忘记密码界面
*/

class ResetPwdActivity : BaseMvpActivity<ResetPwdPresenter>(), ResetPwdView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)
        initView()
    }


    /*
        初始化视图
     */
    private fun initView() {

        mConfirmBtn.enable(mPwdEt, { isBtnEnable() })
        mConfirmBtn.enable(mPwdConfirmEt, { isBtnEnable() })

        mConfirmBtn.onClick(this)
    }


    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.mConfirmBtn -> {
                if (mPwdEt.text.toString() != mPwdConfirmEt.text.toString()){
                    toast("密码不一致")
                    return@onClick
                }
                mPresenter.resetPwd(intent.getStringExtra("mobile"),mPwdEt.text.toString())
            }
            R.id.mNextBtn -> {
            }
        }
    }

    private fun isBtnEnable(): Boolean {
        return mPwdEt.text.isNullOrEmpty().not() &&
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }

    override fun onResetPwdResult(result: String) {
        toast(result)
        startActivity(intentFor<LoginActivity>().singleTop().clearTop())
    }

}