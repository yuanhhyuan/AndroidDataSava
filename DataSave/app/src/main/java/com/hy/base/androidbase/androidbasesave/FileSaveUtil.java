package com.hy.base.androidbase.androidbasesave;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import static android.content.Context.MODE_PRIVATE;

/**
 * 内部存储Internal Storage
 *  */
public class FileSaveUtil {

    String TAG = "060_FileSaveUtil";

    FileSaveCallback mFileSaveCallback;

    public FileSaveUtil(FileSaveCallback callback){
this.mFileSaveCallback = callback;
    }

    /**
     * @Description: /data/data/** /files目录写数据
     * @param:  调用该方法的Context;要写入的string
     * @return:
     */
    public void fileWrite(Context context,String buf){
//        String filename参数：在/data/data//files目录下存储时的文件名。
//        int mode参数：文件的操作模式，主要有两种模式可选择：MODE_PRIVATE， MODE_APPEND。
        // 默认为MODE_PRIVATE，当指定相同文件名进行读写的时候，新的内容会覆盖原有内容；
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("text", MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(fileOutputStream));
            writer.write("fileWrite...........");   //写入固定的String
            writer.write(buf);           //写入输入的String
            writer.write("\n");          //写入换行符
            writer.flush();
            writer.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description:  /data/data/** /files目录读数据
     * @param:  调用该方法的Context
     * @return:
     */
    public  void fileRead(Context context){
        String line = "";
        try {
            FileInputStream fileInputStream = context.openFileInput("text");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            line = bufferedReader.readLine();
            while (line != null) {
                Log.e(TAG, "fileRead ：" + line);
                //回调的方式
                mFileSaveCallback.onFileRead(line);

                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileInputStream.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @Description: /data/data/ ** /cache 目录写数据
     * @param:
     * @return:
     */
    public void cacheWrite(Context context,String buf){
        File file = new File(context.getCacheDir(), "cache_dir_text");//创建/data/data/<package name>/cache/cache_dir_text文件对象。
        if (!file.exists()) {
            try {
                file.createNewFile();//如果文件不存在，则创建
                FileOutputStream fos = new FileOutputStream(file);
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(fos));
                writer.write("cacheWrite.......");   //写入固定的String
                writer.write(buf);           //写入出入的String
                writer.write("\n");          //写入换行符
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Description: /data/data/ ** /cache 目录读数据
     * @param:
     * @return:
     */
    public void cacheRead(Context context){
        File file = new File(context.getCacheDir(), "cache_dir_text");
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line1 = br.readLine();
            while(line1!=null){
                //回调的方式
                mFileSaveCallback.oncacheRead(line1);

                Log.e(TAG, " " + line1);
                line1 = br.readLine();
            }
            br.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 外部存储写数据
     * @param:
     * @return:
     */
    public void externalWrite(){
        //创建sccard_text文件对象，由于不同手机SDcard目录不同，所以我们通过Environment.getExternalStorageDirectory()获得路径。
        File file = new File(Environment.getExternalStorageDirectory(), "sccard_text");

        Log.e(TAG, "isSDCardMounted : " + isSDCardMounted());
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
//            FileOutputStream fos =new FileOutputStream(file);
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
//            bw.write("你好啊");
//            bw.write("\n");
//            bw.flush();
//            bw.close();
//            fos.close();

            file.createNewFile();//如果文件不存在，则创建
            FileOutputStream fos = new FileOutputStream(file);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(fos));
            writer.write("externalRead.......");   //写入固定的String
            writer.write("hello");           //写入出入的String
            writer.write("\n");          //写入换行符
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 外部存储读数据
     * @param:
     * @return:
     */
    public void externalRead(){
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "sccard_text");
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br =  new BufferedReader(new InputStreamReader(fis));
            String line = br.readLine();
            while(line!=null){
                Log.e(TAG, "data : " + line);
                line = br.readLine();
            }
            br.close();
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 判断SD卡是否被挂载
    public static boolean isSDCardMounted() {
        // return Environment.getExternalStorageState().equals("mounted");
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }
}
