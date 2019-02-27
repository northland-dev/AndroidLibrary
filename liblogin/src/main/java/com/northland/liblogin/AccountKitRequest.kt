package com.northland.liblogin

import android.app.Activity
import android.content.Intent
import com.northland.liblogin.callbacks.AccountKitCallback
import com.northland.liblogin.interfaces.OnActivityResult
import com.northland.liblogin.interfaces.Request
import com.northland.liblogin.taks.AccountKitLoginTask

/**
 *
 * @author hemeng
 * @create 2019-02-26 17:39
 **/
class AccountKitRequest(private val act: Activity, private val call: AccountKitCallback) :
    Request, OnActivityResult {
    override fun execute() {
        task.run()
    }

    private val task = AccountKitLoginTask(this)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        task.onActivityResult(requestCode, resultCode, data)
    }

    override fun getActivity(): Activity {
        return act
    }

    override fun getCallBack(): Request.CallBack {
        return call
    }
}