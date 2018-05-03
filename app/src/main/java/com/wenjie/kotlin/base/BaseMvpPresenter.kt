package com.wenjie.kotlin.base

import java.lang.ref.WeakReference

/**
 * ProjectName: MvpKotlin
 * PackageNmae: com.wenjie.kotlin.base
 * Author: wenjie
 * FileName: com.wenjie.kotlin.base.BaseMvpPresenter.java
 * Date: 2018-05-03 10:17
 * Description:
 */
open class BaseMvpPresenter<V : IBaseMvpView> {
    protected var mView: V? = null

    private var weakReferenceView: WeakReference<V>? = null

    public fun attachMvpView(view: V) {
        weakReferenceView = WeakReference(view)
        this.mView = weakReferenceView!!.get()
    }

    public fun detachMvpView() {
        weakReferenceView!!.clear()
        weakReferenceView = null
        mView = null
    }
}