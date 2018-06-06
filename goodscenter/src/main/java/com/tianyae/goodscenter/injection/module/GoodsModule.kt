package com.tianyae.goodscenter.injection.module

import com.tianyae.goodscenter.service.CategoryService
import com.tianyae.goodscenter.service.GoodsService
import com.tianyae.goodscenter.service.impl.CategoryServiceImpl
import com.tianyae.goodscenter.service.impl.GoodsServiceImpl
import dagger.Module
import dagger.Provides


/*
    商品Module
 */
@Module
class GoodsModule {
    @Provides
    fun provideGoodsService(goodsService: GoodsServiceImpl): GoodsService {
        return goodsService
    }
}