package com.wenjie.kotlin.utils

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.wenjie.kotlin.R

/**
 * ProjectName: MvpKotlin
 * PackageNmae: com.wenjie.kotlin.utils
 * Author: wenjie
 * FileName: com.wenjie.kotlin.utils.DialogUtils.java
 * Date: 2018-05-03 13:40
 * Description:
 */
object DialogUtils {

    @SuppressLint("InflateParams")
    fun createLoadingDialog(context: Context, msg: String): Dialog {
        /*
         * 获得view填充器对象
		 */
        val inflater = LayoutInflater.from(context)

        /*
         * 得到加载view
         */
        val v = inflater.inflate(R.layout.loading_dialog, null)

        val tipTextView: TextView = v.find(R.id.tipTextView)
        if (!TextUtils.isEmpty(msg)) tipTextView.text = msg
        val loadingDialog = Dialog(context, R.style.loading_dialog)
        loadingDialog.setCancelable(true)
        loadingDialog.setCanceledOnTouchOutside(false)
        loadingDialog.setContentView(v)

//        val dm = context.resources.displayMetrics
//        val width: Int = dm.widthPixels * 0.3 as
//        val height: Int = ViewGroup.LayoutParams.WRAP_CONTENT
//        loadingDialog.window.setLayout(width, height)

        return loadingDialog
    }

}