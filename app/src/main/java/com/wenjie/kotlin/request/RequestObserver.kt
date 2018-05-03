package com.wenjie.kotlin.request

import android.text.TextUtils
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.net.ConnectException
import java.net.SocketTimeoutException

/**
 * ProjectName: MvpKotlin
 * PackageNmae: com.wenjie.kotlin.request
 * Author: wenjie
 * FileName: com.wenjie.kotlin.request.RequestObserver.java
 * Date: 2018-05-03 10:08
 * Description:
 */
abstract class RequestObserver<T> : Observer<T> {

    /**
     * 定义一个请求成功的抽象方法 子类必须实现并在实现中进行处理服务器返回的数据
     *
     * @param t 服务器返回的数据
     */
    protected abstract fun onSuccess(t: T)

    /**
     * 定义一个请求失败的抽象方法 子类必须实现并在实现中进行服务器返回数据的处理
     *
     * @param msg 服务器返回的错误信息
     */
    protected abstract fun onFailure(msg: String)

    override fun onNext(t: T) {
        /*
         * 请求成功将数据发出去
         */
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        val msg: String = when (e) {
            is SocketTimeoutException -> "请求超时。请稍后重试！"
            is ConnectException -> "请求超时。请稍后重试！"
            else -> "请求未能成功，请稍后重试！"
        }
        if (!TextUtils.isEmpty(msg)) {
            onFailure(msg)
        }
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onComplete() {
    }

}