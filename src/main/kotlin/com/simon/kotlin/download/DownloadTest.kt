package com.simon.kotlin.download

import okhttp3.*
import okio.IOException
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit

object DownloadTest {

    /**
     * 下载方法
     *
     * @param url                 下载的URL
     * @param destFileDir         文件保存路径
     * @param callBack 下载结果回调
     */
    fun download(
        url: String,
        destFileDir: String,
        code: Long,
        callBack: DownloadCallBack
    ) {
        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
        val request = Request.Builder().url(url).build()
        val call = mOkHttpClient.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callBack.error(Throwable(e), url, destFileDir, code)
            }

            override fun onResponse(call: Call, response: Response) {
                println("response code " + response.code)
                if (response.code != 200) {
                    callBack.error(IllegalArgumentException("url error"),url,destFileDir,code)
                    return
                }
                val buf = ByteArray(2048)
                var len: Int
                val arr = url.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val fileName = arr[arr.size - 1]
                val parent = File(destFileDir)
                if (!parent.exists()) {
                    val a = parent.mkdirs()
                }
                val file = File(destFileDir, fileName)
                try {
                    response.body?.byteStream().use { `is` ->
                        FileOutputStream(file).use { fos ->
                            if (`is` != null) {
                                while ((`is`.read(buf).also {
                                        len = it
                                    }) != -1) {
                                    fos.write(buf, 0, len)
                                }
                                fos.flush()
                                callBack.success(file, code)
                            }
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        })
    }

    /**
     * The interface Web download call back.
     */
    interface DownloadCallBack {
        /**
         * 异常回调
         *
         * @param throwable   错误信息
         * @param url         url
         * @param destFileDir 路径
         * @param code        错误编码
         */
        fun error(throwable: Throwable, url: String, destFileDir: String, code: Long)

        /**
         * Success.
         *
         * @param file the file
         * @param code code
         */
        fun success(file: File, code: Long)
    }
}

fun main() {
    DownloadTest.download(
//        "https://audi-approval-market.jidouauto.com/html/2021-11-29/263b931db0014790a2a407c53e5812a7.html",
        "https://cdn-public-live-audi.jidouauto.com/html/2021-11-29/263b931db0014790a2a407c53e5812a7.html",
        "src/main/resources",
        29L,
        object : DownloadTest.DownloadCallBack {
            override fun error(throwable: Throwable, url: String, destFileDir: String, code: Long) {
                println(throwable.message)
            }

            override fun success(file: File, code: Long) {
                println(file.absoluteFile)
            }
        })
}