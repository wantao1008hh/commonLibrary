package com.wan.commonsdk.rx;

import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import me.jessyan.rxerrorhandler.handler.RetryWithDelayOfFlowable;


/**
 * RxJava线程切换类
 */
public class SchedulersTransformer {

    public static <T> ObservableTransformer<T, T> ioUi(int repeat) {
        return upstream -> upstream
                .retryWhen(new RetryWithDelay(repeat, 1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<T, T> ioUi() {
        return upstream -> upstream
                .retryWhen(new RetryWithDelay(3, 1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<T, T> ioUiNotRetry() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static <T> FlowableTransformer<T, T> flowableIoUi() {
        return upstream -> upstream
                .retryWhen(new RetryWithDelayOfFlowable(3, 1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
