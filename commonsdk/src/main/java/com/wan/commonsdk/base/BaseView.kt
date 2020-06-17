package com.wan.commonsdk.base

import android.content.Context
import com.jess.arms.mvp.IView

interface BaseView : IView {
    fun isActive(): Boolean

    fun getContext(): Context
}