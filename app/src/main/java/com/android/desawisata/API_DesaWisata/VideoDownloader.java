package com.android.desawisata.API_DesaWisata;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class VideoDownloader extends AsyncTask<Void, Void, String> {

    Context c;
    String urlVideo;
    ListView videoList;

    ProgressDialog pd;

    public VideoDownloader(Context c, String urlVideo, ListView videoList) {
        this.c = c;
        this.urlVideo = urlVideo;
        this.videoList = videoList;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(c);
        pd.setTitle("Unduh");
        pd.setMessage("Data sedang di untuh ... Mohon Tunggu");
        pd.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.downloadVideo();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);

        pd.dismiss();
        if (jsonData == null){
            Toast.makeText(c, "Data gagal di unduh", Toast.LENGTH_SHORT).show();
        } else {
            new VideoParser(c, jsonData, videoList).execute();
        }
    }

    private String downloadVideo(){
        HttpURLConnection con = ConnectorVideo.connection(urlVideo);
        if (con == null){
            return null;
        }

        try {
            InputStream is = new BufferedInputStream(con.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            StringBuffer jsonData = new StringBuffer();

            while ((line = br.readLine()) != null){
                jsonData.append(line+"\n");
            }

            br.close();
            is.close();

            return jsonData.toString();

        } catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
