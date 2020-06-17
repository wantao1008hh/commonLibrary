package com.wan.commonsdk.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jess.arms.utils.ArmsUtils;
import com.wan.commonsdk.R;

import me.jessyan.autosize.AutoSizeCompat;

/**
 * 通用弹窗
 */
public class RoundWhiteDialog extends Dialog {

    private TextView tvTitle;
    private FrameLayout flContainer;
    private OnCloseListener onCloseListener;

    public RoundWhiteDialog(@NonNull Context context) {
        super(context, R.style.Common_ShareDialog);
        init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        init();
    }

    private void init() {
        setContentView(R.layout.common_dialog_round_white);
        tvTitle = findViewById(R.id.tv_title);
        ImageView ivClose = findViewById(R.id.iv_close);
        ivClose.setOnClickListener(v -> {
            dismiss();
            if (onCloseListener != null) {
                onCloseListener.onClose();
            }
        });
        flContainer = findViewById(R.id.fl_container);
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(android.R.color.transparent);
        }

        //去除部分手机顶部蓝线
        int divierId = getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
        View divider = findViewById(divierId);
        if (divider != null) {
            divider.setBackgroundColor(Color.TRANSPARENT);
        }
    }


    @Override
    public void setTitle(CharSequence title) {
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        } else {
            tvTitle.setVisibility(View.GONE);
        }
    }

    public void setContent(View view) {
        flContainer.removeAllViews();
        flContainer.addView(view);
    }

    public void setMessage(String msg) {
        TextView textView = new TextView(getContext());
        textView.setText(msg);
        textView.setTextSize(15);
        textView.setTextColor(getContext().getResources().getColor(R.color.common_black));
        textView.setGravity(Gravity.CENTER);
        flContainer.removeAllViews();
        flContainer.addView(textView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public void setOnCloseListener(OnCloseListener onCloseListener) {
        this.onCloseListener = onCloseListener;
    }

    public static class Builder {

        private Activity activity;
        private String title;
        private View content;
        private String message;
        private boolean canceledOnTouchOutside = true;
        private OnCloseListener onCloseListener;
        private OnCreateListener onCreateListener;
        private int width = WindowManager.LayoutParams.WRAP_CONTENT, height = WindowManager.LayoutParams.WRAP_CONTENT;
        private boolean focus;

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContent(int layout) {
            View view = LayoutInflater.from(activity).inflate(layout, null);
            return setContent(view);
        }

        /**
         * 设置宽度
         *
         * @param width 单位dp
         * @return 对象
         */
        public Builder setWidth(int width) {
            this.width = ArmsUtils.dip2px(activity, width);
            return this;
        }

        /**
         * 设置高度
         *
         * @param height 单位dp
         * @return 对象
         */
        public Builder setHeight(int height) {
            this.height = ArmsUtils.dip2px(activity, height);
            return this;
        }

        public Builder setOnCreateListener(OnCreateListener onCreateListener) {
            this.onCreateListener = onCreateListener;
            return this;
        }

        public Builder setContent(View content) {
            this.content = content;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.canceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setfocus(boolean focus) {
            this.focus = focus;
            return this;
        }

        public Builder setOnCloseListener(OnCloseListener onCloseListener) {
            this.onCloseListener = onCloseListener;
            return this;
        }

        public RoundWhiteDialog build() {
            RoundWhiteDialog roundWhiteDialog = new RoundWhiteDialog(activity);
            roundWhiteDialog.setTitle(title);
            if (!TextUtils.isEmpty(message)) {
                roundWhiteDialog.setMessage(message);
            }
            if (content != null) {
                roundWhiteDialog.setContent(content);
            }
            if (onCreateListener != null) {
                onCreateListener.onCreateContentView(content, roundWhiteDialog);
            }
            roundWhiteDialog.setOnCloseListener(onCloseListener);
            roundWhiteDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
            Window window = roundWhiteDialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.gravity = Gravity.CENTER;
                //宽高可设置具体大小、如果没有调用，则默认是WindowManager.LayoutParams.WRAP_CONTENT
                lp.width = width;
                lp.height = height;
                // Set the dialog to not focusable.
                //加上下面的代码，可以使dialog弹出时不显示toolbar和navigatebar
                if (!focus) {
                    window.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
                }
//                lp.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_IMMERSIVE;
                window.setAttributes(lp);
            }
            return roundWhiteDialog;
        }

        public void show() {
            build().show();
        }


    }

    public interface OnCloseListener {
        void onClose();
    }

    public interface OnCreateListener {
        void onCreateContentView(@Nullable View contentView, Dialog dialog);
    }
}
