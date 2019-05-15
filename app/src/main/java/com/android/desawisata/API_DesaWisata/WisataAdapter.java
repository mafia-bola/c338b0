package com.android.desawisata.API_DesaWisata;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.desawisata.R;
import com.android.desawisata.WisataDetail;

import java.util.ArrayList;

public class WisataAdapter extends BaseAdapter {

    Context c;
    ArrayList<DesaWisata> desaWisata;

    public WisataAdapter(Context c, ArrayList<DesaWisata> desaWisata) {
        this.c = c;
        this.desaWisata = desaWisata;
    }

    @Override
    public int getCount() {
        return desaWisata.size();
    }

    @Override
    public Object getItem(int position) {
        return desaWisata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(c).inflate(R.layout.wisata_model, parent, false);
        }

        TextView txtNamaWisata = convertView.findViewById(R.id.txtNamaWisata);
        TextView txtNamaDesa = convertView.findViewById(R.id.txtNamaDesa);
        ImageView backgroundDesa = convertView.findViewById(R.id.backgroundDesa);

        final DesaWisata dw = (DesaWisata) this.getItem(position);
        txtNamaWisata.setText(dw.getNama_wisata());
        txtNamaDesa.setText(dw.getNama_desa());
        PicassoClient.downloadImage(c, dw.getThumbnail(), backgroundDesa);

        final String nama_desa = dw.getNama_desa();
        final String status = dw.getStatus();
        final String nama_wisata = dw.getNama_wisata();
        final String alamat_wisata = dw.getAlamat_wisata();
        final String sejarah = dw.getSejarah_wisata();
        final String demografi = dw.getDemografi();
        final String potensi = dw.getPotensi();
        final double lat = dw.getLat();
        final double lng = dw.getLng();
        final int tempat_wisata_id = dw.getTempat_wisata_id();

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetail(nama_desa, status, nama_wisata, alamat_wisata, sejarah, demografi, potensi, lat, lng, tempat_wisata_id);
            }
        });

        return convertView;
    }

    private void openDetail(
            String nama_desa, String status,
            String nama_wisata, String alamat_wisata,
            String sejarah_wisata, String demografi, String potensi,
            double lat, double lng, int tempat_wisata_id
            ){
        Intent detail = new Intent(c, WisataDetail.class);
        detail.putExtra("NamaDesa", nama_desa);
        detail.putExtra("Status", status);
        detail.putExtra("NamaWisata", nama_wisata);
        detail.putExtra("AlamatWisata", alamat_wisata);
        detail.putExtra("Sejarah", sejarah_wisata);
        detail.putExtra("Demografi", demografi);
        detail.putExtra("Potensi", potensi);
        detail.putExtra("Latitude", lat);
        detail.putExtra("Longitude", lng);
        detail.putExtra("WisataID", tempat_wisata_id);
        c.startActivity(detail);
    }
}
