

按照第三方的 api 设计，需要在主工程中添加一个 xml 文件来存放第三方的一些 key 值，key 值已经写死在 login 的 AndroidManifest 中，只需要替换 vlaue 值即可，demo 如下

```
<?xml version="1.0" encoding="utf-8"?>
<resources>

    <string name="facebook_app_id" translatable="false">xxx</string>
    <string name="fb_login_protocol_scheme" translatable="false">xxx</string>
    <string name="ACCOUNT_KIT_CLIENT_TOKEN" translatable="false">xxx</string>
    <string name="google_client_id" translatable="false">xxx</string>

</resources>

```

#### demo

```
  val t = GoogleRequest(this, object : GoogleCallBack() {
                override fun done(t: GoogleSignInAccount) {
                }

                override fun onFailed(e: Throwable?) {
                }
            })
   task = t
   t.execute()
   
   
   var task: OnActivityResult? = null
   
   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
           super.onActivityResult(requestCode, resultCode, data)
           task?.onActivityResult(requestCode, resultCode, data)
       }
```