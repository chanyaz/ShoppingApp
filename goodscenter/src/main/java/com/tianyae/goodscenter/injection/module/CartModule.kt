package com.tianyae.goodscenter.injection.module

import com.tianyae.goodscenter.service.CartService
import com.tianyae.goodscenter.service.impl.CartServiceImpl
import dagger.Module
import dagger.Provides


/*
    购物车Module
 */
@Module
class CartModule {
    @Provides
    fun provideCartservice(cartService: CartServiceImpl): CartService {
        return cartService
    }
}