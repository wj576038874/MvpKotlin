package com.wenjie.kotlin.request

import com.wenjie.kotlin.base.MvpKotlinApplication
import com.wenjie.kotlin.utils.CacheUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
/**
 * ProjectName: MvpKotlin
 * PackageNmae: com.wenjie.kotlin.request
 * Author: wenjie
 * FileName: com.wenjie.kotlin.request.OkHttpUtils.java
 * Date: 2018-05-03 9:42
 * Description:
 */
object OkHttpUtils {

    private val cacheUtil: CacheUtil = CacheUtil(MvpKotlinApplication.Companion.instence!!)
    private var retrofit: Retrofit? = null
    private var okHttpClient: OkHttpClient? = null

    fun getRetrofit(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl("https://diycode.cc/api/v3/")
                    .client(getOkHttpClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit
    }

    private fun getOkHttpClient(): OkHttpClient? {
        if (okHttpClient == null) {
            val builder = OkHttpClient.Builder()
            builder.addInterceptor(Interceptor { chain ->
                val original = chain.request()
                // 如果当前没有缓存 token 或者请求已经附带 token 了，就不再添加
                if (cacheUtil.token == null || alreadyHasAuthorizationHeader(original)) {
                    return@Interceptor chain.proceed(original)
                }
                val token = "Bearer " + cacheUtil.token.access_token
                val request = original.newBuilder()
                        .header("Authorization", token)
                        .build()
                return@Interceptor chain.proceed(request)
            })
            builder.connectTimeout(15 * 1000, TimeUnit.SECONDS)
            builder.readTimeout(15 * 1000, TimeUnit.SECONDS)
            okHttpClient = builder.build()
        }
        return okHttpClient
    }

    private fun alreadyHasAuthorizationHeader(originalRequest: Request): Boolean {
        val token = originalRequest.header("Authorization")
        // 如果本身是请求 token 的 URL，直接返回 true
        // 如果不是，则判断 header 中是否已经添加过 Authorization 这个字段，以及是否为空
        return !(token == null || token.isEmpty() || originalRequest.url().toString().contains("https://www.diycode.cc/oauth/token"))
    }


//    private var okHttpClient: OkHttpClient? = null
//
//    private val cacheUtil: CacheUtil = CacheUtil(MvpKotlinApplication.Companion.instence)
//
//    private var retrofit: Retrofit? = null
//
//    fun getRetrofit(): Retrofit? {
//        if (retrofit == null) {
//            retrofit = Retrofit.Builder()
//                    .baseUrl("https://diycode.cc/api/v3/")
//                    .client(getOkHttpClient())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//        }
//        return retrofit
//    }
//
//    private fun getOkHttpClient(): OkHttpClient? {
//        if (okHttpClient == null) {
//            val builder = OkHttpClient.Builder()
//            builder.addInterceptor(Interceptor { chain ->
//                val original = chain.request()
//                // 如果当前没有缓存 token 或者请求已经附带 token 了，就不再添加
//                if (cacheUtil.token == null || alreadyHasAuthorizationHeader(original)) {
//                    return@Interceptor chain.proceed(original)
//                }
//                val token = "Bearer " + cacheUtil.token.access_token
//                val request = original.newBuilder()
//                        .header("Authorization", token)
//                        .build()
//                return@Interceptor chain.proceed(request)
//            })
//            builder.connectTimeout(15 * 1000, TimeUnit.SECONDS)
//            builder.readTimeout(15 * 1000, TimeUnit.SECONDS)
//            okHttpClient = builder.build()
//        }
//        return okHttpClient
//    }
//
//    private fun alreadyHasAuthorizationHeader(originalRequest: Request): Boolean {
//        val token = originalRequest.header("Authorization")
//        // 如果本身是请求 token 的 URL，直接返回 true
//        // 如果不是，则判断 header 中是否已经添加过 Authorization 这个字段，以及是否为空
//        return (token == null || token.isEmpty() || originalRequest.url().toString().contains("https://www.diycode.cc/oauth/token"))
//    }
}

