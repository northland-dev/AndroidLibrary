package com.northland.liblogin.callbacks

import com.facebook.accountkit.AccessToken
import com.northland.liblogin.interfaces.Request

/**
 *
 * @author hemeng
 * @create 2019-02-26 17:15
 **/
abstract class AccountKitCallback : Request.CallBack {
    override fun onSuccessed(t: Any) {
        if (t is AccessToken) {
            onSuccessed(t)
        }
    }


    abstract fun done(t: AccessToken)
}