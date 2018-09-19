package com.hy.app.utils;

import android.os.Environment;
import android.util.Log;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xtich on 2017/10/18.
 */

public class LogUtil {

    private static final String TAG = "060_";

    //   private static final String basePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "log" + File.separator;
    private static final String basePath = "/mnt/sdcard" + File.separator + "log" + File.separator;
    private static String fileName = null;
    private static boolean isAppend = false;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //Log开关
    private static final boolean DEBUG = true;


//    /**
//     * 单例模式
//     */
//    private LogUtil() {
//    }
//    public static LogUtil getInstance () {
//        return LogUtilHolder.instance;
//    }
//    private static class LogUtilHolder {
//        private static final LogUtil instance = new LogUtil();
//    }
//
    public static void v(String classname, String message) {
        if (DEBUG) {
            String logStr = createMessage(message);
            writeLog2SDCard("v", logStr);
            Log.v(TAG + classname, message);
        }
    }

    public static void d(String classname, String message) {
        if (DEBUG) {
            String logStr = createMessage(message);
            writeLog2SDCard("d", logStr);
            Log.d(TAG + classname, message);
        }
    }

    public static void i(String classname, String message) {
        if (DEBUG) {
            String logStr = createMessage(message);
            writeLog2SDCard("i", logStr);
            Log.i(TAG + classname, message);
        }
    }

    public static void w(String classname, String message) {
        if (DEBUG) {
            String logStr = createMessage(message);
            writeLog2SDCard("w", logStr);
            Log.e(TAG + classname, message);
        }
    }

    public static void e(String classname, String message) {
        if (DEBUG) {
            String logStr = createMessage(message);
            writeLog2SDCard("e", logStr);
            Log.e(TAG + classname, message);
        }
    }

    public static void wtf(String classname,String message) {
        if (DEBUG) {
            String logStr = createMessage(message);
            writeLog2SDCard("wtf", logStr);
            Log.wtf(TAG + classname, message);
        }
    }

    public static void println(String classname,String message) {
        if (DEBUG) {
            String logStr = createMessage(message);
            writeLog2SDCard("println", logStr);
            Log.println(Log.INFO, TAG, message);
        }
    }

    /**
     * 获取有类名与方法名的logString
     *
     * @param rawMessage
     * @return
     */
    private static String createMessage(String rawMessage) {
        /**
         * Throwable().getStackTrace()获取的是程序运行的堆栈信息，也就是程序运行到此处的流程，以及所有方法的信息
         * 这里我们为什么取2呢？0是代表createMessage方法信息，1是代表上一层方法的信息，这里我们
         * 取它是上两层方法的信息
         */
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
        String fullClassName = stackTraceElement.getClassName();
        String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
        return className + "." + stackTraceElement.getMethodName() + "(): " + rawMessage;
    }


    /**
     * 程序每次运行创建一个新的txt，以时间为文件名
     *
     * @param type    log的类型
     * @param context log的内容
     */
    private static void writeLog2SDCard(String type, String context) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return;
        }
        if (fileName == null)
            fileName = sdf.format(new Date(System.currentTimeMillis())) + ".txt";
        File file = new File(basePath + fileName);
//        Log.e("log", file.toString());
        String dateStr = sdf.format(new Date(System.currentTimeMillis()));
        context = dateStr + "==" + type + "==" + context;

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, isAppend)));
            bufferedWriter.write(context);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.close();
            if (!isAppend) {
                isAppend = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
