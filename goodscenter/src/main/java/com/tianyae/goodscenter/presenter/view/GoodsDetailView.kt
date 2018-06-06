package com.tianyae.goodscenter.presenter.view

import com.tianyae.baselibrary.presenter.view.BaseView
import com.tianyae.goodscenter.data.protocol.Goods

/*
    商品详情 视图回调
 */
interface GoodsDetailView : BaseView {

    //获取商品详情
    fun onGetGoodsDetailResult(result: Goods)
    //加入购物车
    fun onAddCartResult(result: Int)
}