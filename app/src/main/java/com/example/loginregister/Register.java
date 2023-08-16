package com.example.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    EditText e1,e2,e3;
    CDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        e1=(EditText) findViewById(R.id.e1);
        e2=(EditText) findViewById(R.id.e2);
        e3=(EditText)findViewById(R.id.e3);
        db=new CDB(this);
    }
    public void onRegister(View v){
        String dn,dl,conf;
        dn=e1.getText().toString();
        dl=e2.getText().toString();
        conf=e3.getText().toString();
        if(dl.equals(conf)){
            db.addDept(dn,dl);
            e1.setText("");
            e2.setText("");
            e3.setText("");
            Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Passwords are not same", Toast.LENGTH_SHORT).show();
        }
    }
    public void onBack(View v){
        finish();
    }
}