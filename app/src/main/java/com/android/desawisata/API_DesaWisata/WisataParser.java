package com.android.desawisata.API_DesaWisata;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WisataParser extends AsyncTask<Void, Void, Boolean> {

    Context c;
    String jsonData;
    ListView lv;

    ProgressDialog pd;
    ArrayList<DesaWisata> desaWisata = new ArrayList<>();

    public WisataParser(Context c, String jsonData, ListView lv) {
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
            WisataAdapter adapter = new WisataAdapter(c, desaWisata);
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

                int id = jo.getInt("id");
                int desa_id = jo.getInt("desa_id");
                String nama_desa = jo.getString("nama_desa");
                String status = jo.getString("status");
                String nama_wisata = jo.getString("nama_wisata");
                String alamat_wisata = jo.getString("alamat_wisata");
                String sejarah_wisata = jo.getString("sejarah_wisata");
                String demografi = jo.getString("demografi");
                String potensi = jo.getString("potensi");
                double lat = jo.getDouble("lat");
                double lng = jo.getDouble("lng");
                int tempat_wisata_id = jo.getInt("tempat_wisata_id");

                dw = new DesaWisata();
                dw.setId(id);
                dw.setDesa_id(desa_id);
                dw.setNama_desa(nama_desa);
                dw.setStatus(status);
                dw.setNama_wisata(nama_wisata);
                dw.setAlamat_wisata(alamat_wisata);
                dw.setSejarah_wisata(sejarah_wisata);
                dw.setDemografi(demografi);
                dw.setPotensi(potensi);
                dw.setLat(lat);
                dw.setLng(lng);
                dw.setTempat_wisata_id(tempat_wisata_id);

                desaWisata.add(dw);
            }

            return true;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }
}
