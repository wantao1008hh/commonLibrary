package com.wan.commonsdk.rx;


import com.wan.commonsdk.base.BaseView;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * 网络结果处理类, 此类会判断网络错误与业务错误.
 */
public class RespHandler<T> {
    private BaseView view;
    private CustomHandler<T> handler;
//    private final String SUCCESS = "8003";

    public RespHandler(CustomHandler<T> handler) {
        this.handler = handler;
    }

    public RespHandler(CustomHandler<T> handler, BaseView view) {
        this.handler = handler;
        this.view = view;
    }

    public void onCompleted() {
        release();
    }

    public void onError(Throwable e) {
        if (handler != null && !handler.error(e)) {
            handleException(e);
        }
        release();
    }

    public void onNext(T t) {
//        BaseData data;
//        if (t instanceof BaseData) {
//            data = (BaseData) t;
//            if (SUCCESS.equals(data.status)) {
//                handleSuccess(t);
//            } else {
//                if (handler != null && !handler.operationError(t, data.status, data.message)) {
//                    if (data.res_data instanceof String) {
//                        handleOperationError((String) data.res_data);
//                    } else {
//                        handleOperationError(data.message);
//                    }
//                }
//            }
//        } else {
//            handleSuccess(t);
//        }
        handleSuccess(t);
        release();
    }


    private void handleSuccess(T t) {
        try {
            handler.success(t);
        } catch (Exception e) {
            onError(e);
        }
    }

    public void release() {
        view = null;
        handler = null;
    }

    public void handleException(Throwable e) {
        e.printStackTrace();
        if (view != null) {
            if (e instanceof HttpException) {
                HttpException httpException = (HttpException) e;
                ResponseBody responseBody = httpException.response().errorBody();
                if (responseBody != null) {
                    try {
                        String json = responseBody.string();
                        view.showMessage(json);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    view.showMessage(e.getMessage());
                }
            }
        }
    }

    public void handleOperationError(String message) {
        if (view != null) {
            view.showMessage(message);
        }
    }

    public interface CustomHandler<T> {
        /**
         * 请求成功同时业务成功的情况下会调用此函数
         */
        void success(T t);

        /**
         * 请求成功但业务失败的情况下会调用此函数.
         *
         * @return 是否需要自行处理业务错误.
         * <p>
         * true - 需要, 父类不会处理错误
         * </P>
         * false - 不需要, 交由父类处理
         */
        boolean operationError(T t, String status, String message);

        /**
         * 请求失败的情况下会调用此函数
         *
         * @return 是否需要自行处理系统错误.
         * <p>
         * true - 需要, 父类不会处理错误
         * </P>
         * false - 不需要, 交由父类处理
         */
        boolean error(Throwable e);

        /**
         * 是否展示异常对话框（主要是账号异常、或TOKEN为空）
         *
         * @return 需要自行处理结果
         * true 展示
         * false 不展示、重写此方法一般还需要重写operationError()在这里做业务处理
         * 默认展示
         * @Params 是否是账号异常
         */
        boolean isShowExceptionDialog(String status, String message);
    }
}
