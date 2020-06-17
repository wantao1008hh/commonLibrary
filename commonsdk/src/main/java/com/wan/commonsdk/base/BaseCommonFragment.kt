package com.wan.commonsdk.base

import android.content.Context
import android.os.Bundle
import com.jess.arms.base.BaseFragment
import es.dmoral.toasty.Toasty

abstract class BaseCommonFragment<P : BasePresenterImpl<*, *>> : BaseFragment<P>(), BaseView {

    override fun getContext(): Context {
        return mContext
    }

    override fun isActive(): Boolean {
        return isAdded
    }

    override fun showMessage(message: String) {
        Toasty.info(context, message).show()
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun setData(data: Any?) {
    }

    override fun killMyself() {
        if (isActive())
            activity?.finish()
    }
}