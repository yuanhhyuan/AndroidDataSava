/**
 * Copyright 2017 Sun Jian
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hy.app.config;

import android.app.Application;

import com.hy.AndroidBase.gen.DaoMaster;
import com.hy.AndroidBase.gen.DaoSession;
import com.hy.base.androidbase.androidbasesave.db.DatabaseHelper;
import com.hy.base.androidbase.androidbasesave.greendao.MyOpenHelper;

import org.greenrobot.greendao.database.Database;


public class GreenDaoApplication extends Application {
    /**
     * 重写Application生命周期
     */
    @Override
    public void onCreate() {
        super.onCreate();

        initDatabase();

        //配置GreenDao数据库
        setupGreenDaoDatabase();

        //配置GreenDao数据库
        setupGreenDaoSqlcipherDatabase();
    }

    public void initDatabase() {
        DatabaseHelper.getHelper(getApplicationContext());
    }

    /**
     * 初始化不加密的数据库
     */
    private void setupGreenDaoDatabase() {
        // 没有数据库升级前，初始化DaoMaster
        //        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "user.db", null);   //创建数据库shop.db
        //        SQLiteDatabase db = helper.getWritableDatabase();  //获取可写数据库
        //        daoMaster = new DaoMaster(db);  //获取数据库对象

        //有数据库升级前，初始化DaoMaster
        if (null == daoMaster) {
            synchronized (GreenDaoApplication.class) {
                if (null == daoMaster) {
                    MyOpenHelper helper = new MyOpenHelper(this, "user.db", null);
                    daoMaster = new DaoMaster(helper.getWritableDatabase());
                }
            }
        }

        //获取dao对象管理者
        daoSession = daoMaster.newSession();
    }

    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    public static DaoMaster getDaoMaster() {
        return daoMaster;
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

    /**
     * 初始化加密的数据库
     */
    private void setupGreenDaoSqlcipherDatabase() {

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "userss.db", null);  //创建数据库shop.db
        Database db = helper.getEncryptedReadableDb("hyhy");  //获取可写数据库
        SqlcipherdaoMaster = new DaoMaster(db);  //获取数据库对象

        //获取dao对象管理者
        SqlcipherdaoSession = SqlcipherdaoMaster.newSession();
    }

    private static DaoMaster SqlcipherdaoMaster;
    private static DaoSession SqlcipherdaoSession;

    public static DaoMaster getSqlcipherDaoMaster() {
        return SqlcipherdaoMaster;
    }

    public static DaoSession getSqlcipherDaoSession() {
        return SqlcipherdaoSession;
    }
}
