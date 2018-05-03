package com.wenjie.kotlin.request

import com.wenjie.kotlin.entity.New
import com.wenjie.kotlin.entity.Token
import com.wenjie.kotlin.entity.Topic
import com.wenjie.kotlin.entity.UserDetail
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

/**
 * ProjectName: MvpKotlin
 * PackageNmae: com.wenjie.kotlin.request
 * Author: wenjie
 * FileName: com.wenjie.kotlin.request.ApiService.java
 * Date: 2018-05-03 9:37
 * Description:
 */
interface ApiService {

    /**
     * 获取 Token (一般在登录时调用)
     *
     * @param client_id     客户端 id
     * @param client_secret 客户端私钥
     * @param grant_type    授权方式 - 密码
     * @param username      用户名
     * @param password      密码
     * @return Token 实体类
     */
    @POST("https://www.diycode.cc/oauth/token")
    @FormUrlEncoded
    fun getToken(@Field("client_id") client_id: String, @Field("client_secret") client_secret: String,
                 @Field("grant_type") grant_type: String, @Field("username") username: String,
                 @Field("password") password: String): Observable<Response<Token>>

    /**
     * 获取当前登录者的详细资料
     *
     * @return 用户详情
     */
    @GET("users/me.json")
    fun getMe(): Observable<Response<UserDetail>>

    @GET("news.json")
    fun loadNewsList(@Query("limit") limit: Int?): Observable<Response<List<New>>>

    @GET("topics.json")
    fun loadTopicList(@Query("limit") limit: Int?): Observable<Response<List<Topic>>>
}