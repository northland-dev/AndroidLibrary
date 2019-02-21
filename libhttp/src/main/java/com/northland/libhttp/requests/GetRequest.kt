package com.northland.libhttp.requests

import com.northland.libhttp.OkHttpClientHolder
import okhttp3.*

/**
 *
 * @author
 * @create 2019-02-19 17:41
 **/
class GetRequest : RequestNew() {

    lateinit var realUrl: String

    override fun create(): Request {
        return requetBuilder.get().url(realUrl).tag(requestTag).headers(Headers.of(OkHttpClientHolder.getHolder().getCommonHeader())).build()
    }

    override fun handleParams() {
        val builder = StringBuilder(requestUrl)
        var hasAppend = false
        if (requestParams.isNotEmpty()) {
            builder.append("?")
            for (i in 0 until requestParams.size) {
                builder.append(requestParams.keyAt(i)).append("=").append(requestParams.valueAt(i)).append("&")
            }
            hasAppend = true
        }
        if (hasAppend) {
            //delete '&' at last
            builder.deleteCharAt(builder.length - 1)
        }
        realUrl = builder.toString()
    }



}