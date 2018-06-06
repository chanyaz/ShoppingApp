package com.tianyae.goodscenter.data.api

import com.tianyae.baselibrary.data.protocol.BaseResponse
import com.tianyae.goodscenter.data.protocol.GetGoodsDetailRequest
import com.tianyae.goodscenter.data.protocol.GetGoodsListByKeywordRequest
import com.tianyae.goodscenter.data.protocol.GetGoodsListRequest
import com.tianyae.goodscenter.data.protocol.Goods
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/*
    商品接口
 */
interface GoodsApi {
    /*
       获取商品列表
    */
    @POST("goods/getGoodsList")
    fun getGoodsList(@Body req: GetGoodsListRequest): Observable<BaseResponse<MutableList<Goods>?>>

    /*
        获取商品列表
     */
    @POST("goods/getGoodsListByKeyword")
    fun getGoodsListByKeyword(@Body req: GetGoodsListByKeywordRequest): Observable<BaseResponse<MutableList<Goods>?>>

    /*
        获取商品详情
     */
    @POST("goods/getGoodsDetail")
    fun getGoodsDetail(@Body request: GetGoodsDetailRequest): Observable<BaseResponse<Goods>>

}