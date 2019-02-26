package com.northland.libimage

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

/**
 *
 * @author hemeng
 * @create 2019-02-26 11:08
 **/
class LoadHelper {

    interface onLoadCallBack {


        fun onLoadedBitmap(img:Bitmap)

        fun onLoadFailed()
    }


    companion object {
        fun onLoadBitmap(ctc: Context?, url: String?,callBack: onLoadCallBack?) {
            if (url == null || ctc == null) {
                return
            }
            Glide.with(ctc).asBitmap().load(url).into(object :CustomTarget<Bitmap>(){
                override fun onLoadCleared(placeholder: Drawable?) {
                    callBack?.onLoadFailed()
                }
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    callBack?.onLoadedBitmap(resource)
                }
            })

        }
    }


}