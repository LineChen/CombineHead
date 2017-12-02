package com.line.combinehead.combinehead;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.support.annotation.ColorInt;

import java.util.List;

/**
 * Created by chenliu on 2017/12/2.
 */

public class CombineHeadUtil {

    public static class LeftTop{
        public float left;
        public float top;
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
        int widthSize = mSize - edgeWidth * 2;
        int heightSize = mSize - edgeWidth * 2;
        Bitmap groupAvator = Bitmap.createBitmap(mSize, mSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(groupAvator);
        canvas.drawColor(Color.LTGRAY);
        for (int i = 0; i < count; i++) {
            Bitmap bitmap = data.get(i);
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Matrix matrix = new Matrix();
            matrix.postScale(widthSize * 1.0f / width, heightSize * 1.0f / height);
            Bitmap newBit = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
            canvas.drawBitmap(newBit, edgeWidth, edgeWidth, null);
        }
        canvas.save();
        canvas.restore();

        return null;
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
            case 6: {
                    float left = 0;
                    float top = 0;
                    if (i < 3) {
                        top = (mSize - 2 * heightSize -  edgeWidth) / 2;
                        left = i * widthSize + (i + 1 ) * edgeWidth;
                    } else {
                        top = (mSize - 2 * heightSize -  edgeWidth) / 2 + edgeWidth + heightSize;
                        left = (i - 3) * widthSize + (i - 2) * edgeWidth;
                    }
            }
            case 7: {
                    float left = 0;
                    float top = 0;
                    if (i == 0) {
                        top = edgeWidth;
                        left = (mSize - widthSize) / 2;
                    } else {
                        top = (i + 2) / 3 * heightSize + ((i + 2) / 3 + 1) * edgeWidth;
                        if(i <= 3){
                            left = (i - 1) * widthSize + i * edgeWidth;
                        } else {
                            left = (i - 4) * widthSize + (i - 3) * edgeWidth;
                        }
                    }
            }
            case 8: {
                    float left = 0;
                    float top = 0;
                    if (i < 2) {
                        top = edgeWidth;
                        left = (mSize - 2 * widthSize - edgeWidth) / 2 + i * widthSize + i * edgeWidth;
                    } else {
                        top = (i + 3) / 4 * heightSize + ((i + 3 ) / 4 + 1) * edgeWidth;
                        if(i <= 4){
                            left = (i - 2) * widthSize + (i - 1) * edgeWidth;
                        }else{
                            left = (i - 5) * widthSize + (i - 4) * edgeWidth;
                        }
                    }
            }
            case 9: {
                    float left = i % 3 * widthSize + (i % 3 + 1) * edgeWidth;
                    float top = (i / 3) * heightSize + (i / 3 + 1) * edgeWidth;
        }
    }


}
