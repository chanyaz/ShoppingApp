package com.tianyae.goodscenter.presenter

import com.tianyae.baselibrary.ext.execute
import com.tianyae.baselibrary.presenter.BasePresenter
import com.tianyae.baselibrary.rx.BaseObserver
import com.tianyae.goodscenter.data.protocol.Category
import com.tianyae.goodscenter.presenter.view.CategoryView
import com.tianyae.goodscenter.service.CategoryService
import javax.inject.Inject

class CategoryPresenter @Inject constructor() : BasePresenter<CategoryView>() {

    @Inject
    lateinit var categoryService: CategoryService

    /*
        获取商品分类列表
     */
    fun getCategory(parentId: Int) {
        if (!checkNetWork()) {
            return
        }

        mView.showLoading()
        categoryService.getCategory(parentId).execute(object : BaseObserver<MutableList<Category>?>(mView) {
            override fun onNext(t: MutableList<Category>?) {
                mView.onGetCategoryResult(t)
            }

        }, lifecycleProvider)
    }



}