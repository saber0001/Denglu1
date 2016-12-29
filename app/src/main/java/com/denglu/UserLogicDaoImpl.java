package com.denglu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2016/12/28.
 */

public class UserLogicDaoImpl implements UserLogic{
    private SQLiteDatabase db;
    public UserLogicDaoImpl(Context context){
        db = new MyDBOpenHelper(context).getReadableDatabase();
    }

    @Override
    public User Login(String username, String password) {
        Cursor c = db.query(MyDBOpenHelper.UserColumnNanme.TABLE_NAME,null, MyDBOpenHelper.UserColumnNanme.USERNAME+" = ? and "+ MyDBOpenHelper.UserColumnNanme.PASSWORD
        +"= ?",new String[]{username,password},null,null,null);
        User user = null;
        if (c.moveToNext()){
            user = new User(username,password);
            user.setUsername(c.getString(c.getColumnIndex(MyDBOpenHelper.UserColumnNanme.USERNAME)));
            user.setPassword(c.getString(c.getColumnIndex(MyDBOpenHelper.UserColumnNanme.PASSWORD)));

        }
        return user;
    }
    /**
     *  真实情况注册需要先检查用户名是否重复
     */
    @Override
    public boolean Register(User user) {
        //查重名
        Cursor c  = db.query(MyDBOpenHelper.UserColumnNanme.TABLE_NAME,null, MyDBOpenHelper.UserColumnNanme.USERNAME+" = ?",new String[]{user.getUsername()},null,null,null);
        if (c.moveToNext()){
            return false;
        }
        //插入数据
        ContentValues values = new ContentValues();
        values.put(MyDBOpenHelper.UserColumnNanme.USERNAME,user.getUsername());
        values.put(MyDBOpenHelper.UserColumnNanme.PASSWORD,user.getPassword());
        db.insert(MyDBOpenHelper.UserColumnNanme.TABLE_NAME, MyDBOpenHelper.UserColumnNanme._ID,values);
        return true;

    }
}
