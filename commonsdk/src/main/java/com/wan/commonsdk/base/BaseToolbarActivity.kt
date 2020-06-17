package com.wan.commonsdk.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.wan.commonsdk.R
import kotlinx.android.synthetic.main.common_activity_base_toolbar.*

abstract class BaseToolbarActivity<P : BasePresenterImpl<*, *>> : BaseCommonActivity<P>() {
    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.common_activity_base_toolbar
    }

    override fun initData(savedInstanceState: Bundle?) {
        if (showToolbar()) {
            initToolBar(toolBar, ivBack, toolBarTv, flRight)
        } else {
            toolBar.visibility = View.GONE
        }
        fl_container.addView(LayoutInflater.from(this).inflate(getContentView(), null))
    }

    open fun initToolBar(ivBack: ImageView, toolBarTv: TextView, flRight: FrameLayout) {
        toolBar.setOnClickListener {
            finish()
        }
    }

    open fun initToolBar(
        toolBar: RelativeLayout,
        ivBack: ImageView,
        toolBarTv: TextView,
        flRight: FrameLayout
    ) {
        initToolBar(ivBack, toolBarTv, flRight)
    }

    abstract fun getContentView(): Int

    open fun showToolbar(): Boolean {
        return true
    }

}