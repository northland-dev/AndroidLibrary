package com.northland.libhttp.requests

import okhttp3.MediaType
import okhttp3.RequestBody

/**
 *
 * @author hemeng
 * @create 2019-02-20 18:44
 **/
class PostJsonRequest(val json:String) : PostRequest() {

    override fun handleParams() {
        requetBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),json)
    }
}