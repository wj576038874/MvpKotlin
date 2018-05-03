package com.wenjie.kotlin.entity

import java.io.Serializable

/**
 * ProjectName: MvpKotlin
 * PackageNmae: com.wenjie.kotlin.entity
 * Author: wenjie
 * FileName: com.wenjie.kotlin.entity.New.java
 * Date: 2018-05-03 13:02
 * Description:
 */
class New : Serializable {
    var id: Int = 0                         // 唯一 id
    var title: String? = null                   // 标题
    var created_at: String? = null              // 创建时间
    var updated_at: String? = null              // 更新时间
    var user: User? = null                      // 创建该话题的用户(信息)
    var node_name: String? = null               // 节点名称
    var node_id: Int = 0                    // 节点 id
    var last_reply_user_id: Int = 0         // 最近一次回复的用户 id
    var last_reply_user_login: String? = null   // 最近一次回复的用户登录名
    var replied_at: String? = null              // 最近一次回复时间
    var replies_count: Int = 0              // 回复总数量
    var address: String? = null                 // 具体地址


    override fun toString(): String {
        return "New{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", user=" + user +
                ", node_name='" + node_name + '\'' +
                ", node_id=" + node_id +
                ", last_reply_user_id=" + last_reply_user_id +
                ", last_reply_user_login='" + last_reply_user_login + '\'' +
                ", replied_at='" + replied_at + '\'' +
                ", replies_count=" + replies_count +
                ", address='" + address + '\'' +
                '}'
    }
}