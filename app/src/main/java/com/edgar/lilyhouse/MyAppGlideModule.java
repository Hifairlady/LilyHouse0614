package com.edgar.lilyhouse;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

@GlideModule
public class MyAppGlideModule extends AppGlideModule {

//    @Override
//    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
//        super.applyOptions(context, builder);
//
//        //no memory cache
//        int bitmapPoolSizeBytes = 1024 * 1024 * 0; // 0mb
//        int memoryCacheSizeBytes = 1024 * 1024 * 0; // 0mb
//        builder.setMemoryCache(new LruResourceCache(memoryCacheSizeBytes));
//        builder.setBitmapPool(new LruBitmapPool(bitmapPoolSizeBytes));
//    }
}
