package com.example.vsga_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vsga_activity.Util.DBOpenHelper;

public class Login extends AppCompatActivity {
    EditText Username;
    EditText Pasword;
    Button btnLogin;
    Button btnRegister;
    DBOpenHelper dbOpenHelper;
    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Username = findViewById(R.id.username);
        Pasword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.loginbtn);
        btnRegister = findViewById(R.id.register);
        dbOpenHelper = new DBOpenHelper(this);
        shared = this.getSharedPreferences("remember",MODE_PRIVATE);
        String uname = shared.getString("username","");
        if(uname.equals("")){

        }else{
            startActivity(new Intent(Login.this,MainActivity.class));
            finish();
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            cariData();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Register.class));
            }
        });
    }
    public void cariData(){
        String username = Username.getText().toString();
        String pass = Pasword.getText().toString();
        Cursor cursor = dbOpenHelper.getData(username,pass);
        cursor.moveToFirst();
        if(cursor.getCount()==1){
            Toast.makeText(this,"Berhasil Login",Toast.LENGTH_LONG).show();
            startActivity(new Intent(Login.this,MainActivity.class));
            SharedPreferences.Editor editor = shared.edit();
            editor.putString("username",username);
            editor.commit();
            finish();
        }else{
            Toast.makeText(this,"gagal Login",Toast.LENGTH_LONG).show();
        }
    }
}
