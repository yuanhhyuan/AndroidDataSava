package com.hy.base.androidbase.androidbasesave.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "user")
public class UserInfo1 implements Serializable {

    @DatabaseField(generatedId = true)
    private int id;

    //用户姓名
    @DatabaseField(columnName = "user_name")
    public String username;
    //用户性别
    @DatabaseField(columnName = "user_sex")
    public String usersex;
    //用户年龄
    @DatabaseField(columnName = "user_age")
    public String userage;
    //用户电话
    @DatabaseField(columnName = "user_phone")
    public String userphone;
    //用户身份证号
    @DatabaseField(columnName = "user_id")
    public String userid;
    //时间
    @DatabaseField(columnName = "data_time")
    public String dataTime;

    public UserInfo1() {
    }

    public String getName() {
        return username;
    }
    public void setName(String username) {
        this.username = username;
    }

    public String getSex() {
        return usersex;
    }
    public void setSex(String usersex) {
        this.usersex = usersex;
    }

    public String getAge() {
        return userage;
    }
    public void setAge(String userage) {
        this.userage = userage;
    }

    public String getPhone() {
        return userphone;
    }

    public void setPhone(String userphone) {
        this.userphone = userphone;
    }

    public String getId() {
        return userid;
    }

    public void setId(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "UserInfo1{" +
                "username=" + username +
                ", usersex='" + usersex + '\'' +
                ", userage='" + userage + '\'' +
                ", userphone='" + userphone + '\'' +
                ", userid='" + userid + '\'' +
                '}';
    }
}
