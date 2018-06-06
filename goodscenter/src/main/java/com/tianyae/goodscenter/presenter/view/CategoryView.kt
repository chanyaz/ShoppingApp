package com.tianyae.goodscenter.presenter.view

import com.tianyae.baselibrary.presenter.view.BaseView
import com.tianyae.goodscenter.data.protocol.Category

interface CategoryView : BaseView {

    fun onGetCategoryResult(result: MutableList<Category>?)

}