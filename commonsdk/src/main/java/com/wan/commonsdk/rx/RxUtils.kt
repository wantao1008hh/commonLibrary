package com.wan.commonsdk.rx

import com.jess.arms.utils.RxLifecycleUtils
import com.wan.commonsdk.base.BaseView
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RxUtils
{
    /**
     * 为网络请求绑定默认的加载前显示loading，加载完成后消失
     */
     fun <T> getLoadingTransformer(mRootView:BaseView): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.compose<T>(DoOnXTransformer.doOnSubscribe(Runnable { mRootView.showLoading() }))
                    .compose(
                            DoOnXTransformer.doOnFinish(
                                    Runnable {
                                        mRootView.hideLoading()
                                    })
                    )
        }
    }

    /**
     * 线程切换，并绑定生命周期
     */
    fun <T> getThreadTransformer(mRootView:BaseView): ObservableTransformer<T, T>? {
        return ObservableTransformer { upstream: Observable<T> ->
            upstream
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    //绑定生命周期
                    .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
        }
    }

}