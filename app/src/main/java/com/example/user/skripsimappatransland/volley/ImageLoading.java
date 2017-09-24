package com.example.user.skripsimappatransland.volley;

import android.util.Log;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by Qwerty on 4/30/2017.
 */

public class ImageLoading {

    public static void ImageLoading(String url, final ImageView imageView){
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        imageLoader.get(url, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if (response.getBitmap() != null){
                    imageView.setImageBitmap(response.getBitmap());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("EImage", "Error Image Loader : "+error.getMessage().toString());
//                imageView.setImageBitmap(R.drawable.ic_error_24dp.);
            }
        });
//        imageLoader.get(url, ImageLoader.getImageListener(
//                imageView, R.drawable.ic_find_in_page_24dp, R.drawable.ic_error_24dp));
    }

    /*
    http://www.androidhive.info/2014/05/android-working-with-volley-library-1/

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    // If you are using NetworkImageView
    imgNetWorkView.setImageUrl(Const.URL_IMAGE, imageLoader);

    //Loading image with placeholder and error image
    imageLoader.get(Const.URL_IMAGE, ImageLoader.getImageListener(
                imageView, R.drawable.ico_loading, R.drawable.ico_error));
     */
}
