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

public class VideoParser extends AsyncTask<Void, Void, Boolean> {

    Context c;
    String jsonData;
    ListView videoList;

    ProgressDialog pd;
    ArrayList<DesaWisata> desaWisata = new ArrayList<>();

    public VideoParser(Context c, String jsonData, ListView videoList) {
        this.c = c;
        this.jsonData = jsonData;
        this.videoList = videoList;
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
        return this.parseVideo();
    }

    @Override
    protected void onPostExecute(Boolean parsed) {
        super.onPostExecute(parsed);

        pd.dismiss();
        if (parsed){
            VideoAdapter adapter = new VideoAdapter(c, desaWisata);
            videoList.setAdapter(adapter);
        } else {
            Toast.makeText(c, "Data gagal di proses", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseVideo(){
        try {
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;

            desaWisata.clear();
            DesaWisata dw;

            for (int i=0; i<ja.length(); i++){
                jo = ja.getJSONObject(i);

                int id = jo.getInt("id");
                String file_video = jo.getString("file");

                dw = new DesaWisata();
                dw.setVideo_id(id);
                dw.setFile_video(file_video);

                desaWisata.add(dw);
            }

            return true;

        } catch (JSONException e){
            e.printStackTrace();
        }

        return false;
    }
}
