package com.northland.libhttp.requests

import com.northland.libhttp.OkHttpClientHolder
import okhttp3.FormBody
import okhttp3.Headers
import okhttp3.Request
import okhttp3.RequestBody

/**
 *
 * @author hemeng
 * @create 2019-02-20 17:57
 **/
open class PostRequest : RequestNew() {

    lateinit var requetBody: RequestBody

    override fun create(): Request {

        val c = Headers.of(OkHttpClientHolder.getHolder().getCommonHeader())
        val builder = Headers.Builder()
        builder.addAll(c)
        builder.add("Content-Length",(requetBody.contentLength()).toString())
        val header = builder.build()

        return requetBuilder.post(requetBody).url(requestUrl).tag(requestTag)
            .headers(header).build()
    }

    override fun handleParams() {
        val builder = FormBody.Builder()
        if (requestParams.isNotEmpty()) {
            for (index in 0 until requestParams.size) {
                builder.add(requestParams.keyAt(index), requestParams.valueAt(index))
            }
        }
        requetBody = builder.build()
    }
}