package com.android.desawisata.API_DesaWisata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.desawisata.R;

import java.util.ArrayList;

public class KalenderAdapter extends BaseAdapter {

    Context c;
    ArrayList<DesaWisata> desaWisata;

    public KalenderAdapter(Context c, ArrayList<DesaWisata> desaWisata) {
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
            convertView = LayoutInflater.from(c).inflate(R.layout.event_model, parent, false);
        }

        TextView txtJadwal = convertView.findViewById(R.id.txtJadwal);
        ImageView imageEvent = convertView.findViewById(R.id.imageEvent);

        final DesaWisata dw = (DesaWisata) this.getItem(position);
        txtJadwal.setText(dw.getTanggal_event());
        PicassoClient.downloadImage(c, dw.getFile_event(), imageEvent);

        return convertView;
    }
}
