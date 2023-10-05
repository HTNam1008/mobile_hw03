package com.example.week3_class;

import android.app.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private int duration=Toast.LENGTH_SHORT;
    private Button btnReset ;
    private Button btnSignUp ;
    private Button btnSelectDate;

    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtRetype;
    private EditText edtBirthday;
    private RadioGroup rgGender;
    private CheckBox checkTennis;
    private CheckBox checkOthers;
    private CheckBox  checkFootball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnReset=findViewById(R.id.btnReset);
        btnSignUp=findViewById(R.id.btnSignUp);
        btnSelectDate=findViewById(R.id.btnSelectDate);

        edtUsername=findViewById(R.id.username);
        edtPassword=findViewById(R.id.password);
        edtRetype=findViewById(R.id.retype);
        edtBirthday=findViewById(R.id.birthday);
        rgGender=findViewById(R.id.gender);
        checkTennis=findViewById(R.id.tennis);
        checkFootball=findViewById(R.id.football);
        checkOthers=findViewById(R.id.others);

        btnReset.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        btnSelectDate.setOnClickListener(this);
    }

    private boolean isRetypeCorrect() {
        boolean check=true;
        if (!edtPassword.getText().toString().equals(edtRetype.getText().toString())) {
            check=false;
        }
        return check;
    }

    private boolean isFullInfo() {
        boolean check=true;
        if (edtUsername.getText().toString().isEmpty()) {
            check = false;
        }
        if (edtPassword.getText().toString().isEmpty()) {
            check=false;
        }
        if (edtRetype.getText().toString().isEmpty()){
            check=false;
        }

        if (rgGender.getCheckedRadioButtonId()==-1) {
            check=false;
        }
        if (!checkFootball.isChecked() && !checkTennis.isChecked() &&!checkOthers.isChecked()){
            check=false;
        }
        return check;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnSelectDate.getId()) {
            final Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            //
            DatePickerDialog datePicker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    calendar.set(i,i1,i2);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                    edtBirthday.setText(dateFormat.format(calendar.getTime()));
                }
            },year,month,day);
            datePicker.show();
            return;
        }
        if (v.getId() == btnReset.getId()) {
            edtUsername.setText("");
            edtPassword.setText("");
            edtRetype.setText("");

            rgGender.clearCheck();
            checkTennis.setChecked(false);
            checkTennis.setSelected(false);

            checkFootball.setChecked(false);
            checkFootball.setSelected(false);

            checkOthers.setChecked(false);
            checkOthers.setSelected(false);

            return;
        }

        if (v.getId() == btnSignUp.getId()) {
            if (!validDate(edtBirthday.getText().toString())) {
                context=getApplicationContext();
                Toast.makeText(context,"Ngày tháng không hợp lệ", duration).show();
                return;
            }
            if (isFullInfo()) {
                if (isRetypeCorrect()) {
                    // Tạo một Bundle và đặt các giá trị vào đó
                    Bundle bundle = new Bundle();
                    bundle.putString("username", edtUsername.getText().toString());
                    bundle.putString("password", edtPassword.getText().toString());
                    bundle.putString("birthday", edtBirthday.getText().toString());
                    RadioButton gender = findViewById(rgGender.getCheckedRadioButtonId());
                    bundle.putString("gender", gender.getText().toString());
                    bundle.putString("hobbies", checkHobbies());
// Tạo Intent để mở Activity B và đính kèm Bundle
                    Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                    intent.putExtras(bundle);
// Khởi động Activity B
                    startActivity(intent);
                }
                else {
                    context=getApplicationContext();
                    Toast.makeText(context,"Nhập lại mật khẩu sai",duration).show();
                }
            }
            else {
                context=getApplicationContext();
                Toast.makeText(context,"Hãy nhập đủ thông tin",duration).show();
            }
            return;
        }
    }


    public String checkHobbies() {
        String hobbies="";
        if (checkFootball.isChecked()) {
            hobbies=hobbies+ checkFootball.getText().toString();
        }
        if (checkTennis.isChecked()) {
            hobbies=hobbies+", "+checkTennis.getText().toString();
        }
        if (checkOthers.isChecked()) {
            hobbies=hobbies+", "+checkOthers.getText().toString();
        }
        return hobbies;
    }
    private static boolean validDate(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            formatter.parse(s);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
}
