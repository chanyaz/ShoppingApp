package com.tianyae.goodscenter.presenter.view

import com.tianyae.baselibrary.presenter.view.BaseView
import com.tianyae.goodscenter.data.protocol.Goods

/*
    商品列表 视图回调
 */
interface GoodsListView : BaseView {
    //获取商品列表
    fun onGetGoodsListResult(result: MutableList<Goods>?)
}