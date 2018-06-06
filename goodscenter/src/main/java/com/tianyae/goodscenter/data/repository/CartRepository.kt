package com.tianyae.goodscenter.data.repository

import com.tianyae.baselibrary.data.net.RetrofitFactory
import com.tianyae.baselibrary.data.protocol.BaseResponse
import com.tianyae.goodscenter.data.api.CartApi
import com.tianyae.goodscenter.data.protocol.*
import io.reactivex.Observable
import javax.inject.Inject

/*
    购物车数据层
 */
class CartRepository @Inject constructor() {

    /*
        添加商品到购物车
     */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String): Observable<BaseResponse<Int>> {
        return RetrofitFactory.instance.create(CartApi::class.java)
                .addCart(AddCartRequest(goodsId, goodsDesc, goodsIcon, goodsPrice, goodsCount, goodsSku))
    }

    /*
        获取购物车列表
     */
    fun getCartList(): Observable<BaseResponse<MutableList<CartGoods>?>> {
        return RetrofitFactory.instance.create(CartApi::class.java).getCartList()
    }

    /*
            删除购物车商品
         */
    fun deleteCartList(list: List<Int>): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(CartApi::class.java).deleteCartList(DeleteCartRequest(list))
    }

    /*
        购物车结算
     */
    fun submitCart(list: MutableList<CartGoods>, totalPrice: Long): Observable<BaseResponse<Int>> {
        return RetrofitFactory.instance.create(CartApi::class.java).submitCart(SubmitCartRequest(list, totalPrice))
    }

}
