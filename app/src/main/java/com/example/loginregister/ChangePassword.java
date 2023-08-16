package com.example.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChangePassword extends AppCompatActivity {
    EditText e1,e2,e3;
    CDB db;
    String s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        Intent intent=getIntent();
        s=intent.getStringExtra("username");
        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText)findViewById(R.id.e2);
        e3=(EditText)findViewById(R.id.e3);
        db=new CDB(this);

    }
    public void onUpdate(View v) {
        String oldPassword = e1.getText().toString();
        String newPassword = e2.getText().toString();
        String confPassword = e3.getText().toString();

        if (oldPassword.equals(newPassword)) {
            Toast.makeText(this, "New password matches the old one", Toast.LENGTH_SHORT).show();
            return;
        } else if (!newPassword.equals(confPassword)) {
            Toast.makeText(this, "Confirmation failed", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean updated = db.update(s, oldPassword, newPassword);

        if (updated) {
            Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Password update failed", Toast.LENGTH_SHORT).show();
        }

        e1.setText("");
        e2.setText("");
        e3.setText("");
    }

    public void onBack(View v){
        finish();
    }
}