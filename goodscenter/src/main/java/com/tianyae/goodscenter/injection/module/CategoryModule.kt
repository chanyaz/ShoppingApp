package com.tianyae.goodscenter.injection.module

import com.tianyae.goodscenter.service.CategoryService
import com.tianyae.goodscenter.service.impl.CategoryServiceImpl
import dagger.Module
import dagger.Provides


/*
    商品分类Module
 */
@Module
class CategoryModule {
    @Provides
    fun provideCategoryService(categoryService: CategoryServiceImpl): CategoryService {
        return categoryService
    }
}