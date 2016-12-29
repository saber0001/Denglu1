package com.denglu;

/**
 * Created by Administrator on 2016/12/28.
 */
public interface UserLogic {


    User Login(String username, String password);

    boolean Register(User user);
}
