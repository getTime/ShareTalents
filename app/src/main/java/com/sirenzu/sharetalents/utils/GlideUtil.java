package com.sirenzu.sharetalents.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.sirenzu.sharetalents.R;

import java.io.File;


/**
 * Created by ${Yuanyx} on 2016/10/31.
 */

public class GlideUtil {
    /**
     * 加载本地图片
     *
     * @param context
     * @param url
     * @param target
     */
    public static void loadlocalImage(Context context, File url, ImageView target) {
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .into(target);
    }
    /**
     * 加载本地圆形图片
     *
     * @param context
     * @param url
     * @param target
     */
    public static void loadlocalCircleImage(final Context context, File url, final ImageView target) {
        Glide.with(context).load(url).asBitmap().placeholder(R.mipmap.def_avatar).centerCrop().into(new BitmapImageViewTarget(target) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                target.setImageDrawable(circularBitmapDrawable);
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                target.setImageDrawable(errorDrawable);
            }
        });
    }

    /**
     * 加载圆形头像
     *
     * @param context
     * @param url
     */
    public static void loadCircleAvatar(final Context context, String url, final ImageView target) {
//        Glide.with(context)
//                .load(url)
//                .placeholder(R.mipmap.ic_default)
//                .bitmapTransform(new GlideCircleTransform(context))//裁剪圆形
//                .into(target);

        Glide.with(context).load(url).asBitmap().error(R.mipmap.def_avatar).centerCrop().into(new BitmapImageViewTarget(target) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                target.setImageDrawable(circularBitmapDrawable);
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                target.setImageDrawable(errorDrawable);
            }
        });

    }

    /**
     * 加载图片
     *
     * @param context
     * @param url
     */
    public static void loadImage(Context context, String url, ImageView target) {
        Glide.with(context)
                .load(url)
                .error(R.mipmap.ic_launcher)
                .into(target);
    }
}
