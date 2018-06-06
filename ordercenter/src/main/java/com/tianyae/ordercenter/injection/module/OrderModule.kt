package com.tianyae.ordercenter.injection.module

import com.tianyae.ordercenter.service.OrderService
import com.tianyae.ordercenter.service.impl.OrderServiceImpl
import dagger.Module
import dagger.Provides

/*
    订单Module
 */
@Module
class OrderModule {

    @Provides
    fun provideOrderservice(orderService: OrderServiceImpl): OrderService {
        return orderService
    }

}