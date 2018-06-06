package com.tianyae.goodscenter.data.api

import com.tianyae.baselibrary.data.protocol.BaseResponse
import com.tianyae.goodscenter.data.protocol.AddCartRequest
import com.tianyae.goodscenter.data.protocol.CartGoods
import com.tianyae.goodscenter.data.protocol.DeleteCartRequest
import com.tianyae.goodscenter.data.protocol.SubmitCartRequest
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/*
    购物车 接口
 */
interface CartApi {
    /*
        添加商品到购物车
     */
    @POST("cart/add")
    fun addCart(@Body req: AddCartRequest): Observable<BaseResponse<Int>>

    /*
        获取购物车列表
     */
    @POST("cart/getList")
    fun getCartList(): Observable<BaseResponse<MutableList<CartGoods>?>>

    /*
        删除购物车商品
     */
    @POST("cart/delete")
    fun deleteCartList(@Body req: DeleteCartRequest): Observable<BaseResponse<String>>

    /*
        提交购物车商品
     */
    @POST("cart/submit")
    fun submitCart(@Body req: SubmitCartRequest): Observable<BaseResponse<Int>>

}