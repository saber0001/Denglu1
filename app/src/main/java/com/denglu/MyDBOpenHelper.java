package com.denglu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/12/28.
 */

public class MyDBOpenHelper  extends SQLiteOpenHelper{
    private final static String DB_NAME = "bwf_db";
    private final static int DB_VERSON = 1;

    public MyDBOpenHelper(Context context){
        super(context,DB_NAME,null,DB_VERSON);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * 创建User表
         */
        StringBuffer sql = new StringBuffer();
        sql.append("create table ")
                .append(UserColumnNanme.TABLE_NAME)
                .append("(")
                .append(UserColumnNanme._ID).append(" int primary key autoincrement,")
                .append(UserColumnNanme.USERNAME).append(" text,")
                .append(UserColumnNanme.PASSWORD).append(" text")
                .append(")");
        db.execSQL(sql.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public interface UserColumnNanme{
        String TABLE_NAME = "user";
        String _ID = "_id";
        String USERNAME = "username";
        String PASSWORD = "password";
    }
}
