package com.android.desawisata.API_DesaWisata;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.desawisata.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ImageParser extends AsyncTask<Void, Void, Boolean> {

    Context c;
    String jsonData;
    ListView lv;

    ProgressDialog pd;
    ArrayList<DesaWisata> desaWisata = new ArrayList<>();

    public ImageParser(Context c, String jsonData, ListView lv) {
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
            final ImageAdapter adapter = new ImageAdapter(c, desaWisata);
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
                int desa_wisata_id = jo.getInt("desa_wisata_id");
                String file_foto = jo.getString("file");

                String link = c.getString(R.string.urlAddress);
                file_foto = link+file_foto;

                dw = new DesaWisata();
                dw.setFoto_id(id);
                dw.setTempat_wisata_id(desa_wisata_id);
                dw.setFile_foto(file_foto);

                desaWisata.add(dw);
            }

            return true;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }
}
