package com.wenjie.kotlin.mvp.presenter

import android.text.TextUtils
import com.wenjie.kotlin.base.BaseMvpPresenter
import com.wenjie.kotlin.entity.Token
import com.wenjie.kotlin.entity.TopicsAndNews
import com.wenjie.kotlin.entity.UserDetail
import com.wenjie.kotlin.mvp.model.ILoginModel
import com.wenjie.kotlin.mvp.model.OnLoadDatasListener
import com.wenjie.kotlin.mvp.model.impl.LoginModel
import com.wenjie.kotlin.mvp.view.ILoginView

/**
 * ProjectName: MvpKotlin
 * PackageNmae: com.wenjie.kotlin.mvp.presenter
 * Author: wenjie
 * FileName: com.wenjie.kotlin.mvp.presenter.LoginPresenter.java
 * Date: 2018-05-03 10:41
 * Description:
 */
class LoginPresenter : BaseMvpPresenter<ILoginView>() {

    private var loginModel: ILoginModel? = null

    init {
        loginModel = LoginModel()
    }


    fun login() {
        if (mView == null) return
        if (TextUtils.isEmpty(mView!!.getUserName()) || TextUtils.isEmpty(mView!!.getPassword())) {
            mView!!.showMsg("用户名或密码不能为空")
            return
        }

        mView!!.getDialog().show()
        loginModel!!.login(
                "",
                "",
                "password",
                mView!!.getUserName(),
                mView!!.getPassword(),
                object : OnLoadDatasListener<Token> {
                    override fun onSuccess(t: Token) {
                        mView!!.getDialog().dismiss()
                        mView!!.setText(t.access_token!!)
                    }

                    override fun onFailure(msg: String) {
                        mView!!.getDialog().dismiss()
                        mView!!.showMsg(msg)
                    }

                }
        )
    }

    fun getMe() {
        mView!!.getDialog().show()
        loginModel!!.getMe("", "", "password", mView!!.getUserName(), mView!!.getPassword(), object : OnLoadDatasListener<UserDetail> {
            override fun onSuccess(t: UserDetail) {
                mView!!.getDialog().dismiss()
                mView!!.setUserDetail(t)
            }

            override fun onFailure(msg: String) {
                mView!!.getDialog().dismiss()
                mView!!.showMsg(msg)
            }
        })
    }

    fun hebing(){
        mView!!.getDialog().show()
        loginModel!!.bebing(object  : OnLoadDatasListener<TopicsAndNews>{
            override fun onFailure(msg: String) {
                mView!!.getDialog().dismiss()
                mView!!.showMsg(msg)
            }

            override fun onSuccess(t: TopicsAndNews) {
                mView!!.getDialog().dismiss()
                mView!!.setBebingData(t)
            }

        })
    }

}