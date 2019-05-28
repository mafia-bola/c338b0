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
        ImageView backgroundDesa = convertView.findViewById(R.id.backgroundDesa);

        final DesaWisata dw = (DesaWisata) this.getItem(position);
        txtNamaWisata.setText(dw.getNama_wisata());
        PicassoClient.downloadImage(c, dw.getThumbnail(), backgroundDesa);

        final int id = dw.getId();
        final String nama_wisata = dw.getNama_wisata();
        final String alamat_wisata = dw.getAlamat_wisata();
        final String sejarah = dw.getSejarah_wisata();
        final String demografi = dw.getDemografi();
        final String potensi = dw.getPotensi();
        final double lat = dw.getLat();
        final double lng = dw.getLng();

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetail(id, nama_wisata, alamat_wisata, sejarah, demografi, potensi, lat, lng);
            }
        });

        return convertView;
    }

    private void openDetail(
            int id,
            String nama_wisata, String alamat_wisata,
            String sejarah_wisata, String demografi, String potensi,
            double lat, double lng
            ){
        Intent detail = new Intent(c, WisataDetail.class);
        detail.putExtra("WisataID", id);
        detail.putExtra("NamaWisata", nama_wisata);
        detail.putExtra("AlamatWisata", alamat_wisata);
        detail.putExtra("Sejarah", sejarah_wisata);
        detail.putExtra("Demografi", demografi);
        detail.putExtra("Potensi", potensi);
        detail.putExtra("Latitude", lat);
        detail.putExtra("Longitude", lng);
        c.startActivity(detail);
    }
}
