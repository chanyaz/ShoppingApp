package com.tianyae.usercenter.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.tianyae.baselibrary.ui.activity.BaseMvpActivity
import com.tianyae.usercenter.R
import com.tianyae.usercenter.R.id.mRegisterBtn
import com.tianyae.usercenter.presenter.RegisterPresenter
import com.tianyae.usercenter.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {
    override fun onRegisterResult(result: Boolean) {
        toast("注册成功")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mPresenter = RegisterPresenter()

        mPresenter.mView = this

        mRegisterBtn.setOnClickListener({
            mPresenter.register("", "","")
        })
    }
}
