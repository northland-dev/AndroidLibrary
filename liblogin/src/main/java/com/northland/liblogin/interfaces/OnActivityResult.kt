package com.northland.liblogin.interfaces

import android.content.Intent

/**
 *
 * @author hemeng
 * @create 2019-02-26 17:27
 **/
interface OnActivityResult {
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
}