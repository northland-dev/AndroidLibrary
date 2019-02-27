package com.northland.liblogin.taks

import com.northland.liblogin.interfaces.LoginProcess
import com.northland.liblogin.interfaces.Request

/**
 *
 * @author hemeng
 * @create 2019-02-26 12:41
 **/
abstract class LoginTask(val request: Request) : LoginProcess, Runnable {

    override fun run() {
        if (tokenExpired()) {
            requestNewToken()
        } else {
            returnCacheToken()
        }
    }
}