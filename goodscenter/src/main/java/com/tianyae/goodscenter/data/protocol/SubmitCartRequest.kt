package com.tianyae.goodscenter.data.protocol

data class SubmitCartRequest(val goodsList: List<CartGoods>, val totalPrice: Long)