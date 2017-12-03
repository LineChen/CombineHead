package com.line.combinehead.combinehead;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.ColorInt;

import java.util.List;

/**
 * Created by chenliu on 2017/12/2.
 */

public class CombineHeadUtil {

    static class LeftTop{
        float left;
        float top;
    }

    /**
     *
     * @param headBitmaps 数据源
     * @param imageSize 需要生成图片的边长
     * @param spaceing 间隔大小
     * @param bgColor   背景色
     * @return
     */
    public static Bitmap getConbineHead(List<Bitmap> headBitmaps, int imageSize, int spaceing, @ColorInt int bgColor){
        if(headBitmaps == null || headBitmaps.size() == 0)  return null;
        int count = Math.min(headBitmaps.size(), 9);
        int itemwidth = getItemWidth(count, imageSize, spaceing);
        Bitmap groupAvator = Bitmap.createBitmap(imageSize, imageSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(groupAvator);
        canvas.drawColor(bgColor);
        LeftTop leftTop = new LeftTop();
        for (int i = 0; i < count; i++) {
            Bitmap bitmap = headBitmaps.get(i);
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Matrix matrix = new Matrix();
            matrix.postScale(itemwidth * 1.0f / width, itemwidth * 1.0f / height);
            Bitmap newBit = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
            calculateLeftTop(count, imageSize, itemwidth, spaceing, i, leftTop);
            canvas.drawBitmap(newBit, leftTop.left, leftTop.top, null);
        }
        canvas.save();
        canvas.restore();
        return groupAvator;
    }

    private static int getItemWidth(int count, int imageSize, int spaceing) {
        int width = 0;
        if(count == 1){
            width = imageSize - spaceing * 2;
        } else if(count <= 4){
            width = (imageSize - 3 * spaceing) / 2;
        } else if(count <= 9){
            width = (imageSize - 4 * spaceing) / 3;
        }
        return width;
    }

    private static void calculateLeftTop(int count, int imageSize, int itemWidth, int spaceing, int index, LeftTop leftTop){
        if(leftTop == null) return;
        switch (count) {
            case 1:
                leftTop.left = spaceing;
                leftTop.top = spaceing;
                break;
            case 2:
                leftTop.left = index % 2 * itemWidth + spaceing * (index + 1);
                leftTop.top = spaceing;
                break;
            case 3:
                if(index == 0){
                    leftTop.left = (imageSize - itemWidth) / 2;
                    leftTop.top = spaceing;
                } else {
                    leftTop.left = index / 2 * itemWidth + spaceing * index;
                    leftTop.top = itemWidth + spaceing *  2;
                }
                break;
            case 4:
                leftTop.left = index % 2 * itemWidth + (index % 2 + 1) * spaceing;
                leftTop.top = (index / 2) * itemWidth + (index / 2 + 1) * spaceing;
                break;
            case 5:
                if(index < 2){
                    leftTop.left = (imageSize - 2 * itemWidth - spaceing) / 2 + index * itemWidth + index * spaceing;
                    leftTop.top = (imageSize - 2 * itemWidth -  spaceing) / 2;
                } else {
                    leftTop.left = (index - 2) * itemWidth + (index - 1 ) * spaceing;
                    leftTop.top = (imageSize - 2 * itemWidth -  spaceing) / 2 + spaceing + itemWidth;
                }
            break;
            case 6:
                if (index < 3) {
                    leftTop.left = index * itemWidth + (index + 1 ) * spaceing;
                    leftTop.top = (imageSize - 2 * itemWidth -  spaceing) / 2;
                } else {
                    leftTop.left = (index - 3) * itemWidth + (index - 2) * spaceing;
                    leftTop.top = (imageSize - 2 * itemWidth -  spaceing) / 2 + spaceing + itemWidth;
                }
                break;
            case 7:
                    if (index == 0) {
                        leftTop.top = spaceing;
                        leftTop.left = (imageSize - itemWidth) / 2;
                    } else {
                        leftTop.top = (index + 2) / 3 * itemWidth + ((index + 2) / 3 + 1) * spaceing;
                        if(index <= 3){
                            leftTop.left = (index - 1) * itemWidth + index * spaceing;
                        } else {
                            leftTop.left = (index - 4) * itemWidth + (index - 3) * spaceing;
                        }
                    }
            break;
            case 8:
                    if (index < 2) {
                        leftTop.top = spaceing;
                        leftTop.left = (imageSize - 2 * itemWidth - spaceing) / 2 + index * itemWidth + index * spaceing;
                    } else {
                        leftTop.top = (index + 3) / 4 * itemWidth + ((index + 3 ) / 4 + 1) * spaceing;
                        if(index <= 4){
                            leftTop.left = (index - 2) * itemWidth + (index - 1) * spaceing;
                        }else{
                            leftTop.left = (index - 5) * itemWidth + (index - 4) * spaceing;
                        }
                    }
            break;
            case 9:
                    leftTop.left = index % 3 * itemWidth + (index % 3 + 1) * spaceing;
                    leftTop.top = (index / 3) * itemWidth + (index / 3 + 1) * spaceing;
                    break;
        }
    }


}
