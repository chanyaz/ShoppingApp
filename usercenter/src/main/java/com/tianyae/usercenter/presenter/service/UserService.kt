package com.tianyae.usercenter.presenter.service

import io.reactivex.Observable

interface UserService {
    fun register(mobile:String, verifyCOde:String, pwd:String):Observable<Boolean>
}