package com.tianyae.ordercenter.injection.module

import com.tianyae.ordercenter.service.ShipAddressService
import com.tianyae.ordercenter.service.impl.ShipAddressServiceImpl
import dagger.Module
import dagger.Provides

/*
    收货人信息Module
 */
@Module
class ShipAddressModule {

    @Provides
    fun provideShipAddressservice(shipAddressService: ShipAddressServiceImpl): ShipAddressService {
        return shipAddressService
    }

}
