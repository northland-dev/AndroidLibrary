package com.northland.libhttp.callbacks

import android.util.Log
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

/**
 *
 * @author
 * @create 2019-02-19 18:01
 **/
interface BaseCallBack : Callback {

    override fun onFailure(call: okhttp3.Call, e: IOException) {
        Log.e("", e.toString())
    }

    override fun onResponse(call: okhttp3.Call, response: Response) {
//        Log.i("",response.body()?.string())
        val json = response.body()?.string()
        onResponseString(json)
    }

    open fun onResponseString(data: String?) {}


}