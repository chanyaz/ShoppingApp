package com.tianyae.baselibrary.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.*
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.tianyae.baselibrary.R

object GlideUtils {
    fun loadImage(context: Context, url: String, imageView: ImageView) {
        Glide.with(context).load(url).apply(centerCropTransform()).into(imageView)
    }

    fun loadImageFitCenter(context: Context, url: String, imageView: ImageView) {
        Glide.with(context).load(url).apply(fitCenterTransform()).into(imageView)
    }

    fun loadUrlImage(context: Context, url: String, imageView: ImageView) {
        Glide.with(context).load(url).apply(placeholderOf(R.drawable.ic_back).error(R.drawable.ic_back)).apply(centerCropTransform()).into(
                object : SimpleTarget<Drawable>() {
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                        imageView.setImageDrawable(resource)
                    }
                })

    }
}