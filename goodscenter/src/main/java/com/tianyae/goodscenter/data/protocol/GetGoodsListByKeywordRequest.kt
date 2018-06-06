package com.tianyae.goodscenter.data.protocol

data class GetGoodsListByKeywordRequest(val keyword: String,
                                        val pageNo: Int)