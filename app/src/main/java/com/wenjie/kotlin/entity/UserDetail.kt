package com.wenjie.kotlin.entity

import java.io.Serializable

/**
 * ProjectName: MvpKotlin
 * PackageNmae: com.wenjie.kotlin.entity
 * Author: wenjie
 * FileName: com.wenjie.kotlin.entity.UserDetail.java
 * Date: 2018-05-03 11:37
 * Description:
 */
class UserDetail : Serializable {
    var id: Int = 0                     // ID
    var login: String? = null               // 用户名
    var name: String? = null                // 昵称
    var avatar_url: String? = null          // 头像链接
    var location: String? = null            // 城市
    var company: String? = null             // 公司
    var twitter: String? = null             // twitter
    var website: String? = null             // 网站地址
    var bio: String? = null                 // 个人介绍
    var tagline: String? = null             // 签名
    var github: String? = null              // github
    var created_at: String? = null          // 创建时间
    var email: String? = null               // email
    var topics_count: Int = 0           // 话题数量
    var replies_count: Int = 0          // 回复数量
    var following_count: Int = 0        // 正在 follow 的人数
    var followers_count: Int = 0        // follow 他的人数
    var favorites_count: Int = 0        // 收藏的数量
    var level: String? = null               // 等级
    var level_name: String? = null          // 等级名称

    override fun toString(): String {
        return "UserDetail{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", location='" + location + '\'' +
                ", company='" + company + '\'' +
                ", twitter='" + twitter + '\'' +
                ", website='" + website + '\'' +
                ", bio='" + bio + '\'' +
                ", tagline='" + tagline + '\'' +
                ", github='" + github + '\'' +
                ", created_at='" + created_at + '\'' +
                ", email='" + email + '\'' +
                ", topics_count=" + topics_count +
                ", replies_count=" + replies_count +
                ", following_count=" + following_count +
                ", followers_count=" + followers_count +
                ", favorites_count=" + favorites_count +
                ", level='" + level + '\'' +
                ", level_name='" + level_name + '\'' +
                '}';
    }
}