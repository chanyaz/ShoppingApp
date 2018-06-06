package com.tianyae.goodscenter.injection.component

import com.tianyae.baselibrary.injection.PerComponentScope
import com.tianyae.baselibrary.injection.component.ActivityComponent
import com.tianyae.goodscenter.injection.module.CategoryModule
import com.tianyae.goodscenter.ui.fragment.CategoryFragment
import dagger.Component


@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)], modules = [(CategoryModule::class)])
interface CategoryComponent {
    fun inject(fragment: CategoryFragment)
}