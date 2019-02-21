package com.northland.libhttp.requests

import android.util.ArrayMap
import com.northland.libhttp.OkHttpClientHolder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import java.io.IOException

/**
 *
 * @author hemeng
 * @create 2019-02-19 18:07
 **/
abstract class RequestNew {

    lateinit var requestUrl: String
    val requetBuilder: Request.Builder = Request.Builder()
    var requestTag: String? = null
    var requestParams: ArrayMap<String, String> = ArrayMap()
    var responseCallBack: Callback? = null

    /**
     * 设置请求的 url 地址
     * */
    fun url(url: String): RequestNew {
        this.requestUrl = url
        return this@RequestNew
    }

    /**
     * 设置请求的 tag
     * */
    fun tag(tag: String): RequestNew {
        this.requestTag = tag
        return this@RequestNew
    }

    /**
     * 设置请求的 callback
     * */
    fun callBack(callBack: Callback): RequestNew {
        this.responseCallBack = callBack
        return this@RequestNew
    }

    /**
     * 设置请求的参数
     * */
    fun param(map: Map<String, String>): RequestNew {
        if (map.isEmpty()) {
            return this@RequestNew
        }
        requestParams.putAll(map)
        return this@RequestNew
    }


    /**
     * 真正执行请求的方法
     * */
    fun onRequest() {
        var call: Call? = null
        try {
            handleParams()
            val r = create()
            call = OkHttpClientHolder.getHttpClient()?.newCall(r)
            if (responseCallBack != null) {
                call?.enqueue(responseCallBack)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            if (responseCallBack != null) {
                responseCallBack?.onFailure(call, IOException(e.toString()))
            }
        }

    }


    /**
     * 构造一个 Request 对象
     * */
    abstract fun create(): Request

    /**
     * 处理请求参数
     * <li> GET 请求是拼接在 url 后面
     * <li> POST 请求是放到 from 表单里
     * */
    abstract fun handleParams()


//    abstract fun requestBody(): RequestBody?


}