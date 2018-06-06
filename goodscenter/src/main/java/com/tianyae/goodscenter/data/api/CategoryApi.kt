package com.tianyae.goodscenter.data.api

import com.tianyae.baselibrary.data.protocol.BaseResponse
import com.tianyae.goodscenter.data.protocol.Category
import com.tianyae.goodscenter.data.protocol.GetCategoryRequest
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/*
    购物车 接口
 */
interface CategoryApi {
    /*
      获取商品分类列表
   */
    @POST("category/getCategory")
    fun getCategory(@Body req: GetCategoryRequest): Observable<BaseResponse<MutableList<Category>?>>
}