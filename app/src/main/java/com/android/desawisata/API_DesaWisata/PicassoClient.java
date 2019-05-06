package com.android.desawisata.API_DesaWisata;

import android.content.Context;
import android.widget.ImageView;

import com.android.desawisata.R;
import com.squareup.picasso.Picasso;

public class PicassoClient {

    public static void downloadImage(Context c, String urlAddress, ImageView imageList) {
        if (urlAddress != null && urlAddress.length() > 0) {
            Picasso
                    .with(c)
                    .load(urlAddress)
                    .placeholder(R.drawable.fixed_background)
                    .fit()
                    .into(imageList);
        } else {
            Picasso.with(c).load(R.drawable.fixed_background).into(imageList);
        }
    }
}
