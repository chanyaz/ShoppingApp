package com.tianyae.baselibrary.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tianyae.baselibrary.injection.component.ActivityComponent
import com.tianyae.baselibrary.presenter.BasePresenter
import com.tianyae.baselibrary.presenter.view.BaseView
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

open abstract class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {

    lateinit var mActivityComponent: ActivityComponent


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        initActivityInjection()
        injectComponent()

        return super.onCreateView(inflater, container, savedInstanceState)

    }

    abstract fun injectComponent()


    private fun initActivityInjection() {
//        mActivityComponent = DaggerActivityComponent.builder().appComponent((activity.application as BaseApplication).appComponent)
//                .activityModule(ActivityModule(activity))
//                .lifecycleProviderModule(LifecycleProviderModule(this))
//                .build()
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError(text: String) {
        toast(text)
    }

    @Inject
    lateinit var mPresenter: T

}