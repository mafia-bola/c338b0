package com.android.desawisata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.desawisata.API_DesaWisata.PicassoClient;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

public class KegiatanDetail extends AppCompatActivity {

    JustifiedTextView txtNamaKegiatan, txtDeskripsi;
    ImageView imageView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kegiatan_detail);

        txtNamaKegiatan = findViewById(R.id.txtNamaKegiatan);
        txtDeskripsi = findViewById(R.id.txtDeskripsi);
        imageView = findViewById(R.id.imageKegiatan);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(KegiatanDetail.this, KegiatanActivity.class);
                startActivity(back);
                finish();
            }
        });
        getSupportActionBar().setTitle("");

        Intent detail = this.getIntent();
        String nama_kegiatan = detail.getExtras().getString("NamaKegiatan");
        String deskripsi = detail.getExtras().getString("Deskripsi");
        String foto = detail.getExtras().getString("Foto");

        txtNamaKegiatan.setText(nama_kegiatan);
        txtDeskripsi.setText(deskripsi);
        PicassoClient.downloadImage(this, foto, imageView);
    }
}
