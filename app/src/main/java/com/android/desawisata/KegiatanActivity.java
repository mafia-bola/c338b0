package com.android.desawisata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.android.desawisata.API_DesaWisata.KegiatanDownloader;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import java.util.Objects;

public class KegiatanActivity extends AppCompatActivity {

    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ListView lv;
    SearchView sv;
    JustifiedTextView txtNamaKegiatan;
    BottomNavigationView bottomNavigationView;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert
                .setMessage("Apakah anda mau menutup Aplikasi ?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        KegiatanActivity.this.finish();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kegiatan);

        txtNamaKegiatan = findViewById(R.id.txtJudulKegiatan);
        sv = findViewById(R.id.sv);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Atraksi Wisata");
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                drawerLayout.closeDrawers();
                switch (menuItem.getItemId()){
                    case R.id.about:
                        Intent about = new Intent(KegiatanActivity.this, AboutActivity.class);
                        startActivity(about);
                        finish();
                        return true;
                    case R.id.exitApp:
                        AlertDialog.Builder alert = new AlertDialog.Builder(KegiatanActivity.this);
                        alert
                                .setMessage("Apakah anda mau menutup Aplikasi ?")
                                .setCancelable(false)
                                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        KegiatanActivity.this.finish();
                                    }
                                })
                                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alertDialog = alert.create();
                        alertDialog.show();
                        return true;
                    default:
                        return true;
                }
            }
        });

        drawerLayout = findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menuWisata:
                        menuItem.setChecked(true);
                        Intent car = new Intent(KegiatanActivity.this, WisataActivity.class);
                        startActivity(car);
                        finish();
                        break;
                    case R.id.menuKegiatan:
                        menuItem.setChecked(true);
                        Intent about = new Intent(KegiatanActivity.this, KegiatanActivity.class);
                        startActivity(about);
                        finish();
                        break;
                    case R.id.menuKalender:
                        menuItem.setChecked(true);
                        Intent kalender = new Intent(KegiatanActivity.this, KalenderActivity.class);
                        startActivity(kalender);
                        finish();
                        break;
                }
                return false;
            }
        });

        String link = getString(R.string.urlAddress);
        final String urlAddress = link+"api/kegiatan/";

        lv = findViewById(R.id.lv);
        new KegiatanDownloader(KegiatanActivity.this, urlAddress, lv).execute();

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String search = newText;
                String url = getString(R.string.urlAddress);
                final String urlAddress = url+"api/kegiatan/"+search;
                new KegiatanDownloader(KegiatanActivity.this, urlAddress, lv).execute();

                return false;
            }
        });
    }
}
