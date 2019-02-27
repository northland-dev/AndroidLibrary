package com.northland.liblogin.taks

import android.content.Intent
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.northland.liblogin.interfaces.Request
import java.util.*

/**
 *
 * @author hemeng
 * @create 2019-02-26 15:19
 **/
class FacebookLoginTask(private val r: Request) : LoginTask(r) {

    private var callbackManager: CallbackManager = CallbackManager.Factory.create()
    private val permissions = Arrays.asList("public_profile", "email")

    override fun tokenExpired(): Boolean {
        return !AccessToken.isCurrentAccessTokenActive()
    }

    override fun requestNewToken() {
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                if (result?.accessToken != null) {
                    r.getCallBack().onSuccessed(result.accessToken)
                } else {
                    r.getCallBack().onFailed(Exception("facebook LoginResult is null"))
                }
            }

            override fun onCancel() {
                r.getCallBack().onFailed(Exception("user canceled "))
            }

            override fun onError(error: FacebookException?) {
                r.getCallBack().onFailed(error)
            }
        })
        LoginManager.getInstance().logInWithReadPermissions(r.getActivity(), permissions)


    }

    override fun returnCacheToken() {
        r.getCallBack().onSuccessed(AccessToken.getCurrentAccessToken())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}