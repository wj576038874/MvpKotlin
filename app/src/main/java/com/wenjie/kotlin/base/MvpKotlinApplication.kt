package com.wenjie.kotlin.base

import android.app.Application

/**
 * ProjectName: MvpKotlin
 * PackageNmae: com.wenjie.kotlin.base
 * Author: wenjie
 * FileName: com.wenjie.kotlin.base.MvpKotlinApplication.java
 * Date: 2018-05-03 9:36
 * Description:
 */
class MvpKotlinApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instence = this
    }

    companion object {
        lateinit var instence: MvpKotlinApplication
            private set
    }

}