package com.android.desawisata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class AboutActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        Intent kembali = new Intent(AboutActivity.this, WisataActivity.class);
        startActivity(kembali);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}
