package com.tianyae.ordercenter.injection.component

import com.tianyae.baselibrary.injection.PerComponentScope
import com.tianyae.baselibrary.injection.component.ActivityComponent
import com.tianyae.ordercenter.injection.module.OrderModule
import dagger.Component

/*
    订单Component
 */
@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)],modules = [(OrderModule::class)])
interface OrderComponent {
}
