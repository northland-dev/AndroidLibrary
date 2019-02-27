package com.northland.liblogin.interfaces

import android.app.Activity

/**
 *
 * @author hemeng
 * @create 2019-02-26 12:25
 **/
interface Request {


    fun getActivity(): Activity

    fun getCallBack(): CallBack

    fun execute()


    interface CallBack {
        fun onSuccessed(t: Any)
        fun onFailed(e: Throwable?)
    }

}