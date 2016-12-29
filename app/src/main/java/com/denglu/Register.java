package com.denglu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/12/28.
 */

public class Register extends Activity {
    private EditText re_name,re_password,re_quepassword;
    private Button btn_reset,btn_submint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        initView();
    }
    public void initView(){
        re_name = (EditText) findViewById(R.id.name_register);
        re_password = (EditText) findViewById(R.id.password_register);
        re_quepassword = (EditText) findViewById(R.id.register_quepassword);
        btn_reset = (Button) findViewById(R.id.clear);
        btn_submint = (Button) findViewById(R.id.submit);

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                re_name.setText(" ");
                re_password.setText("");
                re_quepassword.setText("");
            }
        });
        btn_submint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg_name = re_name.getText().toString();
                String msg_password = re_password.getText().toString();
                String msg_quepassword = re_quepassword.getText().toString();
                if (msg_name == null || msg_name.equals("")){
                    re_name.setError("用户名不能为空!!!");
                    Toast.makeText(getBaseContext(),"用户名不能为空!!!!",Toast.LENGTH_LONG).show();
                    return;
                }
                if (msg_password == null || msg_password.equals("")){
                    re_password.setError("密码不为空!!!!");
                    Toast.makeText(getBaseContext(),"密码不为空!!!",Toast.LENGTH_LONG).show();
                    return;
                }
                if (msg_quepassword == null || msg_quepassword.equals("")){
                    re_quepassword.setError("确认密码不为空!!!");
                    Toast.makeText(getBaseContext(),"确认密码不为空!!!",Toast.LENGTH_LONG).show();
                    return;
                }
                User user = new User(msg_name,msg_password);
                UserLogic userLogic = new UserLogicDaoImpl(getBaseContext());
                boolean falg = userLogic.Register(user);
                Toast.makeText(getBaseContext(),falg?"注册成功":"注册失败,用户名已经存在",Toast.LENGTH_LONG).show();
                if (falg){
                    Intent intent = new Intent();
                    setResult(100,intent);
                    finish();
                }
            }
        });
    }
}
