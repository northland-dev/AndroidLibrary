package com.northland.liblogin.taks

import android.content.Intent
import com.facebook.accountkit.AccountKit
import com.facebook.accountkit.AccountKitLoginResult
import com.facebook.accountkit.ui.AccountKitActivity
import com.facebook.accountkit.ui.AccountKitConfiguration
import com.facebook.accountkit.ui.LoginType
import com.northland.liblogin.interfaces.Request

/**
 *
 * @author hemeng
 * @create 2019-02-26 15:36
 **/
class AccountKitLoginTask(private val r: Request) : LoginTask(r) {

    private val ACCTOUNT_KIT_REQUEST_CODE = 103

    override fun tokenExpired(): Boolean {
        return AccountKit.getCurrentAccessToken() == null
    }

    override fun requestNewToken() {

        val config = AccountKitConfiguration.AccountKitConfigurationBuilder(
            LoginType.PHONE,
            AccountKitActivity.ResponseType.CODE
        ).build()
        val intent = Intent(r.getActivity(), AccountKitActivity::class.java)
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, config)
        r.getActivity().startActivityForResult(intent, ACCTOUNT_KIT_REQUEST_CODE)

    }

    override fun returnCacheToken() {
        val token = AccountKit.getCurrentAccessToken()
        if (token != null) {
            r.getCallBack().onSuccessed(token)
        } else {
            r.getCallBack().onFailed(Exception("token is null "))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            ACCTOUNT_KIT_REQUEST_CODE -> {
                if (data == null) {
                    r.getCallBack().onFailed(Exception("onActivityResult data is null"))
                    return
                }
                val result = data.getParcelableExtra<AccountKitLoginResult>(AccountKitLoginResult.RESULT_KEY)
                if (result.error != null) {
                    r.getCallBack().onFailed(Exception(result.error?.errorType?.message))
                    return
                }

                if (result.wasCancelled()) {
                    r.getCallBack().onFailed(Exception("user canceled"))
                    return
                }
                val token = result.accessToken
                if (token != null) {
                    r.getCallBack().onSuccessed(token)
                } else {
                    r.getCallBack().onFailed(Exception("result token is null"))
                }
            }
        }
    }
}