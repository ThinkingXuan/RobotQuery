package com.njcit.robotquery.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * http://limuzhi.com/2016/01/24/Android%E5%9B%BE%E7%89%87%E5%BA%93-Glide/
 * <p/>
 * http://vardhan-justlikethat.blogspot.jp/2014/09/android-image-loading-libraries-picasso.html
 * <p/>
 * Created by Administrator on 2016/5/10.
 */
public class ImageUtils {


    /**
     * 圆图
     *
     * @param source
     * @return
     */
    public static Bitmap getCircularBitmapImage(Bitmap source) {
        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;
        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
        if (squaredBitmap != source) {
            source.recycle();
        }
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);
        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);
        squaredBitmap.recycle();
        return bitmap;
    }

    /**
     * 聊天对话的使用
     *
     * @param context
     * @param imageView
     * @param uri
     */

//    public static void loadChatUserImg(Context context, ImageView imageView, String uri) {
//        Glide.with(context.getApplicationContext())
//                .load(uri)
//                .centerCrop()
//                .transform(new GlideCircleTransform(context.getApplicationContext()))
//                .thumbnail(0.1f)//缩略图
//                //.placeholder(R.drawable.ic_placeholder)//设置加载中图片
//                //.error(R.drawable.ic_error)//设置错误图片
//                .into(imageView);
//    }
//
//    /**
//     * 聊天对话的使用
//     *
//     * @param context
//     * @param imageView
//     * @param uri
//     */
//    public static void loadChatUserImg(Context context, ImageView imageView, int uri) {
//        Glide.with(context.getApplicationContext())
//                .load(uri)
//                .centerCrop()
//                .transform(new GlideCircleTransform(context.getApplicationContext()))
//                .thumbnail(0.1f)//缩略图
//                //.placeholder(R.drawable.ic_placeholder)//设置加载中图片
//                //.error(R.drawable.ic_error)//设置错误图片
//                .into(imageView);
//    }

    /**
     * 针对其他读取图片使用
     *
     * @param context
     * @param imageView
     * @param uri
     */
    public static void loadImg(Context context, ImageView imageView, String uri) {
        Glide.with(context.getApplicationContext())
                .load(uri)
                .centerCrop()
                //.transform(new GlideCircleTransform(context.getApplicationContext()))
                .thumbnail(0.1f)//缩略图
//                .placeholder(R.drawable.ic_placeholder)//设置加载中图片
//                .error(R.drawable.ic_error)//设置错误图片
                .into(imageView);
    }

    /**
     * 加载本地图片
     *
     * @param context
     * @param imageView
     * @param uri
     */
    public static void loadImgResourceId(Context context, ImageView imageView, int uri) {
        Glide.with(context.getApplicationContext())
                .load(uri)
                .centerCrop()
                //.transform(new GlideCircleTransform(context.getApplicationContext()))
                //.thumbnail(0.5f)//缩略图
                //.placeholder(R.drawable.ic_placeholder)//设置加载中图片
                //.error(R.drawable.ic_error)//设置错误图片
                .into(imageView);
    }

}
