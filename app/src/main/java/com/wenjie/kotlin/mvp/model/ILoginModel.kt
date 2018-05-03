package com.wenjie.kotlin.mvp.model

import com.wenjie.kotlin.entity.Token
import com.wenjie.kotlin.entity.TopicsAndNews
import com.wenjie.kotlin.entity.UserDetail

/**
 * ProjectName: MvpKotlin
 * PackageNmae: com.wenjie.kotlin.mvp.model
 * Author: wenjie
 * FileName: com.wenjie.kotlin.mvp.model.ILoginModel.java
 * Date: 2018-05-03 10:04
 * Description:
 */
interface ILoginModel {

    /**
     * 登陆方法
     *
     * @param client_id           client_id
     * @param client_secret       client_secret
     * @param grant_type          grant_type
     * @param username            用户名
     * @param password            用户密码
     * @param onLoadDatasListener 监听函数
     */
    fun login(client_id: String, client_secret: String, grant_type: String, username: String, password: String, onLoadDatasListener: OnLoadDatasListener<Token>)

    /**
     * 获取用户信息，用户登录成之后获取到token 然后拿到token再用token去获取用户信息
     *
     * @param onLoadDatasListener 监听
     */
    fun getMe(client_id: String, client_secret: String, grant_type: String, username: String, password: String, onLoadDatasListener: OnLoadDatasListener<UserDetail>)


    fun bebing(onLoadDatasListener: OnLoadDatasListener<TopicsAndNews>)

}