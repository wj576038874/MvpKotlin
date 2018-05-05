package com.wenjie.kotlin.utils

import android.app.Activity
import android.view.View
import android.widget.Toast
import com.wenjie.kotlin.base.MvpKotlinApplication

/**
 *  ProjectName：MvpKotlin-master
 *  PackageName：com.wenjie.kotlin.utils
 *  Author：wenjie
 *  Date：2018-05-05 18:41
 *  Description：扩展函数
 */

/**
 * Toast
 * @param message 内容
 * @param time 时长，默认短时间
 */
fun toast(message: String, time: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(MvpKotlinApplication.Companion.instence, message, time).show()
}

/**
 * findViewById
 * @param resId 资源id
 */
fun <T : View> View.find(resId: Int): T {
    return this.findViewById(resId)
}

/**
 * findViewById
 * @param resId 资源id
 */
fun <T : View> Activity.find(resId: Int): T {
    return this.findViewById(resId)
}
