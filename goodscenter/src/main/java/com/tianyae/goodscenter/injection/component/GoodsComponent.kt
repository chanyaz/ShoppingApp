package com.tianyae.goodscenter.injection.component

import com.tianyae.baselibrary.injection.PerComponentScope
import com.tianyae.baselibrary.injection.component.ActivityComponent
import com.tianyae.goodscenter.injection.module.CartModule
import com.tianyae.goodscenter.injection.module.CategoryModule
import com.tianyae.goodscenter.injection.module.GoodsModule
import com.tianyae.goodscenter.ui.activity.GoodsActivity
import com.tianyae.goodscenter.ui.fragment.CategoryFragment
import com.tianyae.goodscenter.ui.fragment.GoodsDetailTabOneFragment
import dagger.Component


@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)], modules = [(GoodsModule::class), (CartModule::class)])
interface GoodsComponent {
    fun inject(activity: GoodsActivity)
    fun inject(fragment: GoodsDetailTabOneFragment)
}