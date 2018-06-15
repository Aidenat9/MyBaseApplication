package com.tmgg.xbaselibrary.utils.glideu;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by 皓然 on 2017/8/19.
 */

public class GlideImageLoader {

    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
//                .placeholder(R.mipmap.pic_default)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);
    }
    public static void loadImage(Context context, int resid, ImageView imageView) {
        Glide.with(context)
                .load(resid)
//                .placeholder(R.mipmap.pic_default)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);
    }

    public static void loadImage(Context context, int resid, ImageView imageView, boolean isCenter) {
        Glide.with(context)
                .load(resid)
//                .placeholder(R.mipmap.pic_default)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);
    }

    public static void loadRoundImage(Context context, String url, ImageView mCover) {
        Glide.with(context).load(url).centerCrop().transform(new GlideCircleTransform(context)).into(mCover);
    }
}
