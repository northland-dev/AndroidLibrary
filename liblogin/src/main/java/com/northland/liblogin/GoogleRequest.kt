package com.northland.liblogin

import android.app.Activity
import android.content.Intent
import com.northland.liblogin.callbacks.GoogleCallBack
import com.northland.liblogin.interfaces.OnActivityResult
import com.northland.liblogin.interfaces.Request
import com.northland.liblogin.taks.GoogleLoginTask

/**
 *
 * @author hemeng
 * @create 2019-02-26 17:23
 **/
class GoogleRequest(private val act: Activity, private val call: GoogleCallBack) :
    Request, OnActivityResult {
    override fun execute() {
        task.run()
    }

    private val task = GoogleLoginTask(this)

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