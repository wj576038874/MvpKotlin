package com.wenjie.kotlin.mvp.model.impl

import com.wenjie.kotlin.base.MvpKotlinApplication
import com.wenjie.kotlin.entity.*
import com.wenjie.kotlin.mvp.model.ILoginModel
import com.wenjie.kotlin.mvp.model.OnLoadDatasListener
import com.wenjie.kotlin.request.ApiService
import com.wenjie.kotlin.request.OkHttpUtils
import com.wenjie.kotlin.request.RequestObserver
import com.wenjie.kotlin.utils.CacheUtil
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

/**
 * ProjectName: MvpKotlin
 * PackageNmae: com.wenjie.kotlin.mvp.model.impl
 * Author: wenjie
 * FileName: com.wenjie.kotlin.mvp.model.impl.LoginModel.java
 * Date: 2018-05-03 10:05
 * Description:
 */
class LoginModel : ILoginModel {
    override fun bebing(onLoadDatasListener: OnLoadDatasListener<TopicsAndNews>) {
        val observable1 = OkHttpUtils.getRetrofit()!!.create(ApiService::class.java).loadTopicList(1)
        val observable2 = OkHttpUtils.getRetrofit()!!.create(ApiService::class.java).loadNewsList(1)

        val observable = Observable.zip(observable1, observable2, BiFunction<Response<List<Topic>>, Response<List<New>>, TopicsAndNews> { topicsResponse, newsResponse ->
            val topicsAndNews = TopicsAndNews()
            if (newsResponse.isSuccessful) {
                topicsAndNews.topics = topicsResponse.body()
            }
            if (topicsResponse.isSuccessful) {
                topicsAndNews.news = newsResponse.body()
            }
            topicsAndNews
        })

        val observer = object : RequestObserver<TopicsAndNews>() {
            override fun onSuccess(t: TopicsAndNews) {
                onLoadDatasListener.onSuccess(t)
            }

            override fun onFailure(msg: String) {
                onLoadDatasListener.onFailure(msg)
            }

        }

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)

    }

    override fun getMe(client_id: String, client_secret: String, grant_type: String, username: String, password: String, onLoadDatasListener: OnLoadDatasListener<UserDetail>) {
        val observable: Observable<Response<UserDetail>> = OkHttpUtils.getRetrofit()!!.create(ApiService::class.java).getToken(client_id, client_secret, grant_type, username, password)
                .flatMap({ t ->
                    if (t.isSuccessful) {
                        val cacheUtil = CacheUtil(MvpKotlinApplication.Companion.instence!!)
                        val token = t.body()
                        if (token != null) {
                            cacheUtil.saveToken(token)
                        }
                        return@flatMap OkHttpUtils.getRetrofit()!!.create(ApiService::class.java).getMe()
                    }
                    val responseBody = t.errorBody()!!
                    val userDetailResponse: Response<UserDetail> = Response.error(t.code(), responseBody)
                    return@flatMap Observable.just(userDetailResponse)
                })

        val observer: Observer<Response<UserDetail>> = object : RequestObserver<Response<UserDetail>>() {
            override fun onSuccess(t: Response<UserDetail>) {
                when {
                    t.isSuccessful -> onLoadDatasListener.onSuccess(t.body()!!)
                    t.code() == 401 -> onLoadDatasListener.onFailure("用户名或密码错误")
                    else -> onLoadDatasListener.onFailure("获取用户信息失败")
                }
            }

            override fun onFailure(msg: String) {
                onLoadDatasListener.onFailure(msg)
            }

        }
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)

    }

    override fun login(client_id: String, client_secret: String, grant_type: String, username: String, password: String, onLoadDatasListener: OnLoadDatasListener<Token>) {
        val observable = OkHttpUtils.getRetrofit()!!.create(ApiService::class.java).getToken(client_id, client_secret, grant_type, username, password)
        val observer = object : RequestObserver<Response<Token>>() {
            override fun onSuccess(t: Response<Token>) {
                when {
                    t.isSuccessful -> onLoadDatasListener.onSuccess(t.body()!!)
                    t.code() == 401 -> onLoadDatasListener.onFailure("用户名或密码错误")
                    else -> onLoadDatasListener.onFailure("登录失败")
                }
            }

            override fun onFailure(msg: String) {
                onLoadDatasListener.onFailure(msg)
            }
        }
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }


}