package com.tianyae.usercenter.presenter.service.impl

import com.tianyae.baselibrary.data.protocol.BaseRespone
import com.tianyae.baselibrary.rx.BaseExceptioon
import com.tianyae.usercenter.data.respository.UserRepository
import com.tianyae.usercenter.presenter.service.UserService
import io.reactivex.Observable
import io.reactivex.functions.Function

class UserServiceImpl : UserService {
    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {

        val repository = UserRepository()


        return repository.register(mobile, verifyCode, pwd)
                .flatMap(Function<BaseRespone<String>, Observable<Boolean>> { t ->
                    if (t.status != 0) {
                        return@Function Observable.error(BaseExceptioon(t.status, t.message))
                    }
                    Observable.just(true)
                })

    }
}
