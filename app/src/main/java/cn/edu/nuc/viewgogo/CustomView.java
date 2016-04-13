package cn.edu.nuc.viewgogo;

import android.content.Context;
import android.graphics.AvoidXfermode;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by weifucheng on 2016/4/9.
 */
public class CustomView extends View implements Runnable{
    private Paint mPaint;
    private Context mContext;
    private int mRadiu;
    private Bitmap mBitmap;
    private Boolean mIsClick=false;
    public CustomView(Context context) {
        super(context);
    }
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initRes(context);
        initPaint();
//        new Thread(this).start();
//        initListener();
    }
    private void initListener(){
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIsClick){
                    mPaint.setColorFilter(null);
                    mIsClick=false;
                }else{
                    mPaint.setColorFilter(new LightingColorFilter(0xffffffff,0x00ffff00));
                    mIsClick=true;
                }
                postInvalidate();
            }
        });
    }
    private void initRes(Context context){
        mContext=context;
        mBitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.test01);
    }
    private void initPaint(){

        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.argb(255,211,53,243));
        mPaint.setStrokeWidth(10);
        ColorMatrix colorMatrix=new ColorMatrix(new float[]{
                1.5f,1.5f,1.5f,0,-1,
                1.5f,1.5f,1.5f,0,-1,
                1.5f,1.5f,1.5f,0,-1,
                0,0,0,1,0,
        });
//          mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
//          mPaint.setColorFilter(new LightingColorFilter(0xffffff00,0x00000000));
//          mPaint.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN));
        mPaint.setXfermode(new AvoidXfermode(0xFFFFFFFF,255, AvoidXfermode.Mode.TARGET));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawCircle(300,300,200,mPaint);
        canvas.drawBitmap(mBitmap,50,50,mPaint);
        //canvas.drawRect(50,50,50+mBitmap.getWidth(),50+mBitmap.getHeight(),mPaint);
    }

    @Override
    public void run() {
        while(true){
            try {
                if(mRadiu<200){
                    mRadiu+=10;
                    postInvalidate();
                }else{
                    mRadiu=0;
                }
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
