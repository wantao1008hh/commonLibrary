package com.wan.commonsdk.base

import android.app.Activity
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.mvp.IModel
import com.wan.commonsdk.rx.DoOnXTransformer
import io.reactivex.ObservableTransformer

abstract class BasePresenterImpl<M : IModel, V : BaseView>(model: M, rootView: V) :
    BasePresenter<M, V>(model, rootView) {

    fun isActive(): Boolean {
        return mRootView != null && mRootView.isActive()
    }

    fun getActivity(): Activity {
        return mRootView as Activity
    }

    override fun useEventBus(): Boolean {
        return false
    }

}