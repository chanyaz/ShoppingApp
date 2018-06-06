package com.tianyae.ordercenter.injection.component

import com.tianyae.baselibrary.injection.PerComponentScope
import com.tianyae.baselibrary.injection.component.ActivityComponent
import com.tianyae.ordercenter.injection.module.ShipAddressModule
import dagger.Component

/*
    收货人信息Component
 */
@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)], modules = [(ShipAddressModule::class)])
interface ShipAddressComponent {

}
