package com.example.mariya.someandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mariya.someandroidapp.CustomViews.AuthDialog;
import com.example.mariya.someandroidapp.interfaces.AuthListener;

public class MainActivity extends AppCompatActivity implements AuthListener {

    private AuthDialog auth_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onCodeReceived(String auth_token) {
        if (auth_token == null)
            return;

    }

    public void after_click_login (View view) {
        auth_dialog = new AuthDialog(this, this);
        auth_dialog.setCancelable(true);
        auth_dialog.show();
    }
}
