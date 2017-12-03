package com.line.combinehead;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.line.combinehead.combinehead.CombineHeadUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    static Queue<String> urls = new LinkedList<>();
    static {
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512702265&di=37b926d77df562f97e26f1598a6e0d92&imgtype=jpg&er=1&src=http%3A%2F%2Fpic27.photophoto.cn%2F20130601%2F0036036307762415_b.jpg");
        urls.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=272162387,1882373772&fm=27&gp=0.jpg");
        urls.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1841735587,1754178892&fm=27&gp=0.jpg");
        urls.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=4077282133,9308138&fm=27&gp=0.jpg");
        urls.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3141321719,4227905030&fm=27&gp=0.jpg");
        urls.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3265039697,1790980665&fm=27&gp=0.jpg");
        urls.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=4042920819,4259410653&fm=27&gp=0.jpg");
        urls.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4249499471,3103145698&fm=27&gp=0.jpg");
        urls.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2846910142,166539610&fm=27&gp=0.jpg");
    }

    private List<Bitmap> bitmapList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.image);
        bitmapList = new ArrayList<>();
        imageView.post(new Runnable() {
            @Override
            public void run() {
                final int imageSize = imageView.getWidth();
                loadBitmap(new Observer() {
                    @Override
                    public void update(Observable o, Object arg) {
                        Bitmap head = CombineHeadUtil.getConbineHead(bitmapList, imageSize, 10, Color.LTGRAY);
                        imageView.setImageBitmap(head);
                    }
                });
            }
        });


    }

    private void loadBitmap(final Observer observer) {
        Glide.with(this).load(urls.poll()).asBitmap().override(100, 100).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                bitmapList.add(resource);
                if(urls.size() > 0){
                    loadBitmap(observer);
                } else {
                    observer.update(null, null);
                }
            }
        });

    }
}
