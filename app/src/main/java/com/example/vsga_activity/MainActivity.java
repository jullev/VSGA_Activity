package com.example.vsga_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txthello;
    Button Maps;
    Button btnSearch;
    Button btnLogout;
    EditText Search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txthello = findViewById(R.id.hello);
        Maps = findViewById(R.id.mapsaktifity);
        btnSearch = findViewById(R.id.searchjalan);
        btnLogout = findViewById(R.id.logout);
        Search = findViewById(R.id.search);
        final SharedPreferences shared = MainActivity.this.getSharedPreferences
                ("remember",MODE_PRIVATE);
        String user = shared.getString("username","nu");
        Log.e("shared",user);
        Toast.makeText(this,user,Toast.LENGTH_LONG).show();
        txthello.setText("Selamat Datang "+user);
        Maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ActivityMaps.class));
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String alamat = Search.getText().toString();
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q="+alamat));
                startActivity(intent);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = shared.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(MainActivity.this,Login.class));
                finish();
            }
        });
    }
}
