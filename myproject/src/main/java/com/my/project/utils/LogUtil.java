package com.my.project.utils;

/**
 * Created by hexiaoning on 2016/8/5.
 */
public class LogUtil {

    public static void Log(String tag, String log) {
        android.util.Log.i(tag, log);
    }

    public static void log(String log) {
        Log("project", log);
    }
}
