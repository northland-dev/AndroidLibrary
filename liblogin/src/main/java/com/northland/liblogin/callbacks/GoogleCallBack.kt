package com.northland.liblogin.callbacks

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.northland.liblogin.interfaces.Request

/**
 *
 * @author hemeng
 * @create 2019-02-26 17:08
 **/
abstract class GoogleCallBack : Request.CallBack {
    override fun onSuccessed(t: Any) {
        if (t is GoogleSignInAccount) {
            onSuccessed(t)
        } else {
            onFailed(Exception("t is not GoogleSignInAccount"))
        }
    }

    abstract fun done(t: GoogleSignInAccount)


}