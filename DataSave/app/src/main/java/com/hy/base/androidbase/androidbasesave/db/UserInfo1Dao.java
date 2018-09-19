package com.hy.base.androidbase.androidbasesave.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.QueryBuilder;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class UserInfo1Dao implements Serializable {
    private Context context;
    private Dao<UserInfo1, Integer> mDao;

    public UserInfo1Dao(Context context) {
        this.context = context;
        mDao = DatabaseHelper.getHelper(context).getUserInfo1Dao(UserInfo1.class);
    }

    /**
     * 增加
     *
     * @param userInfo1
     */
    public void add(UserInfo1 userInfo1) {
        try {
            mDao.create(userInfo1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加
     *
     * @param userInfo1
     */
    public void insert(UserInfo1 userInfo1, String mfield, String mcondition) {

        try {
            if (queryWhere(mfield, mcondition).size() == 0) {
                try {
                    mDao.create(userInfo1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除
     *
     * @param id
     */
    public void delete(int id) {
        try {
            mDao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除所有数据
     */
    public void deleteAll()
    {
        try
        {
            mDao.delete(queryAll());
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 修改
     *
     * @param userInfo1
     */
    public void update(UserInfo1 userInfo1) {
        try {
            mDao.update(userInfo1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询(id)
     *
     * @param id
     */
    public UserInfo1 query(int id) {
        try {
            UserInfo1 userInfo1 = mDao.queryForId(id);
            return userInfo1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询所有
     *
     * @return
     */
    public ArrayList<UserInfo1> queryAll() throws SQLException {
        return TransactionManager.callInTransaction(DatabaseHelper.getHelper(context).getConnectionSource(), new Callable<ArrayList<UserInfo1>>() {
            @Override
            public ArrayList<UserInfo1> call() throws Exception {
                ArrayList<UserInfo1> list = (ArrayList<UserInfo1>) mDao.queryForAll();
                return list;
            }
        });
    }

    /**
     * 条件查询
     *
     * @return
     */
    public ArrayList<UserInfo1> queryWhere(final String field, final String condition) throws SQLException {
        return TransactionManager.callInTransaction(DatabaseHelper.getHelper(context).getConnectionSource(), new Callable<ArrayList<UserInfo1>>() {
            @Override
            public ArrayList<UserInfo1> call() throws Exception {
                QueryBuilder<UserInfo1, Integer> queryBuilder = mDao.queryBuilder();
                ArrayList<UserInfo1> list = (ArrayList<UserInfo1>) queryBuilder.where().eq(field, condition).query();
                return list;
            }
        });
    }

}
