package com.wenjie.kotlin.mvp.model

/**
 * ProjectName: MvpKotlin
 * PackageNmae: com.wenjie.kotlin.mvp.model
 * Author: wenjie
 * FileName: com.wenjie.kotlin.mvp.model.OnLoadDatasListener.java
 * Date: 2018-05-03 10:03
 * Description:
 */
interface OnLoadDatasListener<T> {

    /**
     * 成功
     * @param t 数据
     */
    fun onSuccess(t: T)

    /**
     * 失败
     * @param msg 错误信息
     */
    fun onFailure(msg: String)
}