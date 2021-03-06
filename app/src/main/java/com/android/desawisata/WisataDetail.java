package com.android.desawisata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ListView;

import com.android.desawisata.API_DesaWisata.AtraksiDownloader;
import com.android.desawisata.API_DesaWisata.ImageDownloader;
import com.android.desawisata.API_DesaWisata.VideoDownloader;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

public class WisataDetail extends AppCompatActivity implements OnMapReadyCallback {

    ListView lv, listView;
    ListView videoList;
    JustifiedTextView txtSejarahWisata, txtDemografi, txtPotensi;
    GoogleMap mMap;
    Toolbar toolbar;
    JustifiedTextView txtAlamatWisata, txtWisata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisata_detail);

        txtAlamatWisata = findViewById(R.id.txtAlamatWisata);
        txtSejarahWisata = findViewById(R.id.txtSejarahWisata);
        txtDemografi = findViewById(R.id.txtDemografi);
        txtPotensi = findViewById(R.id.txtPotensi);
        txtWisata = findViewById(R.id.txtWisata);
        toolbar = findViewById(R.id.toolbar);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent detail = this.getIntent();
        String namaWisata = detail.getExtras().getString("NamaWisata");
        String alamatWisata = detail.getExtras().getString("AlamatWisata");
        String sejarahWisata = detail.getExtras().getString("Sejarah");
        String demografi = detail.getExtras().getString("Demografi");
        String potensi = detail.getExtras().getString("Potensi");
        final int tempat_wisata_id = detail.getExtras().getInt("WisataID");

        txtAlamatWisata.setText("Alamat Lokasi : "+alamatWisata);
        txtWisata.setText(namaWisata);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtSejarahWisata.setText(Html.fromHtml(sejarahWisata, Html.FROM_HTML_MODE_LEGACY));
        } else {
            txtSejarahWisata.setText(Html.fromHtml(sejarahWisata));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtDemografi.setText(Html.fromHtml(demografi, Html.FROM_HTML_MODE_LEGACY));
        } else {
            txtDemografi.setText(Html.fromHtml(demografi));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtPotensi.setText(Html.fromHtml(potensi, Html.FROM_HTML_MODE_LEGACY));
        } else {
            txtPotensi.setText(Html.fromHtml(potensi));
        }

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(WisataDetail.this, WisataActivity.class);
                startActivity(back);
                finish();
            }
        });
        getSupportActionBar().setTitle(namaWisata);

        String link = getString(R.string.urlAddress);

        lv = findViewById(R.id.lv);
        final String urlAddress = link + "api/wisata/" + tempat_wisata_id + "/foto";
        new ImageDownloader(WisataDetail.this, urlAddress, lv).execute();

        videoList = findViewById(R.id.videoList);
        final String urlVideo = link + "api/wisata/" + tempat_wisata_id + "/video";
        new VideoDownloader(WisataDetail.this, urlVideo, videoList).execute();

        listView = findViewById(R.id.listView);
        final String urlKegiatan = link + "api/kegiatan/" + tempat_wisata_id;
        new AtraksiDownloader(WisataDetail.this, urlKegiatan, listView).execute();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Intent detail = this.getIntent();
        String namaWisata = detail.getExtras().getString("NamaWisata");
        double lat = detail.getExtras().getDouble("Latitude");
        double lng = detail.getExtras().getDouble("Longitude");

        mMap = googleMap;
        LatLng defaults = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(defaults).title(namaWisata));

        float zoomLevel = 10.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaults, zoomLevel));
    }
}

