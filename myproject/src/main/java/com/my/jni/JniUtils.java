package com.my.jni;

/**
 * Created by hexiaoning on 2016/11/7.
 */
public class JniUtils {
    public static native String getStringFromC();

    static {
        //System.loadLibrary("NdkJniDemo");
    }
}
