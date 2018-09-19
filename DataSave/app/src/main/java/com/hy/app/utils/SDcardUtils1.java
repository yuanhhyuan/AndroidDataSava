package com.hy.app.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import com.hy.app.base.AppConstant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class SDcardUtils1 {

    /**
     * 将/data/data/APK/zxcg.db  数据库拷贝到外部存储
     * */
    public static void corydbtoExternal(Context mcontext) {
        //找到文件的路径  /data/data/包名/databases/数据库名称
        File dbFile = new File(Environment.getDataDirectory().getAbsolutePath()+"/data/"+mcontext.getPackageName()+"/databases/zxcg1.db");
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //文件复制到sd卡中
            fis = new FileInputStream(dbFile);

            String str = Environment.getExternalStorageDirectory().getAbsolutePath()+"/copy.db";
            LogUtil.e("060_","corydbtoExternal str : "+str);
            if(str != null){
                removeFileFromSDCard(str);
            }

            fos = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath()+"/copy.db");
            int len = 0;
            byte[] buffer = new byte[2048];
            while(-1!=(len=fis.read(buffer))){
                fos.write(buffer, 0, len);
            }
            fos.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //关闭数据流
            try{
                if(fos!=null)fos.close();
                if (fis!=null)fis.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }


    /**
     * 将/data/data/APK/.xml  SharedPreferences xml 拷贝到外部存储
     * */
    public static void coryxmltoExternal(Context mcontext) {
        //找到文件的路径  /data/data/包名/databases/数据库名称
        File dbFile = new File(Environment.getDataDirectory().getAbsolutePath()+"/data/"+mcontext.getPackageName()+"/databases/" + AppConstant.SP_NAME);
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //文件复制到sd卡中
            fis = new FileInputStream(dbFile);

            String str = Environment.getExternalStorageDirectory().getAbsolutePath() + AppConstant.SP_NAME;
            if(str != null){
                removeFileFromSDCard(str);
            }

            fos = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + AppConstant.SP_NAME);
            int len = 0;
            byte[] buffer = new byte[2048];
            while(-1!=(len=fis.read(buffer))){
                fos.write(buffer, 0, len);
            }
            fos.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //关闭数据流
            try{
                if(fos!=null)fos.close();
                if (fis!=null)fis.close();
            }catch(IOException e){
                e.printStackTrace();
            }

        }
    }

    // 判断文件是否存在
    public static boolean isUpgradeFileExist(String zipFilePath) {
        boolean result = false;
        if (isExistSDCard()) {
            //"/mnt/sdcard/hcn_mcu_update.zip"
            File file = new File(zipFilePath);
            if (file != null) {
                if (file.exists()) {
                    result = true;
                }
            }
        }
        return result;
    }

    //删除文件
    private static void delete(File file) {
        if (file.isFile()) {
            file.delete();
            Log.e("file", "delete" + file.getAbsolutePath());
            return;
        }

        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
                return;
            }

            for (int i = 0; i < childFiles.length; i++) {
                delete(childFiles[i]);
            }
            file.delete();
        }
    }

    // 从sdcard中删除文件
    public static boolean removeFileFromSDCard(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            try {
                file.delete();
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * SD卡剩余空间
     */
    public static long getSDFreeSize() {
        // 取得SD卡文件路径
        File path = Environment.getExternalStorageDirectory();
        StatFs sf = new StatFs(path.getPath());
        // 获取单个数据块的大小(Byte)
        long blockSize = sf.getBlockSize();
        // 空闲的数据块的数量
        long freeBlocks = sf.getAvailableBlocks();
        // 返回SD卡空闲大小
        // return freeBlocks * blockSize; //单位Byte
        // return (freeBlocks * blockSize)/1024; //单位KB
        return (freeBlocks * blockSize) / 1024 / 1024; // 单位MB
    }

    /**
     * SD卡总容量
     */
    public static long getSDAllSize() {
        // 取得SD卡文件路径
        File path = Environment.getExternalStorageDirectory();
        StatFs sf = new StatFs(path.getPath());
        // 获取单个数据块的大小(Byte)
        long blockSize = sf.getBlockSize();
        // 获取所有数据块数
        long allBlocks = sf.getBlockCount();
        // 返回SD卡大小
        // return allBlocks * blockSize; //单位Byte
        // return (allBlocks * blockSize)/1024; //单位KB
        return (allBlocks * blockSize) / 1024 / 1024; // 单位MB
    }

    /**
     * 获取指定文件大小
     */
    public static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
            Log.e("获取文件大小", "文件不存在!");
        }
        return size / 1024 / 1024;
    }

    // 判断SD卡是否存在
    public static boolean isExistSDCard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
}
