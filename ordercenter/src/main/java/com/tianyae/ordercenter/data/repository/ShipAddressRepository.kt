package com.tianyae.ordercenter.data.repository

import com.tianyae.baselibrary.data.net.RetrofitFactory
import com.tianyae.baselibrary.data.protocol.BaseResponse
import com.tianyae.ordercenter.data.api.ShipAddressApi
import com.tianyae.ordercenter.data.protocol.AddShipAddressRequset
import com.tianyae.ordercenter.data.protocol.DeleteShipAddressRequset
import com.tianyae.ordercenter.data.protocol.EditShipAddressRequest
import com.tianyae.ordercenter.data.protocol.ShipAddress
import io.reactivex.Observable
import javax.inject.Inject

/*
   收货地址数据层
 */
class ShipAddressRepository @Inject constructor() {

    /*
        添加收货地址
     */
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(ShipAddressApi::class.java).addShipAddress(AddShipAddressRequset(shipUserName,shipUserMobile,shipAddress))
    }

    /*
        删除收货地址
     */
    fun deleteShipAddress(id: Int): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(ShipAddressApi::class.java).deleteShipAddress(DeleteShipAddressRequset(id))
    }

    /*
        修改收货地址
     */
    fun editShipAddress(address: ShipAddress): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(ShipAddressApi::class.java).editShipAddress(EditShipAddressRequest(address.id,address.shipUserName,address.shipUserMobile,address.shipAddress,address.shipIsDefault))
    }

    /*
        获取收货地址列表
     */
    fun getShipAddressList(): Observable<BaseResponse<MutableList<ShipAddress>?>> {
        return RetrofitFactory.instance.create(ShipAddressApi::class.java).getShipAddressList()
    }

}
