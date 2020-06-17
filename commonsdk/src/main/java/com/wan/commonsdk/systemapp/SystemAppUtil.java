package com.wan.commonsdk.systemapp;

/*
 * author : 万涛
 * date : 2020/6/17 10:33
 */

import android.app.Activity;
import android.content.pm.ApplicationInfo;


import java.io.DataOutputStream;

public class SystemAppUtil {

    /**
     * 1. 输入 exit 退出android系统终端
     *
     * 2. 解压你的apk文件，进入查看lib/armeabi文件夹下有没有 .so文件，如果没有这种库文件的话，直接跳到第10步，（因为有些apk文件是要调用动态链接库的，你不拷贝的话，就没有办法运行！会报错）如果有的话， 将这些*.so文件都拷贝到/system/lib文件夹下：
     *
     * 命令：adb push libiReader_txtparser.so system/lib
     * @param activity
     * @return
     */
    public static boolean installMySelf(Activity activity) {
        boolean isSystemApp = isSystemApp(activity);
        if (!isSystemApp) {
            String appPath = activity.getPackageCodePath();
            String cmd1 = "mount -o rw,remount /system";
            String cmd2 = "chmod 777 /system/app";
            String cmd4 = "cp " + appPath + " /system/app/launch_.apk";
            String cmd5 = "chmod 777 /system/app/launch_.apk";
            String cmd6 = "reboot";
            CommandResult result1 = ShellUtils.execCommand(cmd1, true, true);
            CommandResult result2 = ShellUtils.execCommand(cmd2, true, true);
//            CommandResult result3 = ShellUtils.execCommand(cmd3, true, true);
            CommandResult result4 = ShellUtils.execCommand(cmd4, true, true);
            CommandResult result5 = ShellUtils.execCommand(cmd5, true, true);
            CommandResult result6 = ShellUtils.execCommand(cmd6, true, true);
            if (result5.errorMsg != null) {
            }
        }
        return isSystemApp;
    }

    public static boolean isSystemApp(Activity activity) {
        ApplicationInfo applicationInfo = activity.getApplicationInfo();
        return (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
    }

    /**
     * 应用程序运行命令获取 Root权限，设备必须已破解(获得ROOT权限)
     *
     * @return 应用程序是/否获取Root权限
     */
    public static boolean upgradeRootPermission(String pkgCodePath) {
        Process process = null;
        DataOutputStream os = null;
        try {
            String cmd = "chmod 777 " + pkgCodePath;
            process = Runtime.getRuntime().exec("su"); //切换到root帐号
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(cmd + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
            }
        }
        return true;
    }
}
