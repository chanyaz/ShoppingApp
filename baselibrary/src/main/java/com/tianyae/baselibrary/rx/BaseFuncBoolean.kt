package com.tianyae.baselibrary.rx

import com.tianyae.baselibrary.data.protocol.BaseResponse
import io.reactivex.Observable
import io.reactivex.functions.Function

class BaseFuncBoolean<T> : Function<BaseResponse<T>, Observable<Boolean>> {
    override fun apply(t: BaseResponse<T>): Observable<Boolean> {
        if (t.status != 0) {
            return Observable.error(BaseExceptioon(t.status, t.message))
        }
        return Observable.just(true)
    }
}