package com.northland.libhttp.callbacks

import com.alibaba.fastjson.JSONObject
import com.northland.libhttp.OkHttpClientHolder
import okhttp3.Call
import okhttp3.Response
import java.io.IOException
import java.lang.reflect.ParameterizedType

/**
 *
 * @author hemeng
 * @create 2019-02-21 11:08
 **/
open class JsonCallBack<T> : BaseCallBack {

    override fun onFailure(call: Call, e: IOException) {
        super.onFailure(call, e)
    }

    open fun onResponseData(t: T) {}

    override fun onResponseString(data: String?) {
        super.onResponseString(data)
        if (data != null) {
            val genType = javaClass.genericSuperclass
            if (genType != null && genType is ParameterizedType) {
                val type = genType.actualTypeArguments[0]
                val jsonObj = JSONObject.parseObject<T>(data, type)
                OkHttpClientHolder.getHolder().post2UiThread(Runnable {
                    onResponseData(jsonObj as T)
                })
            }
        }
    }


}