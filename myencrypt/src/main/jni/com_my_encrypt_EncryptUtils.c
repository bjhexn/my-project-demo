#include "com_my_encrypt_EncryptUtils.h"
#include <string.h>
/*
 * Class:     Java_com_wobiancao_ndkjnidemo_ndk_JniUtils
 * Method:    getStringFormC
 * Signature: ()Ljava/lang/String;
 */

const char keyValue[] = {
        11, 11, 21, -45, 25, 98, -55, -45, 10, 35, -45, 35,
        26, -5, 25, -65, -78, -13, 85, 45, -5, 10, -0, 11,
        -35, -48, -98, 65, -11, 11, -67, 25, 36, -56, -45, -5,
        12, 15, 35, -15, 25, -14, 62, -25, 33, -45, 55, 12, -8
};

const char iv[] =  {    //16 bit
        -33, 32, -26, 25, 35, -27, 55, -13, -15,32,
        23, 45, -26, 32, 5,12
};


jbyteArray Java_com_my_encrypt_EncryptUtils_getKeyValue(JNIEnv *env, jobject obj)
{

  jbyteArray kvArray = (*env)->NewByteArray(env, sizeof(keyValue));
  jbyte *bytes = (*env)->GetByteArrayElements(env,kvArray,0);

  int i;
  for (i = 0; i < sizeof(keyValue);i++){
    bytes[i] = (jbyte)keyValue[i];
  }

  (*env)->SetByteArrayRegion(env,kvArray, 0, sizeof(keyValue),bytes);
  (*env)->ReleaseByteArrayElements(env,kvArray,bytes,0);

  return kvArray;
}

//JNIEXPORT JNICALL
jbyteArray Java_com_my_encrypt_EncryptUtils_getIv(JNIEnv *env, jobject obj)
{

  jbyteArray ivArray = (*env)->NewByteArray(env, sizeof(iv));
  jbyte *bytes = (*env)->GetByteArrayElements(env,ivArray, 0);

  int i;
  for (i = 0; i < sizeof(iv); i++){
    bytes[i] = (jbyte)iv[i];
  }

  (*env)->SetByteArrayRegion(env,ivArray, 0, sizeof(iv), bytes);
  (*env)->ReleaseByteArrayElements(env,ivArray,bytes,0);

  return ivArray;
}

