package com.liu.tornclothes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView iv;
    private Bitmap bmpCopy;
    private Paint paint;
    private Canvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv= (ImageView) findViewById(R.id.iv);
        //加载外衣图片
        Bitmap bmp= BitmapFactory.decodeResource(getResources(),R.drawable.awaiyi);
        //创建空白bitmap
        bmpCopy=Bitmap.createBitmap(bmp.getWidth(),bmp.getHeight(),bmp.getConfig());
        paint=new Paint();
        canvas=new Canvas(bmpCopy);
        //复制原图片,获得可以编辑的bitmap对象
        canvas.drawBitmap(bmp,new Matrix(),paint);
        iv.setImageBitmap(bmpCopy);

        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    //移动时擦除外衣图片
                    case MotionEvent.ACTION_MOVE:
                        int x= (int) event.getX();
                        int y= (int) event.getY();
                        for(int i=-20;i<20;i++){
                            for(int j=-20;j<20;j++){
                                //以x,y为圆心，20为半径的圆内的所有点置为透明
                                if(i*i+j*j<=400&&x+i>=0&&x+i<bmpCopy.getWidth()&&y+j>=0&&y+j<bmpCopy.getHeight()){
                                    bmpCopy.setPixel(x+i,y+j, Color.TRANSPARENT);
                                }
                            }
                        }

                        //更新ImageView
                        iv.setImageBitmap(bmpCopy);
                        break;
                }
                return true;
            }
        });
    }
}
