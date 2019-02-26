package com.northland.libimage

/**
 *
 * @author hemeng
 * @create 2019-02-26 11:36
 **/
class SuffixCheck {


    companion object {

        fun isSupport(path: String?): Boolean {
            if (path == null) {
                return false
            }

            if (path.endsWith(".png", true)
                || path.endsWith(".jpg", true)
                || path.endsWith(".gif", true)
            ) {
                return true
            }

            return false

        }
    }
}