package com.wenjie.kotlin.mvp.view

import android.app.Dialog
import com.wenjie.kotlin.base.IBaseMvpView
import com.wenjie.kotlin.entity.TopicsAndNews
import com.wenjie.kotlin.entity.UserDetail

/**
 * ProjectName: MvpKotlin
 * PackageNmae: com.wenjie.kotlin.mvp.view
 * Author: wenjie
 * FileName: com.wenjie.kotlin.mvp.view.ILoginView.java
 * Date: 2018-05-03 10:37
 * Description:
 */
interface ILoginView : IBaseMvpView {

    fun getDialog(): Dialog

    fun getUserName(): String

    fun getPassword(): String

    fun showMsg(msg: String)

    fun setText(result: String)

    /**
     * 设置用户信息
     */
    fun setUserDetail(userDetail: UserDetail)

    fun setBebingData(topicsAndNews: TopicsAndNews)
}