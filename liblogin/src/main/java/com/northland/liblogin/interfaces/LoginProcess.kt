package com.northland.liblogin.interfaces

/**
 *
 * @author hemeng
 * @create 2019-02-26 12:36
 **/
interface LoginProcess : OnActivityResult {


    fun tokenExpired(): Boolean

    fun requestNewToken()

    fun returnCacheToken()


}