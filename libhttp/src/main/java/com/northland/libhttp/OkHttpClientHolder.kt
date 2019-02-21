package com.northland.libhttp

import android.os.Handler
import android.os.Looper
import android.util.ArrayMap
import com.northland.libhttp.interceptors.LogInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 *
 * @author
 * @create 2019-02-19 15:55
 **/
class OkHttpClientHolder {

    var okHttpClient: OkHttpClient? = null
    private val TIME_OUT = 60L//
    private val commonHeaders: ArrayMap<String, String> = ArrayMap()
    private val uiHandler = Handler(Looper.getMainLooper())

    init {
        onConfigOkHttp()
    }


    private fun onConfigOkHttp() {
        val bulider = OkHttpClient.Builder()

        // set timeout time
        bulider.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        bulider.writeTimeout(TIME_OUT, TimeUnit.SECONDS)
        bulider.readTimeout(TIME_OUT, TimeUnit.SECONDS)

        addInterceptor(bulider)
        okHttpClient = bulider.build()
    }

    private fun addInterceptor(builder: OkHttpClient.Builder) {
        if (BuildConfig.BUILD_TYPE == "debug") {
            //todo add logger
            builder.addInterceptor(LogInterceptor())
        }
    }


    fun cleanAndSetHeaders(map: Map<String, String>) {
        if (map.isEmpty()) {
            return
        }
        commonHeaders.clear()
        commonHeaders.putAll(map)
    }

    fun getCommonHeader(): Map<String, String> {
        return commonHeaders
    }

    fun post2UiThread(r: Runnable) {
        uiHandler.post(r)
    }


    /**
     * cancel a request by tag
     * @param tag
     * */
    fun onCancel(tag: String?) {
        if (tag == null || "" == tag) {
            return
        }
        if (okHttpClient != null) {
            for (call in okHttpClient?.dispatcher()?.queuedCalls()!!) {
                if (tag == call.request().tag()) {
                    call.cancel()
                }
            }

            for (call in okHttpClient?.dispatcher()?.runningCalls()!!) {
                if (tag == call.request().tag()) {
                    call.cancel()
                }
            }
        }
    }


    companion object {
        private var holder: OkHttpClientHolder = OkHttpClientHolder()
        fun getHttpClient(): OkHttpClient? {
            return holder.okHttpClient
        }

        fun getHolder(): OkHttpClientHolder {
            return holder
        }
    }

}