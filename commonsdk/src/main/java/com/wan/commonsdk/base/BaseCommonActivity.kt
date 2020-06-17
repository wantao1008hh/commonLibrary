package com.wan.commonsdk.base

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import com.android.tu.loadingdialog.LoadingDailog
import com.jess.arms.base.BaseActivity
import es.dmoral.toasty.Toasty


abstract class BaseCommonActivity<P : BasePresenterImpl<*, *>> : BaseActivity<P>(), BaseView {

    var loadingDialog: LoadingDailog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun isActive(): Boolean {
        return !isFinishing && !supportFragmentManager.isDestroyed
    }

    override fun getContext(): Context {
        return this
    }

    override fun showMessage(message: String) {
        Toasty.info(this, message).show()
    }

    override fun killMyself() {
        finish()
    }

    override fun showLoading() {
        if (loadingDialog==null) {
            val loadBuilder = LoadingDailog.Builder(this)
                .setMessage("加载中...")
                .setCancelable(true)
                .setCancelOutside(true)
             loadingDialog = loadBuilder.create()
        }
        loadingDialog?.show()
    }

    override fun hideLoading() {
        loadingDialog?.dismiss()
    }

}
