package com.tianyae.usercenter.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import com.kotlin.base.utils.DateUtils
import com.qiniu.android.storage.UploadManager
import com.tianyae.baselibrary.common.BaseConstant
import com.tianyae.baselibrary.ext.onClick
import com.tianyae.baselibrary.ui.activity.BaseMvpActivity
import com.tianyae.baselibrary.utils.GlideUtils
import com.tianyae.usercenter.R
import com.tianyae.usercenter.injection.component.DaggerUserComponent
import com.tianyae.usercenter.injection.module.UserModule
import com.tianyae.usercenter.presenter.UserInfoPresenter
import com.tianyae.usercenter.presenter.view.UserInfoView
import kotlinx.android.synthetic.main.activity_user_info.*
import java.io.File


/*
    用户信息
*/

class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView, TakePhoto.TakeResultListener {

    private lateinit var mTakePhoto: TakePhoto

    private lateinit var mTempFile: File

    private val mUploadManager: UploadManager by lazy { UploadManager() }

    private var mLocalFileUrl:String? = null
    private var mRemoteFileUrl:String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        mTakePhoto = TakePhotoImpl(this, this)
        mTakePhoto.onCreate(savedInstanceState)

        initView()
        initData()
    }

    private fun initData() {

    }


    /*
        初始化视图
     */
    private fun initView() {
        mUserIconView.onClick {
            showAlertView()
        }

    }

    private fun showAlertView() {
        AlertView("选择图片", null, "取消", null, arrayOf("拍照", "相册"), this,
                AlertView.Style.ActionSheet, OnItemClickListener { _, position ->

            mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)

            when (position) {
                0 -> {
                    createTempFile()
                    mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
                }
                1 -> {
                    mTakePhoto.onPickFromGallery()
                }

            }
        }).show()
    }


    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun takeSuccess(result: TResult?) {
        Log.d("TakePhoto", result?.image?.originalPath)
        Log.d("TakePhoto", result?.image?.compressPath)

        mLocalFileUrl = result?.image?.compressPath

        mPresenter.getUploadToken()
    }

    override fun takeCancel() {
    }

    override fun takeFail(result: TResult?, msg: String?) {
        Log.e("TakePhoto", msg)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode, resultCode, data)
    }

    private fun createTempFile() {
        val tempFileName = "${DateUtils.curTime}.png"
        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            this.mTempFile = File(Environment.getExternalStorageDirectory(), tempFileName)
            return
        }

        mTempFile = File(filesDir, tempFileName)

    }

    override fun onGetUploadTokenResult(result: String) {
        mUploadManager.put(mLocalFileUrl,null,result, { _, _, response ->
            mRemoteFileUrl = BaseConstant.IMAGE_SERVER_ADDRESS + response?.get("hash")

            Log.d("test", mRemoteFileUrl)
            GlideUtils.loadUrlImage(this@UserInfoActivity, mRemoteFileUrl!!,mUserIconIv)
        },null)
    }

}