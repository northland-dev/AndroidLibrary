package com.northland.liblogin.callbacks

import com.facebook.AccessToken
import com.northland.liblogin.interfaces.Request

/**
 *
 * @author hemeng
 * @create 2019-02-26 17:12
 **/
abstract class FacebookCallBack : Request.CallBack {
    override fun onSuccessed(t: Any) {
        if (t is AccessToken) {
            onSuccessed(t)
        } else {
            onFailed(Exception("t is not AccessToken"))
        }
    }

    abstract fun done(t: AccessToken)

}