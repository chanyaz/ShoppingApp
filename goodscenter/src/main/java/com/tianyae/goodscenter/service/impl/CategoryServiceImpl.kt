package com.tianyae.goodscenter.service.impl

import com.tianyae.baselibrary.ext.convert
import com.tianyae.goodscenter.data.protocol.Category
import com.tianyae.goodscenter.data.repository.CategoryRepository
import com.tianyae.goodscenter.service.CategoryService
import io.reactivex.Observable
import javax.inject.Inject

class CategoryServiceImpl @Inject constructor() : CategoryService {

    @Inject
    lateinit var repository: CategoryRepository


    override fun getCategory(parentId: Int): Observable<MutableList<Category>?> {
        return repository.getCategory(parentId).convert()
    }
}