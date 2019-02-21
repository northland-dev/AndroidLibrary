package com.northland.libhttp.interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import java.lang.Exception
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit
import java.util.logging.Level
import java.util.logging.Logger

/**
 *
 * @author
 * @create 2019-02-19 16:59
 **/
class LogInterceptor : Interceptor {

    private val infoLogger: Logger = Logger.getLogger("http")
    private val send = ">>>"
    private val rec = "<<<"

    private fun log(info: String) {
        infoLogger.log(Level.INFO, info)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val start = System.nanoTime()
        var response: Response? = null
        try {
            response = chain.proceed(request)
        } catch (e: Exception) {
            throw e
        }
        val useTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start)
        logRequest(request, chain)
        return logResponse(response, chain, useTime)
    }

    private fun logResponse(response: Response, chain: Interceptor.Chain, useTime: Long): Response {
        val firstLine = "\t $rec ${response.code()}:${response.message()}:${response.request().url()}:$useTime ms"
        log(firstLine)
        //headers
        for (index in 0 until response.headers().size()) {
            log("\t $rec ${response.headers().name(index)}:${response.headers().value(index)}")
        }
        val body = response.body()
        if (body != null) {
            val data = body.string()
            log("\t $rec response body:$data")

            //这里需要注意 消费一个 response 再返回一个 copy 的
            val type = body.contentType()
            val newBody = ResponseBody.create(type, data)
            return response.newBuilder().body(newBody).build()
        }
        return response
    }


    private fun logRequest(request: Request, chain: Interceptor.Chain) {
        val firstLine = "\t $send ${request.method()}:${request.url()}"
        log(firstLine)
        //headers
        for (index in 0 until request.headers().size()) {
            log("\t $send ${request.headers().name(index)}:${request.headers().value(index)}")
        }
        //requestBody
        if (request.body() != null) {
            val type = request.body()?.contentType()?.type()
            var canPrint = false
            if ("text" == type) {
                canPrint = true
            } else {
                val subType = request.body()?.contentType()?.subtype()?.toLowerCase()
                if (subType != null) {
                    if (subType.contains("json") || subType.contains("xml") || subType.contains("html")) {
                        canPrint = true
                    }
                }
            }
            if (canPrint) {
                val r = request.newBuilder().build().body() ?: return
                val buffer = Buffer()
                r.writeTo(buffer)
                val str = buffer.readString(Charset.forName("UTF-8"))
                log("\t $send request body:$str")
            }
        }

    }
}