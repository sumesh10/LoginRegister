package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2;
    CDB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText) findViewById(R.id.e2);
        db=new CDB(this);
    }
    public void register(View v){
        Intent i=new Intent("Reg");
        startActivity(i);
    }
    public void login(View v){
        String a;
        try {
            String uname,upassword;
            uname=e1.getText().toString();
            upassword=e2.getText().toString();
            a= db.find(uname,upassword);
            if(a.equals(uname)){
                Toast.makeText(this, "Welcome "+uname, Toast.LENGTH_LONG).show();

                Intent i=new Intent("home");
                i.putExtra("username",e1.getText().toString());
                startActivity(i);
                e1.setText("");
                e2.setText("");


            }
            else if(a.equals("Incorrect password")){
                Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show();
            }
            else if(a.equals("User does not exist")){
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}