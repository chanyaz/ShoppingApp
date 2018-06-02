package com.tianyae.baselibrary.rx

import com.tianyae.baselibrary.common.ResultCode
import com.tianyae.baselibrary.data.protocol.BaseResponse
import io.reactivex.Observable
import io.reactivex.functions.Function

class BaseFuncString<T> : Function<BaseResponse<T>, Observable<String>> {
    override fun apply(t: BaseResponse<T>): Observable<String> {
        if (t.status != ResultCode.SUCCESS) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(t.message)
    }
}