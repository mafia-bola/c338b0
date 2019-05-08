package com.android.desawisata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;

import com.android.desawisata.API_DesaWisata.PicassoClient;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

public class KegiatanDetail extends AppCompatActivity {

    JustifiedTextView txtJudulKegiatan, txtDeskripsi;
    ImageView imageView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kegiatan_detail);

        txtJudulKegiatan = findViewById(R.id.txtJudulKegiatan);
        txtDeskripsi = findViewById(R.id.txtDeskripsi);
        imageView = findViewById(R.id.imageKegiatan);
        toolbar = findViewById(R.id.toolbar);

        Intent detail = this.getIntent();
        String nama_kegiatan = detail.getExtras().getString("NamaKegiatan");
        String deskripsi = detail.getExtras().getString("Deskripsi");
        String foto = detail.getExtras().getString("Foto");

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
        getSupportActionBar().setTitle(nama_kegiatan);


        txtJudulKegiatan.setText(nama_kegiatan);
        txtDeskripsi.setText(deskripsi);
        PicassoClient.downloadImage(this, foto, imageView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtDeskripsi.setText(Html.fromHtml(deskripsi, Html.FROM_HTML_MODE_LEGACY));
        } else {
            txtDeskripsi.setText(Html.fromHtml(deskripsi));
        }
    }
}
