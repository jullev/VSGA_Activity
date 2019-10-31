package com.example.vsga_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vsga_activity.Util.DBOpenHelper;

public class Register extends AppCompatActivity {
    EditText Username;
    EditText Password;
    EditText RePassword;
Button btnRegister;
DBOpenHelper dbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Username = findViewById(R.id.registuname);
        Password = findViewById(R.id.registpass);
        RePassword = findViewById(R.id.registpassagain);
        btnRegister = findViewById(R.id.btnregis);
        dbOpenHelper = new DBOpenHelper(this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();
            }
        });
    }
    public void SaveData(){
        String username = Username.getText().toString();
        String password = Password.getText().toString();
        String repass = RePassword.getText().toString();
        if(password.equals(repass)){
            dbOpenHelper.insertUser(username,password);
            Toast.makeText(this,"Berhasil Login",Toast.LENGTH_LONG).show();
            startActivity(new Intent(Register.this,Login.class));
        }else {
            Toast.makeText(this,"Password Anda Tidak Cocok",Toast.LENGTH_LONG).show();
        }
    }
}
