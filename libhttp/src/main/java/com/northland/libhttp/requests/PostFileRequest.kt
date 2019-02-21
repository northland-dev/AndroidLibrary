package com.northland.libhttp.requests

import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 *
 * @author hemeng
 * @create 2019-02-20 18:46
 **/
class PostFileRequest(val filePath: String) : PostRequest() {


    override fun handleParams() {
        val uploadFile = File(filePath)
        if (uploadFile.exists()) {
            requetBody = RequestBody.create(MultipartBody.FORM, uploadFile)
        }
    }


}