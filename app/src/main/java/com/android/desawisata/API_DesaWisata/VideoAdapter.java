package com.android.desawisata.API_DesaWisata;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.desawisata.R;

import java.util.ArrayList;

public class VideoAdapter extends BaseAdapter {

    Context c;
    ArrayList<DesaWisata> desaWisata;
    int index = 0;

    public VideoAdapter(Context c, ArrayList<DesaWisata> desaWisata) {
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
            convertView = LayoutInflater.from(c).inflate(R.layout.wisata_video, parent, false);
        }

        final VideoView videoView = convertView.findViewById(R.id.videoView);

        final DesaWisata dw = (DesaWisata) this.getItem(position);
        final MediaController mediaController = new MediaController(c);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.setVideoPath(dw.getFile_video());
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        videoView.setMediaController(mediaController);
                        mediaController.setAnchorView(videoView);

                    }
                });
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(c, "Video telah berakhir", Toast.LENGTH_SHORT).show();
                if (index++ == desaWisata.size()){
                    index = 0;
                    Toast.makeText(c, "Videos telah selesai", Toast.LENGTH_SHORT).show();
                } else {
                    videoView.setVideoPath(dw.getFile_video());
                    videoView.start();
                }
            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.d("API_Test", "Checking" + what + " extra " + extra);
                return false;
            }
        });
        videoView.start();

        return convertView;
    }
}
