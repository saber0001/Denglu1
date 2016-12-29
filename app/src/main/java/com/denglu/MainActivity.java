package com.denglu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText enter_name,enter_passwrod;
    private Button button_login,button_register;

    private UserLogic userLogic = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enter_name = (EditText) findViewById(R.id.name_enter);
        enter_passwrod = (EditText) findViewById(R.id.enter_password);
        button_login = (Button) findViewById(R.id.button1);
        button_register = (Button) findViewById(R.id.button2);
        userLogic = new UserLogicDaoImpl(this);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = enter_name.getText().toString();
                String password = enter_passwrod.getText().toString();
                if (username == null || username.equals("")){
                    enter_name.setError("用户名不能为空!!!!");
                    Toast.makeText(getBaseContext(),"用户名不为空!!!!",Toast.LENGTH_LONG).show();
                }
                if (password == null || enter_passwrod.equals("")){
                    enter_passwrod.setError("密码不能为空!!!");
                    Toast.makeText(getBaseContext(),"密码不能为空!!!",Toast.LENGTH_LONG).show();
                }
                User user = userLogic.Login(username,password);
                if (user !=null){
                    Toast.makeText(getBaseContext(),"用户名:"+user.getUsername()+"登录成功!!!1",Toast.LENGTH_LONG);
                    Intent intent = new Intent(MainActivity.this,Welcome.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getBaseContext(),"用户名或者密码错误，登录失败",Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == 100){
            User user = (User) data.getSerializableExtra("user");
            if (user !=null){
                enter_name.setText(user.getUsername());
                enter_passwrod.setText(user.getPassword());
            }
        }
    }
}
