package com.tianyae.usercenter.data.protocol

data class EditUserRequest(val userIcon: String,
                           val userName: String,
                           val userGender: String,
                           val userSign: String)