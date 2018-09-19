package com.hy.base.androidbase.androidbasesave;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hy.base.androidbase.androidbasesave.db.DatabaseHelper;
import com.hy.base.androidbase.androidbasesave.db.DateUtils;
import com.hy.base.androidbase.androidbasesave.db.SDcardUtils1;
import com.hy.base.androidbase.androidbasesave.db.UserInfo1;
import com.hy.base.androidbase.androidbasesave.db.UserInfo1Dao;
import com.hy.base.androidbase.androidbasesave.greendao.GreenDaoQureyActivity;
import com.hy.base.androidbase.androidbasesave.greendao.SqlcipherUserUtils;
import com.hy.base.androidbase.androidbasesave.greendao.User;
import com.hy.base.androidbase.androidbasesave.greendao.UserUtils;
import com.hy.datasave.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AndroidbasesaveActivity extends Activity implements FileSaveCallback {
    String TAG = "060_AndroidbasesaveActivity";

    EditText spedit;
    TextView spout;
    Button spwrite;
    Button spread;
    String spstr;


    EditText filewrite;
    EditText fileread;
    Button filewrite1;
    Button fileread1;
    String filewritestr;

    EditText cachewrite;
    EditText cacheread;
    Button cachewrite1;
    Button cacheread1;

    String cachewritestr;

    Button externalWrite1;
    Button externalRead;
    EditText externalWrite;
    String externalWritestr;

    Button zeng;
    Button insert0;
    Button insert1;
    Button shan;
    Button shanall;
    Button gai;
    Button cha0;
    Button cha;

    Button insertg;
    Button deleteg;
    Button updateg;
    Button queryg;
    Button queryallg;

    Button insertgs;
    Button queryallgs;

    FileSaveUtil fileSaveUtil = new FileSaveUtil(this);
    UserInfo1Dao userInfo1Dao = new UserInfo1Dao(AndroidbasesaveActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_androidbs);

        //SharedPreferences
        spout = findViewById(R.id.spout);
        spwrite = findViewById(R.id.spwrite);
        spread = findViewById(R.id.spread);
        spedit = findViewById(R.id.spedit);

        spedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                spedit.setTextColor(Color.BLUE);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                spstr = s.toString();
                Log.e("060_", "spstr : " + spstr);
            }
        });


        spwrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "spstr : " + spstr);
                SharedPreferencesUtils.getInstance().putString("num", spstr);
            }
        });

        spread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String out = SharedPreferencesUtils.getInstance().getString("num");
                Log.e(TAG, "out : " + out);
                spout.setText(out);
            }
        });


        //file
        filewrite = findViewById(R.id.filewrite);
        fileread = findViewById(R.id.fileread);
        filewrite1 = findViewById(R.id.filewrite1);
        fileread1 = findViewById(R.id.fileread1);
        filewrite.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                filewrite.setTextColor(Color.BLUE);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filewritestr = s.toString();
                Log.e("060_", "filewrite : " + filewritestr);
            }
        });

        filewrite1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileSaveUtil.fileWrite(AndroidbasesaveActivity.this, filewritestr);
            }
        });

        fileread1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileSaveUtil.fileRead(AndroidbasesaveActivity.this);
            }
        });


        cachewrite = findViewById(R.id.cachewrite);
        cacheread = findViewById(R.id.cacheread);
        cachewrite1 = findViewById(R.id.cachewrite1);
        cacheread1 = findViewById(R.id.cacheread1);
        cachewrite.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                cachewrite.setTextColor(Color.BLUE);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                cachewritestr = s.toString();
            }
        });

        cachewrite1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("060_", "cachewritestr : " + cachewritestr);
                fileSaveUtil.cacheWrite(AndroidbasesaveActivity.this, cachewritestr);
            }
        });

        cacheread1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileSaveUtil.cacheRead(AndroidbasesaveActivity.this);
            }
        });


        externalWrite = findViewById(R.id.externalWrite);
        externalWrite.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                externalWrite.setTextColor(Color.BLUE);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                externalWritestr = s.toString();
                Log.e("060_", "externalWritestr : " + externalWritestr);
            }
        });
        externalWrite1 = findViewById(R.id.externalWrite1);
        externalWrite1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] data = ("春蚕到死丝方尽，蜡炬成灰泪始干。" + "\n").getBytes();
                SDcardUtils.saveToExternalPublic(data, Environment.DIRECTORY_DOWNLOADS, "mytext1");
                SDcardUtils.saveToExternalRootDirectory(data, "external_1618", "mytext2");
                SDcardUtils.saveToExternalPrivateDirectory(data, getExternalFilesDir(Environment.DIRECTORY_MUSIC), "mytext3");
            }
        });

        externalRead = findViewById(R.id.externalRead);
        externalRead.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] result = SDcardUtils.getData(getExternalFilesDir(Environment.DIRECTORY_MUSIC).getAbsolutePath(), "mytext3");
                Log.e(TAG, "out : " + new String(result));
            }
        });

        DatabaseHelper.getHelper(AndroidbasesaveActivity.this);

        zeng = findViewById(R.id.zeng);
        insert0 = findViewById(R.id.insert0);
        insert1 = findViewById(R.id.insert1);
        gai = findViewById(R.id.gai);
        shan = findViewById(R.id.shan);
        shanall = findViewById(R.id.shanall);
        cha0 = findViewById(R.id.cha0);
        cha = findViewById(R.id.cha);

        zeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfo1 uerInfo1 = new UserInfo1();
                uerInfo1.dataTime = DateUtils.getDateStr(System.currentTimeMillis());
                uerInfo1.username = "wang";
                uerInfo1.usersex = "male";
                uerInfo1.userage = "25";
                uerInfo1.userphone = "13222221111";
                uerInfo1.userid = "210";
                userInfo1Dao.add(uerInfo1);

                UserInfo1 uerInfo2 = new UserInfo1();
                uerInfo2.dataTime = DateUtils.getDateStr(System.currentTimeMillis());
                uerInfo2.username = "li";
                uerInfo2.usersex = "female";
                uerInfo2.userage = "32";
                uerInfo2.userphone = "13500000000";
                uerInfo2.userid = "211";
                userInfo1Dao.add(uerInfo2);

                UserInfo1 uerInfo4 = new UserInfo1();
                uerInfo4.dataTime = DateUtils.getDateStr(System.currentTimeMillis());
                uerInfo4.username = "feng";
                uerInfo4.usersex = "male";
                uerInfo4.userage = "36";
                uerInfo4.userphone = "13100000000";
                uerInfo4.userid = "212";
                userInfo1Dao.add(uerInfo4);

                SDcardUtils1.corydbtoExternal(getApplicationContext());
            }
        });

        insert0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserInfo1 uerInfo5 = new UserInfo1();
                uerInfo5.dataTime = DateUtils.getDateStr(System.currentTimeMillis());
                uerInfo5.username = "feng";
                uerInfo5.usersex = "male";
                uerInfo5.userage = "36";
                uerInfo5.userphone = "13100000000";
                uerInfo5.userid = "212";

                userInfo1Dao.insert(uerInfo5, "user_id", "212");

            }
        });

        insert1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserInfo1 uerInfo6 = new UserInfo1();
                uerInfo6.dataTime = DateUtils.getDateStr(System.currentTimeMillis());
                uerInfo6.username = "feng";
                uerInfo6.usersex = "male";
                uerInfo6.userage = "36";
                uerInfo6.userphone = "13100000000";
                uerInfo6.userid = "214";

                userInfo1Dao.insert(uerInfo6, "user_id", uerInfo6.userid);

            }
        });

        shan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userInfo1Dao.delete(2);
            }
        });

        shanall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userInfo1Dao.deleteAll();
            }
        });

        gai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfo1 uerInfo3 = new UserInfo1();
                uerInfo3.dataTime = DateUtils.getDateStr(System.currentTimeMillis());
                uerInfo3.username = "zhang";
                uerInfo3.usersex = "female";
                uerInfo3.userage = "30";
                uerInfo3.userphone = "13800000000";
                uerInfo3.userid = "213";
                userInfo1Dao.update(uerInfo3);
            }
        });

        cha0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ArrayList<UserInfo1> where = userInfo1Dao.queryWhere("user_name", "wang");

                    for (int i = 0; i < where.size(); i++) {
                        Log.e("060", "where[" + i + "] :" + where.get(i).toString());
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        cha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ArrayList<UserInfo1> all = userInfo1Dao.queryAll();

                    for (int i = 0; i < all.size(); i++) {
                        Log.e("060", "all[" + i + " :" + all.get(i).toString());
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        insertg = findViewById(R.id.insertg);
        deleteg = findViewById(R.id.deleteg);
        updateg = findViewById(R.id.updateg);
        queryg = findViewById(R.id.queryg);
        queryallg = findViewById(R.id.queryallg);

        insertg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserUtils.getUserUtils().insert(new User(1L, "zhangsan", "1",null));
                UserUtils.getUserUtils().insert(new User(2L, "lisi", "2","male"));
                UserUtils.getUserUtils().insert(new User(1L, "wangwu", "3","female"));
                UserUtils.getUserUtils().insert(new User(1L, "zhaoliu", "4","male"));
            }
        });
        deleteg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserUtils.getUserUtils().delete(1);
            }
        });
        updateg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserUtils.getUserUtils().update(new User(2L, "hahahha", "5","male"));
            }
        });
        queryg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserUtils.getUserUtils().queryUser("zhaoliu");
            }
        });
        queryallg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AndroidbasesaveActivity.this, GreenDaoQureyActivity.class);
                startActivity(i);
            }
        });

        insertgs = findViewById(R.id.insertgs);
        queryallgs = findViewById(R.id.queryallgs);
        insertgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SqlcipherUserUtils.getUserUtils().insert(new User(1L, "zhangsan", "1",""));
                SqlcipherUserUtils.getUserUtils().insert(new User(2L, "lisi", "2",""));
                SqlcipherUserUtils.getUserUtils().insert(new User(1L, "wangwu", "3",""));
                SqlcipherUserUtils.getUserUtils().insert(new User(1L, "zhaoliu", "4",""));
            }
        });
        queryallgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> all = SqlcipherUserUtils.getUserUtils().queryAll();
                for (int i = 0; i < all.size(); i++) {
                    Log.e("060", "all[" + i + " :" + all.get(i).toString());
                }
            }
        });
    }


    @Override
    public void onFileRead(String info) {
        fileread.setText(info);
    }

    @Override
    public void oncacheRead(String info) {
        cacheread.setText(info);
    }
}
