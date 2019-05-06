package com.android.desawisata.API_DesaWisata;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.android.desawisata.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class KegiatanParser extends AsyncTask<Void, Void, Boolean> {

    Context c;
    String jsonData;
    ListView lv;

    ProgressDialog pd;
    ArrayList<DesaWisata> desaWisata = new ArrayList<>();

    public KegiatanParser(Context c, String jsonData, ListView lv) {
        this.c = c;
        this.jsonData = jsonData;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(c);
        pd.setTitle("Proses");
        pd.setMessage("Data sedang di proses ... Mohon Tunggu");
        pd.show();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Boolean parsed) {
        super.onPostExecute(parsed);

        pd.dismiss();
        if (parsed){
            KegiatanAdapter adapter = new KegiatanAdapter(c, desaWisata);
            lv.setAdapter(adapter);
        } else {
            Toast.makeText(c, "Data gagal di proses", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseData(){
        try {
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;

            desaWisata.clear();
            DesaWisata dw;

            for (int i=0; i<ja.length(); i++){
                jo = ja.getJSONObject(i);

                int kegiatan_id = jo.getInt("id");
                String nama_kegiatan = jo.getString("nama_kegiatan");
                String deskripsi = jo.getString("deskripsi");
                String foto = jo.getString("foto");

                String link = c.getString(R.string.urlAddress);
                foto = link+foto;

                dw = new DesaWisata();
                dw.setKegiatan_id(kegiatan_id);
                dw.setNama_kegiatan(nama_kegiatan);
                dw.setDeskripsi(deskripsi);
                dw.setFoto(foto);

                desaWisata.add(dw);
            }

            return true;

        } catch (JSONException e){
            e.printStackTrace();
        }

        return false;
    }
}
