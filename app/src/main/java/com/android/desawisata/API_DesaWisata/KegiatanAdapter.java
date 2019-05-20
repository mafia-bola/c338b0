package com.android.desawisata.API_DesaWisata;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.desawisata.KegiatanDetail;
import com.android.desawisata.R;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import java.util.ArrayList;

public class KegiatanAdapter extends BaseAdapter {

    Context c;
    ArrayList<DesaWisata> desaWisata;

    public KegiatanAdapter(Context c, ArrayList<DesaWisata> desaWisata) {
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
            convertView = LayoutInflater.from(c).inflate(R.layout.kegiatan_model, parent, false);
        }

        JustifiedTextView txtViewKegiatan = convertView.findViewById(R.id.txtViewKegiatan);
        JustifiedTextView txtKegiatanWisata = convertView.findViewById(R.id.txtKegiatanWisata);
        ImageView backgroundKegiatan = convertView.findViewById(R.id.backgroundKegiatan);

        final DesaWisata dw = (DesaWisata) this.getItem(position);
        txtViewKegiatan.setText(dw.getNama_kegiatan());
        txtKegiatanWisata.setText(dw.getNama_wisata());
        PicassoClient.downloadImage(c, dw.getFoto(), backgroundKegiatan);

        final String nama_kegiatan = dw.getNama_kegiatan();
        final String deskripsi = dw.getDeskripsi();
        final String foto = dw.getFoto();

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetail(nama_kegiatan, deskripsi, foto);
            }
        });

        return convertView;
    }

    private void openDetail(String nama_kegiatan, String deskripsi, String foto){
        Intent detail = new Intent(c, KegiatanDetail.class);
        detail.putExtra("NamaKegiatan", nama_kegiatan);
        detail.putExtra("Deskripsi", deskripsi);
        detail.putExtra("Foto", foto);
        c.startActivity(detail);
    }
}
