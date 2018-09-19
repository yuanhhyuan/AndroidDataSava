package com.hy.base.androidbase.androidbasesave.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hy.AndroidBase.gen.DaoMaster;
import com.hy.AndroidBase.gen.UserDao;

import org.greenrobot.greendao.database.Database;

/**
 * @version V1.0
 * @Package com.hy.base.androidbase.androidbasesave.greendao
 * @Description: ${todo}
 * @author: huangyuan
 * @date: 2018/9/14 15:55
 * @Copyright: www.***.com Inc. All rights reserved.
 */
public class MyOpenHelper extends DaoMaster.OpenHelper {

    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    /**
     * 数据库升级
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //操作数据库的更新 有几个表升级都可以传入到下面
        MigrationHelper.getInstance().migrate(db, UserDao.class);
    }
}
