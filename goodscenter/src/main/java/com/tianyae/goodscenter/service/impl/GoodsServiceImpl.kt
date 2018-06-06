package com.tianyae.goodscenter.service.impl

import com.tianyae.baselibrary.ext.convert
import com.tianyae.goodscenter.data.protocol.Category
import com.tianyae.goodscenter.data.protocol.Goods
import com.tianyae.goodscenter.data.repository.CategoryRepository
import com.tianyae.goodscenter.data.repository.GoodsRepository
import com.tianyae.goodscenter.service.CategoryService
import com.tianyae.goodscenter.service.GoodsService
import io.reactivex.Observable
import javax.inject.Inject

class GoodsServiceImpl @Inject constructor() : GoodsService {

    @Inject
    lateinit var repository: GoodsRepository

    /*
        获取商品列表
     */
    override fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?> {
        return repository.getGoodsList(categoryId, pageNo).convert()
    }

    /*
      根据关键字查询商品
   */
    override fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<MutableList<Goods>?> {
        return repository.getGoodsListByKeyword(keyword, pageNo).convert()
    }

    /*
        商品详情
     */
    override fun getGoodsDetail(goodsId: Int): Observable<Goods> {
        return repository.getGoodsDetail(goodsId).convert()
    }


}