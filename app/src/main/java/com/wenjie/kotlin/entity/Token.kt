package com.wenjie.kotlin.entity

import java.io.Serializable

/**
 * ProjectName: MvpKotlin
 * PackageNmae: com.wenjie.kotlin.entity
 * Author: wenjie
 * FileName: com.wenjie.kotlin.entity.Token.java
 * Date: 2018-05-03 9:39
 * Description:
 */
class Token : Serializable {
    var access_token: String? = null// 用户令牌(获取相关数据使用)
    var token_type: String? = null// 令牌类型
    var expires_in: Int? = null// 过期时间
    var refresh_token: String? = null// 刷新令牌(获取新的令牌)
    var created_at: Int? = null // 创建时间


    override fun toString(): String {
        return "Token{" +
                "access_token='" + access_token + '\'' +
                ", token_type='" + token_type + '\'' +
                ", expires_in=" + expires_in +
                ", refresh_token='" + refresh_token + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}