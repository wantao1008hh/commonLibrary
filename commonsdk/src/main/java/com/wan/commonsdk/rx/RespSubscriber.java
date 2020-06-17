package com.wan.commonsdk.rx;

import com.wan.commonsdk.base.BaseView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by hubert
 * <p>
 * Created on 2017/6/14.
 */

public abstract class RespSubscriber<T> implements RespHandler.CustomHandler<T>, Observer<T> {

    private RespHandler<T> respHandler;

    public RespSubscriber() {
        respHandler = new RespHandler<T>(this);
    }

    public RespSubscriber(BaseView baseView) {
        respHandler = new RespHandler<T>(this, baseView);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {
        respHandler.onCompleted();
        respHandler = null;
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        respHandler.onError(e);
        respHandler = null;
    }

    @Override
    public void onNext(T t) {
        respHandler.onNext(t);
    }

    @Override
    public boolean operationError(T t, String status, String message) {
        return false;
    }

    @Override
    public boolean error(Throwable e) {
        return false;
    }

    @Override
    public boolean isShowExceptionDialog(String status, String message) {
        return true;
    }
}
