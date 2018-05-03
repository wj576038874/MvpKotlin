package com.wenjie.kotlin.base

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

/**
 * ProjectName: MvpKotlin
 * PackageNmae: com.wenjie.kotlin.base
 * Author: wenjie
 * FileName: com.wenjie.kotlin.base.BaseMvpActivity.java
 * Date: 2018-05-03 10:20
 * Description:
 */
abstract class BaseMvpActivity<V : IBaseMvpView, P : BaseMvpPresenter<V>> : AppCompatActivity(), IBaseMvpView {

    protected var mPresenter: P? = null

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when (mPresenter) {
            null -> mPresenter = createPresenter()
        }
        mPresenter!!.attachMvpView(this@BaseMvpActivity as V)
    }

    protected abstract fun createPresenter(): P

    override fun onDestroy() {
        super.onDestroy()
        mPresenter!!.detachMvpView()
    }
}