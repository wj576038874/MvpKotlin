package com.wenjie.kotlin.mvp.view.impl

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.wenjie.kotlin.R
import com.wenjie.kotlin.base.BaseMvpActivity
import com.wenjie.kotlin.entity.TopicsAndNews
import com.wenjie.kotlin.entity.UserDetail
import com.wenjie.kotlin.mvp.presenter.LoginPresenter
import com.wenjie.kotlin.mvp.view.ILoginView
import com.wenjie.kotlin.utils.DialogUtils

class MainActivity : BaseMvpActivity<ILoginView, LoginPresenter>(), ILoginView, View.OnClickListener {


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.login -> mPresenter!!.login()
            R.id.getme -> mPresenter!!.getMe()
            R.id.btn_hebing -> mPresenter!!.hebing()
        }
    }

    private var dialog: Dialog? = null

    private var etUserName: EditText? = null
    private var etPassword: EditText? = null
    private var textView: TextView? = null
    private var btnLogin: Button? = null
    private var btnGetMe: Button? = null
    private var tvUserdetail: TextView? = null
    private var tvhebing1: TextView? = null
    private var tvhebing2: TextView? = null
    private var btnHebing: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dialog = DialogUtils.createLoadingDialog(this@MainActivity, "加载中...")
        etUserName = findViewById(R.id.username)
        etPassword = findViewById(R.id.password)
        textView = findViewById(R.id.result)
        btnLogin = findViewById(R.id.login)
        tvUserdetail = findViewById(R.id.userdetail)
        btnGetMe = findViewById(R.id.getme)
        tvhebing1 = findViewById(R.id.tv_hebing1)
        tvhebing2 = findViewById(R.id.tv_hebing2)
        btnHebing = findViewById(R.id.btn_hebing)

        btnGetMe!!.setOnClickListener(this@MainActivity)
        btnLogin!!.setOnClickListener(this@MainActivity)
        btnHebing!!.setOnClickListener(this@MainActivity)
    }

    override fun getDialog(): Dialog {
        return dialog!!
    }

    override fun getUserName(): String {
        return etUserName!!.text.toString()
    }

    override fun getPassword(): String {
        return etPassword!!.text.toString()
    }

    override fun showMsg(msg: String) {
        Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun setText(result: String) {
        textView!!.text = result
    }

    override fun createPresenter(): LoginPresenter {
        return LoginPresenter()
    }

    override fun setUserDetail(userDetail: UserDetail) {
        tvUserdetail!!.text = userDetail.toString()
    }

    @SuppressLint("SetTextI18n")
    override fun setBebingData(topicsAndNews: TopicsAndNews) {
        tvhebing1!!.text = "第一个请求${topicsAndNews.news!![0]}"
        tvhebing2!!.text = "第二个请求${topicsAndNews.topics!![0]}"
    }
}
