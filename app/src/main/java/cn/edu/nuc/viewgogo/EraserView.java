package cn.edu.nuc.viewgogo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by weifucheng on 2016/4/10.
 */
public class EraserView extends View {
    private Bitmap mFgBitmap;
    private Bitmap mBgBitmap;
    private Canvas mCanvas;
    private Paint mPaint;
    private Path mPath;
    private int mScreenW;
    private int mScreenH;

    public EraserView(Context context) {
        super(context);
    }

    public EraserView(Context context, AttributeSet attrs) {
        super(context, attrs);
        cal(context);
        init(context);
    }
    private void cal(Context context){
         int[] ScreenSize=MeasureUtil.getScreenSize((Activity) context);
         mScreenW=ScreenSize[0];
         mScreenH=ScreenSize[1];
    }
    private void init(Context context){
        mPath=new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mPaint.setARGB(0,255,0,255);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setStyle(Paint.Style.STROKE);
        //mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(80);
        mFgBitmap=Bitmap.createBitmap(mScreenW,mScreenH, Bitmap.Config.ARGB_8888);
        mCanvas=new Canvas(mFgBitmap);
        mCanvas.drawColor(0xFF808080);
        mBgBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.test05);
        mBgBitmap=Bitmap.createScaledBitmap(mBgBitmap,mScreenW,mScreenH,true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBgBitmap,0,0,null);
        canvas.drawBitmap(mFgBitmap,0,0,null);
        mCanvas.drawPath(mPath,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        float y=event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x,y);
                break;
            case MotionEvent.ACTION_UP:
                mPath.reset();
            default:break;
        }
        invalidate();
        return true;
    }
}
