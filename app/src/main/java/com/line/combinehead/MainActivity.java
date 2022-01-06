package com.line.combinehead;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.line.combinehead.combinehead.CombineHeadUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button btnSwitch;
    int count = 9;
    static Queue<String> urls = new LinkedList<>();
    static {
        urls.add("https://img2.baidu.com/it/u=1495842112,3628342104&fm=26&fmt=auto");
        urls.add("https://img2.baidu.com/it/u=426967422,3059596943&fm=26&fmt=auto");
        urls.add("https://img2.baidu.com/it/u=426967422,3059596943&fm=26&fmt=auto");
        urls.add("https://img2.baidu.com/it/u=426967422,3059596943&fm=26&fmt=auto");
        urls.add("https://img2.baidu.com/it/u=426967422,3059596943&fm=26&fmt=auto");
        urls.add("https://img2.baidu.com/it/u=426967422,3059596943&fm=26&fmt=auto");
        urls.add("https://img2.baidu.com/it/u=426967422,3059596943&fm=26&fmt=auto");
        urls.add("https://img2.baidu.com/it/u=426967422,3059596943&fm=26&fmt=auto");
        urls.add("https://img2.baidu.com/it/u=426967422,3059596943&fm=26&fmt=auto");
        urls.add("https://img2.baidu.com/it/u=426967422,3059596943&fm=26&fmt=auto");
    }

    private List<Bitmap> bitmapList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.image);
        btnSwitch = findViewById(R.id.btn_switch);
        bitmapList = new ArrayList<>();
        imageView.post(new Runnable() {
            @Override
            public void run() {
                final int imageSize = imageView.getWidth();
                loadBitmap(new Observer() {
                    @Override
                    public void update(Observable o, Object arg) {
                        Bitmap head = CombineHeadUtil.getCombinedHead(bitmapList, imageSize, 10, Color.LTGRAY);
                        imageView.setImageBitmap(head);
                        btnSwitch.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        btnSwitch.setText(String.valueOf(count));
        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = (count + 1) % 10;
                List<Bitmap> bitmaps = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    bitmaps.add(bitmapList.get(i));
                }
                Bitmap head = CombineHeadUtil.getCombinedHead(bitmaps, imageView.getWidth(), 10, Color.LTGRAY);
                imageView.setImageBitmap(head);
                btnSwitch.setText(String.valueOf(count));
            }
        });
    }

    private void loadBitmap(final Observer observer) {
        Glide.with(this).load(urls.poll()).asBitmap().override(100, 100).listener(new RequestListener<String, Bitmap>() {
            @Override
            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                return true;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                bitmapList.add(resource);
                if(urls.size() > 0){
                    loadBitmap(observer);
                } else {
                    observer.update(null, null);
                }
                return false;
            }
        }).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                bitmapList.add(resource);
//                if(urls.size() > 0){
//                    loadBitmap(observer);
//                } else {
//                    observer.update(null, null);
//                }
            }
        });
    }
}
