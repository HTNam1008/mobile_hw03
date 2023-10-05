package com.example.week3_class;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ConcurrentModificationException;

public class SignUpActivity extends AppCompatActivity {
    private Context context;
    private int duration=Toast.LENGTH_SHORT;

    private Button btnExit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        btnExit=findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle receivedBundle = getIntent().getExtras();
        if (receivedBundle != null) {
            String username = receivedBundle.getString("username");
            String password = receivedBundle.getString("password");

            String birthday = receivedBundle.getString("birthday");
            String gender = receivedBundle.getString("gender");
            String hobbies = receivedBundle.getString("hobbies");


            // Bây giờ bạn có thể sử dụng username và password trong Activity B
            // Ví dụ: hiển thị chúng trên giao diện người dùng
            TextView txtUsername = findViewById(R.id.txtUsername);
            TextView txtPassword = findViewById(R.id.txtPassword);
            TextView txtBirthday = findViewById(R.id.txtBirthday);
            TextView txtGender = findViewById(R.id.txtGender);
            TextView txtHobbies = findViewById(R.id.txtHobbies);

            txtUsername.setText("Username: " + username);
            txtPassword.setText(password);
            txtBirthday.setText("Birthday: "+birthday);
            txtGender.setText("Gender: "+gender);
            txtHobbies.setText("Hobbies: "+hobbies);

        }

    }
// th test

}
