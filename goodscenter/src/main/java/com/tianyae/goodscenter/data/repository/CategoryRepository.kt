package com.tianyae.goodscenter.data.repository

import com.tianyae.baselibrary.data.net.RetrofitFactory
import com.tianyae.baselibrary.data.protocol.BaseResponse
import com.tianyae.goodscenter.data.api.CategoryApi
import com.tianyae.goodscenter.data.protocol.Category
import com.tianyae.goodscenter.data.protocol.GetCategoryRequest
import io.reactivex.Observable
import javax.inject.Inject

/*
    商品分类 数据层
 */

class CategoryRepository @Inject constructor(){
    /*
        获取商品分类
     */
    fun getCategory(parentId: Int): Observable<BaseResponse<MutableList<Category>?>> {
        return RetrofitFactory.instance.create(CategoryApi::class.java).getCategory(GetCategoryRequest(parentId))
    }

}
