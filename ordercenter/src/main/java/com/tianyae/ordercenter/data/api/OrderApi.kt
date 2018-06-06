package com.tianyae.ordercenter.data.api

import com.tianyae.baselibrary.data.protocol.BaseResponse
import com.tianyae.ordercenter.data.protocol.*
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/*
    订单 接口
 */
interface OrderApi {
    /*
        取消订单
     */
    @POST("order/cancel")
    fun cancelOrder(@Body req: CancelOrderRequest): Observable<BaseResponse<String>>

    /*
        确认订单
     */
    @POST("order/confirm")
    fun confirmOrder(@Body req: ConfirmOrderRequest): Observable<BaseResponse<String>>

    /*
        根据ID获取订单
     */
    @POST("order/getOrderById")
    fun getOrderById(@Body req: GetOrderByIdRequest): Observable<BaseResponse<Order>>

    /*
        根据订单状态查询查询订单列表
     */
    @POST("order/getOrderList")
    fun getOrderList(@Body req: GetOrderListRequest): Observable<BaseResponse<MutableList<Order>?>>


    /*
        提交订单
     */
    @POST("order/submitOrder")
    fun submitOrder(@Body req: SubmitOrderRequest): Observable<BaseResponse<String>>


}
