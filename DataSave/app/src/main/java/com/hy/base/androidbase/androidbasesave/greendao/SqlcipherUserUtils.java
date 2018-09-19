package com.hy.base.androidbase.androidbasesave.greendao;

import android.support.annotation.NonNull;

import com.hy.AndroidBase.gen.UserDao;
import com.hy.app.config.MyApplication;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SqlcipherUserUtils {
    private static SqlcipherUserUtils mUserUtils;

    public SqlcipherUserUtils(){

    }

    public static SqlcipherUserUtils getUserUtils(){
        if (mUserUtils == null) {
            mUserUtils = new SqlcipherUserUtils();
        }
        return mUserUtils;
    }

    /**
     * 添加数据，如果有重复则覆盖
     *
     */
    public  void insert(User mUser) {
        MyApplication.getSqlcipherDaoSession().getUserDao().insertOrReplace(mUser);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public  void delete(long id) {
        MyApplication.getSqlcipherDaoSession().getUserDao().deleteByKey(id);
    }

    /**
     * 更新数据
     */
    public  void update(User mUser) {
        MyApplication.getSqlcipherDaoSession().getUserDao().update(mUser);
    }

    /**
     * 查询Type为1的所有数据
     *
     * @return
     */
    public  List<User> queryUser(String name) {
        return MyApplication.getSqlcipherDaoSession().getUserDao().queryBuilder().where(UserDao.Properties.Name.eq(name)).list();

    }

    /**
     * 查询所有数据
     *
     * @return
     */
    public  List<User> queryAll() {
        List<User> mUser = new List<User>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<User> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] ts) {
                return null;
            }

            @Override
            public boolean add(User user) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends User> collection) {
                return false;
            }

            @Override
            public boolean addAll(int i, @NonNull Collection<? extends User> collection) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public User get(int i) {
                return null;
            }

            @Override
            public User set(int i, User user) {
                return null;
            }

            @Override
            public void add(int i, User user) {

            }

            @Override
            public User remove(int i) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<User> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<User> listIterator(int i) {
                return null;
            }

            @NonNull
            @Override
            public List<User> subList(int i, int i1) {
                return null;
            }
        };

        if(MyApplication.getSqlcipherDaoSession().getUserDao()!=null){
            mUser = MyApplication.getSqlcipherDaoSession().getUserDao().loadAll();
        }

        return mUser;

        /**
         * 如果直接return MyApplication.getDaoInstant().getUserDao().loadAll(); 如果表为空，会导致奔溃。
         *
         * */
    }


}
