package com.northland.liblogin.taks

import android.content.Intent
import android.content.pm.PackageManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.northland.liblogin.interfaces.Request

/**
 *
 * @author hemeng
 * @create 2019-02-26 12:42
 **/
class GoogleLoginTask(private val r: Request) : LoginTask(r) {


    private var googleClient: GoogleSignInClient
    private val GOOGLE_REQUEST_CODE = 101

    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getClientId()).requestEmail().build()
        googleClient = GoogleSignIn.getClient(r.getActivity(), gso)
    }

    private fun getClientId(): String? {
//        return r.getActivity().packageManager.metaData.getString("google.client.id")
        var id: String = ""

        try {
            val info = r.getActivity().packageManager.getApplicationInfo(r.getActivity().packageName,PackageManager.GET_META_DATA)
            id = info.metaData.getString("google.client.id")
        } catch (e: Exception) {

        }
        return id
    }

    override fun tokenExpired(): Boolean {
        val a = GoogleSignIn.getLastSignedInAccount(r.getActivity()) ?: return true
        return a.isExpired
    }

    override fun requestNewToken() {
        r.getActivity().startActivityForResult(googleClient.signInIntent, GOOGLE_REQUEST_CODE)
    }

    override fun returnCacheToken() {
        val data = GoogleSignIn.getLastSignedInAccount(r.getActivity())
        if (data != null) {
            r.getCallBack().onSuccessed(data)
        } else {
            r.getCallBack().onFailed(Exception("GoogleSignIn LastSignedInAccount is null!"))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            GOOGLE_REQUEST_CODE -> {
                try {
                    val account = GoogleSignIn.getSignedInAccountFromIntent(data)
                    val t = account.getResult(ApiException::class.java)
                    if (t != null) {
                        r.getCallBack().onSuccessed(t)
                    } else {
                        r.getCallBack().onFailed(Exception("onActivityResult getResult is null "))
                    }
                } catch (e: Exception) {
                    r.getCallBack().onFailed(e)
                }
            }
        }
    }

}