package com.tianyae.baselibrary.ui.activity

import android.os.Bundle
import com.tianyae.baselibrary.common.BaseApplication
import com.tianyae.baselibrary.injection.component.ActivityComponent
import com.tianyae.baselibrary.injection.component.DaggerActivityComponent
import com.tianyae.baselibrary.injection.module.ActivityModule
import com.tianyae.baselibrary.injection.module.LifecycleProviderModule
import com.tianyae.baselibrary.presenter.BasePresenter
import com.tianyae.baselibrary.presenter.view.BaseView
import com.tianyae.baselibrary.widgets.ProgressLoading
import org.jetbrains.anko.toast
import javax.inject.Inject

abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    @Inject
    lateinit var mPresenter: T

    lateinit var mActivityComponent: ActivityComponent

    private lateinit var mLoadingDialog: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        injectComponent()
        mLoadingDialog = ProgressLoading.create(this)
    }

    abstract fun injectComponent()


    private fun initActivityInjection() {
        mActivityComponent = DaggerActivityComponent.builder().appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }

    override fun showLoading() {
        mLoadingDialog.showLoading()
    }

    override fun hideLoading() {
        mLoadingDialog.hideLoading()
    }

    override fun onError(text: String) {
        toast(text)
    }


}