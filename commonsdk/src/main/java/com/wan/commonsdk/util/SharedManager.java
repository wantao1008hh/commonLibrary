package com.wan.commonsdk.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedManager {
    public static final String SHARE_NAME = "inventory";
    private static SharedPreferences sharedPreferences;

    /**
     * 使用之前初始化, 可在Application中调用
     *
     * @param context 请传入ApplicationContext避免内存泄漏
     */
    public static void init(Context context) {
        if (sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 获取时钟
     *
     * @param time
     */
    public static void setTime(String time) {
        sharedPreferences.edit().putString("time", time).apply();
    }

    public static String getTime() {
        return sharedPreferences.getString("time", "");
    }


    /**
     * 获取token
     *
     * @param token
     */
    public static void setToken(String token) {
        sharedPreferences.edit().putString("token", token).apply();
    }

    public static String getToken() {
        return sharedPreferences.getString("token", "");
    }


    /**
     * 设备号
     * @param deviceNo
     */
    public static void setDeviceNo(String deviceNo) {
        sharedPreferences.edit().putString("deviceNo", deviceNo).apply();
    }

    public static String getDeviceNo() {
        return sharedPreferences.getString("deviceNo", "");
    }

    /**
     * 设备ID
     * @param deviceId
     */
    public static void setDeviceId(String deviceId) {
        sharedPreferences.edit().putString("deviceId", deviceId).apply();
    }

    public static String getDeviceId() {
        return sharedPreferences.getString("deviceId", "");
    }

    /**
     * 推送
     * @param channelId
     */
    public static void setChannelId(String channelId) {
        sharedPreferences.edit().putString("channelId", channelId).apply();
    }

    public static String getChannelId() {
        return sharedPreferences.getString("channelId", "");
    }

    /**
     * 设备信息
     * @param deviceInfo
     */
    public static void setDeviceInfo(String  deviceInfo) {
        sharedPreferences.edit().putString("deviceInfo", deviceInfo).apply();
    }

    public static String getDeviceInfo() {
        return sharedPreferences.getString("deviceInfo", "");
    }

    /**
     * 投口颜色
     */
    public static void setColorInfo(String  colorInfo) {
        sharedPreferences.edit().putString("colorInfo", colorInfo).apply();
    }

    public static String getColorInfo() {
        return sharedPreferences.getString("colorInfo", "");
    }
}
