package com.tianyae.ordercenter.data.protocol

/*
    修改收货地址
 */
data class EditShipAddressRequest(val id:Int,val shipUserName:String,val shipUserMobile:String,val shipAddress:String,val shipIsDefault:Int)
