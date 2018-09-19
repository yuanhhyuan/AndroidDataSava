package com.hy.base.androidbase.androidbasesave;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.os.StatFs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
外部存储external Storage
 */
public class SDcardUtils {

    /**
     *  是否挂载，Android设备中有SD卡，判断，Android是否能够读取
     * */
    public static boolean isMounted(){
        boolean isMounted = false;

        //      想要判断SD卡是否挂载，需要Environment这个类进行判断

        //      SD卡的状态
        //      获得SD卡状态和Environment这个类中已经预先定义的那个可用状态进行比对
        //      Environment.MEDIA_MOUNTED:说明sd可用（可读可写）
        isMounted = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        return isMounted;
    }

    /**
     *  获得SD卡的总容量，返回单位M
     *  @SuppressLint("NewApi"):添加这个注解，指向最新的API
     * */
    @SuppressLint("NewApi") public static int totalMemory(){
        int count = 0;

        //      这个类携带着SD卡中的信息
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());

        long blockCountLong = statFs.getBlockCountLong();//分区

        long blockSizeLong = statFs.getBlockSizeLong();//每个分区中有多少数据

        count = (int) (blockCountLong*blockSizeLong/1024/1024);

        return count;
    }

    @SuppressLint("NewApi") public static int availableMemory(){
        int count = 0;

        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        long availableCount = statFs.getAvailableBlocksLong();//可用的分区数量

        long blockSizeLong = statFs.getBlockSizeLong();//每个分区中多少数据
        count = (int) (availableCount*blockSizeLong/1024/1024);

        return count;
    }

    /**
     *  获取SD卡中剩余的容量
     * */
    @SuppressLint("NewApi") public static int freeMemory(){
        int count = 0;
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        long freeBlocksLong = statFs.getFreeBlocksLong();//剩余的分区数量
        long blockSizeLong = statFs.getBlockSizeLong();

        count = (int) (freeBlocksLong*blockSizeLong/1024/1024);

        return count;
    }

    /**
     *  存到公有路径下
     * */
    public static void saveToExternalPublic(byte[] data,
                                            String type,String fileName) {
        // TODO Auto-generated method stub
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(Environment.getExternalStoragePublicDirectory(type).getAbsolutePath() //公有路径下的path
                    + File.separator+fileName);
            fos.write(data, 0, data.length);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //  数据保存到SD卡的根目录下，创建文件夹
    public static void saveToExternalRootDirectory(byte[] data, String fileDir,
                                                   String fileName) {
        // TODO Auto-generated method stub
        //      1.创建文件夹
        File dir = new File(Environment.getExternalStorageDirectory(), fileDir);
        if(!dir.exists()){
            dir.mkdirs();
        }
        //      2.创建文件
        File file = new File(dir, fileName);
        //      3.写入数据
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //  外部存储卡私有空间
    public static void saveToExternalPrivateDirectory(byte[] data,
                                                      File externalFilesDir, String fileName) {
        // TODO Auto-generated method stub
        FileOutputStream fos = null;
        try {
            //          1.开流
            fos = new FileOutputStream(new File(externalFilesDir, fileName));
            //          2.写入
            fos.write(data);
            fos.write("\n".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //  获取SD卡中的数据
    public static byte[] getData(String absolutePath, String fileName) {
        // TODO Auto-generated method stub
        byte[] ret = null;
        //      流操作
        //      1.开启InputStream
        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        //      2.进行赋值
        try {
            fis = new FileInputStream(absolutePath+File.separator+fileName);
            baos = new ByteArrayOutputStream();
            int len = 0;
            byte[] buf = new byte[1024*8];
            //          3.将流读到内存流中
            while(true){
                len = fis.read(buf);
                if(len==-1){
                    break;
                }
                baos.write(buf);
            }
            //          4.接收数据，并且返回
            ret = baos.toByteArray();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(baos!=null){
                try {
                    baos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

    public static byte[] getImage(String absolutePath, String fileName) {
        // TODO Auto-generated method stub
        byte[] ret = null;
        FileInputStream  fis = null;
        ByteArrayOutputStream baos = null;
        try {
            fis = new FileInputStream(absolutePath+File.separator+fileName);
            baos = new ByteArrayOutputStream();
            int len = 0;
            byte[] buf = new byte[1024*8];
            while((len = fis.read(buf))!=-1){
                baos.write(buf);
                baos.flush();
            }
            ret = baos.toByteArray();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(baos!=null){
                try {
                    baos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }
}
