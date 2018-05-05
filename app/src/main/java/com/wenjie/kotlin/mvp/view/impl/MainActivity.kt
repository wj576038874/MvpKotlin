package com.wenjie.kotlin.mvp.view.impl

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.wenjie.kotlin.R
import com.wenjie.kotlin.base.BaseMvpActivity
import com.wenjie.kotlin.entity.TopicsAndNews
import com.wenjie.kotlin.entity.UserDetail
import com.wenjie.kotlin.mvp.presenter.LoginPresenter
import com.wenjie.kotlin.mvp.view.ILoginView
import com.wenjie.kotlin.utils.DialogUtils
import com.wenjie.kotlin.utils.find
import com.wenjie.kotlin.utils.toast

class MainActivity : BaseMvpActivity<ILoginView, LoginPresenter>(), ILoginView, View.OnClickListener {

    private lateinit var dialog: Dialog
    private lateinit var etUserName: EditText
    private lateinit var etPassword: EditText
    private lateinit var textView: TextView
    private lateinit var btnLogin: Button
    private lateinit var btnGetMe: Button
    private lateinit var tvUserdetail: TextView
    private lateinit var tvhebing1: TextView
    private lateinit var tvhebing2: TextView
    private lateinit var btnHebing: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dialog = DialogUtils.createLoadingDialog(this@MainActivity, "加载中...")
        etUserName = find(R.id.username)
        etPassword = find(R.id.password)
        textView = find(R.id.result)
        btnLogin = find(R.id.login)
        tvUserdetail = find(R.id.userdetail)
        btnGetMe = find(R.id.getme)
        tvhebing1 = find(R.id.tv_hebing1)
        tvhebing2 = find(R.id.tv_hebing2)
        btnHebing = find(R.id.btn_hebing)

        btnGetMe.setOnClickListener(this@MainActivity)
        btnLogin.setOnClickListener(this@MainActivity)
        btnHebing.setOnClickListener(this@MainActivity)
    }

    override fun getDialog(): Dialog {
        return dialog
    }

    override fun getUserName(): String {
        return etUserName.text.toString()
    }

    override fun getPassword(): String {
        return etPassword.text.toString()
    }

    override fun showMsg(msg: String) {
        toast(msg)
    }

    override fun setText(result: String) {
        textView.text = result
    }

    override fun createPresenter(): LoginPresenter {
        return LoginPresenter()
    }

    override fun setUserDetail(userDetail: UserDetail) {
        tvUserdetail.text = userDetail.toString()
    }

    @SuppressLint("SetTextI18n")
    override fun setBebingData(topicsAndNews: TopicsAndNews) {
        tvhebing1.text = "第一个请求${topicsAndNews.news!![0]}"
        tvhebing2.text = "第二个请求${topicsAndNews.topics!![0]}"
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.login -> mPresenter!!.login()
            R.id.getme -> mPresenter!!.getMe()
            R.id.btn_hebing -> mPresenter!!.hebing()
        }
    }
}
