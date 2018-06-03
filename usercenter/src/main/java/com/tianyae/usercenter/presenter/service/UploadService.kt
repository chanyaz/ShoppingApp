package com.tianyae.usercenter.presenter.service

import io.reactivex.Observable

interface UploadService {
    fun getUploadToken():Observable<String>
}