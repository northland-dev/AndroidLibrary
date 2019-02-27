//package com.northland.liblogin
//
//import com.northland.liblogin.callbacks.AccountKitCallback
//import com.northland.liblogin.callbacks.FacebookCallBack
//import com.northland.liblogin.callbacks.GoogleCallBack
//import com.northland.liblogin.interfaces.Request
//import com.northland.liblogin.taks.AccountKitLoginTask
//import com.northland.liblogin.taks.FacebookLoginTask
//import com.northland.liblogin.taks.GoogleLoginTask
//
///**
// *
// * @author hemeng
// * @create 2019-02-26 15:52
// **/
//class TaskFactory {
//
//    companion object {
//        fun  getLoginTask(r: Request): LoginTask? {
//            var t: LoginTask? = null
//            when {
//                r.getCallBack() is GoogleCallBack -> t = GoogleLoginTask(r)
//                r.getCallBack() is FacebookCallBack -> t = FacebookLoginTask(r)
//                r.getCallBack() is AccountKitCallback -> t = AccountKitLoginTask(r)
//            }
//            return t
//        }
//
////        fun a(act: Activity) {
////
////            val abc = object : Request {
////                override fun getLoginType(): LoginType {
////                    return LoginType.GOOGLE
////                }
////
////                override fun getActivity(): Activity {
////                    return act
////                }
////
////                override fun getCallBack(): Request.CallBack {
////                    return object : GoogleCallBack() {
////                        override fun onSuccessed(t: GoogleSignInAccount) {
////                        }
////
////                        override fun onFailed(e: Throwable?) {
////                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
////                        }
////
////                    }
////                }
////            }
////
////
////            getLoginTask(abc)?.run()
////
////        }
//    }
//}