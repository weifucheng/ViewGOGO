package cn.edu.nuc.viewgogo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by weifucheng on 2016/4/10.
 */
public class ImgView extends View {
    private Bitmap mBitmap;
    public ImgView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.clipRect(
                getPaddingLeft(),
                getPaddingTop(),
                getRight()-getLeft()-getPaddingRight(),
                getBottom()-getTop()-getPaddingBottom());
        canvas.save();
        canvas.drawBitmap(mBitmap,getPaddingLeft(),getPaddingTop(),null);
    }

    public void setmBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap= Bitmap.createScaledBitmap(mBitmap,w,h,true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int resultWidth=0;
        int modeWidth=MeasureSpec.getMode(widthMeasureSpec);
        int sizeWidth=MeasureSpec.getSize(widthMeasureSpec);
        if(modeWidth==MeasureSpec.EXACTLY){
            resultWidth=sizeWidth;
        }else {
            resultWidth=mBitmap.getWidth();
            if(modeWidth==MeasureSpec.AT_MOST){
                resultWidth=Math.min(resultWidth,sizeWidth);
            }
        }
        int resultHeight=0;
        int modelHeight=MeasureSpec.getMode(widthMeasureSpec);
        int sizeHeight=MeasureSpec.getSize(widthMeasureSpec);
        if(modelHeight==MeasureSpec.EXACTLY){
            resultHeight=sizeWidth;
        }else {
            resultHeight=mBitmap.getWidth();
            if(modeWidth==MeasureSpec.AT_MOST){
                resultHeight=Math.min(resultHeight,sizeWidth);
            }
        }
        setMeasuredDimension(resultWidth,resultHeight);
    }
}
