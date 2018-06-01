package com.tianyae.baselibrary.rx

import com.tianyae.baselibrary.data.protocol.BaseResponse
import io.reactivex.Observable
import io.reactivex.functions.Function

class BaseFunc<T> : Function<BaseResponse<T>, Observable<T>> {
    override fun apply(t: BaseResponse<T>): Observable<T> {
        if (t.status != 0) {
            return Observable.error(BaseExceptioon(t.status, t.message))
        }
        return Observable.just(t.data)
    }
}