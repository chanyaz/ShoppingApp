package com.tianyae.goodscenter.injection.component

import com.tianyae.baselibrary.injection.PerComponentScope
import com.tianyae.baselibrary.injection.component.ActivityComponent
import com.tianyae.goodscenter.injection.module.CartModule
import com.tianyae.goodscenter.ui.fragment.CartFragment
import dagger.Component


@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)], modules = [(CartModule::class)])
interface CartComponent {
    fun inject(fragment: CartFragment)
}