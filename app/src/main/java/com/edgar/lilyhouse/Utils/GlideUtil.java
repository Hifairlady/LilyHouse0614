package com.edgar.lilyhouse.Utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.edgar.lilyhouse.GlideApp;
import com.edgar.lilyhouse.R;

public class GlideUtil {

    private static final String TAG = "======================" + GlideUtil.class.getSimpleName();

    private static final String DOWNLOAD_PATH = Environment.getExternalStorageDirectory() +
            "Android/data/LilyHouse/download/";

    public static void setScaledImage(ImageView imageView, String urlString) {
        GlideApp.with(imageView).load(getGlideUrl(urlString))
                .dontTransform()
                .placeholder(R.drawable.lily_loading_img)
                .error(R.drawable.lily_error_img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void loadWithoutMemoryCache(ImageView imageView, String urlString) {
        GlideApp.with(imageView).load(getGlideUrl(urlString)).skipMemoryCache(true)
                .dontTransform()
                .placeholder(R.drawable.lily_loading_img)
                .error(R.drawable.lily_error_img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void setImageView(ImageView imageView, String urlString) {
        GlideApp.with(imageView).load(getGlideUrl(urlString))
                .centerCrop()
                .dontAnimate()
                .placeholder(R.drawable.lily_loading_img)
                .error(R.drawable.lily_error_img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void setCircularImage(ImageView imageView, String urlString) {
        GlideApp.with(imageView).load(getGlideUrl(urlString)).circleCrop()
                .placeholder(R.drawable.ic_circle_avatar)
                .error(R.drawable.ic_circle_avatar)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    private static GlideUrl getGlideUrl(String urlString) {
        return new GlideUrl(urlString, new LazyHeaders.Builder()
                .addHeader("Cache-Control", "max-age=" + (60 * 60 * 24 * 365) )
                .addHeader("Referer", "https://m.dmzj.com/classify.html")
                .addHeader("User-Agent", "Mozilla/5.0 (Linux; Android 8.0; Pixel 2 Build/OPD3.170816.012) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Mobile Safari/537.36")
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .build());
    }

    public static int dip2px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int preloadImage(Context context, String[] urls, int position, int preloadNum) {
        int loaded;
        for (loaded = 0; loaded < preloadNum; loaded++) {
            if (position+loaded >= urls.length) break;
            GlideApp.with(context).load(getGlideUrl(urls[position+loaded]))
                    .placeholder(R.drawable.lily_loading_img)
                    .error(R.drawable.lily_error_img)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .preload();
            Log.d(TAG, "preloadImage: " + urls[position+loaded]);
        }
        return loaded;

    }

}
