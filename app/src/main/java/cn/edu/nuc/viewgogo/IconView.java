package cn.edu.nuc.viewgogo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by weifucheng on 2016/4/11.
 */
public class IconView extends View {

    private Bitmap mBitmap;
    private TextPaint mPaint;
    private String mStr;

    private float mTextSize;
    private enum Ratio{
        WIDTH,HEIGHT
    }
    public IconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        calArgs(context);
        init(context);
    }
    private void calArgs(Context context){
        int sreenW=MeasureUtil.getScreenSize((Activity) context)[0];
        mTextSize=sreenW*1/10F;
    }
    private void init(Context context){
        if(null==mBitmap){
            mBitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.test06);
        }
        if(null==mStr||mStr.trim().length()==0){
            mStr="ViewGOGO";
        }
        mPaint=new TextPaint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG|Paint.LINEAR_TEXT_FLAG);
        mPaint.setColor(Color.LTGRAY);
        mPaint.setTextSize(mTextSize);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasureSize(widthMeasureSpec,Ratio.WIDTH),getMeasureSize(heightMeasureSpec,Ratio.HEIGHT));
    }

    private int getMeasureSize(int measureSpec, Ratio ratio){
        int result=0;
        int mode=MeasureSpec.getMode(measureSpec);
        int size=MeasureSpec.getSize(measureSpec);
        switch (mode){
            case MeasureSpec.EXACTLY:
                result = size;
                break;
            default:
                if(ratio==Ratio.WIDTH){
                    float textWidth = mPaint.measureText(mStr);
                    result= (int) ((textWidth>mBitmap.getWidth()?textWidth:mBitmap.getWidth()))
                            +getPaddingLeft()+getPaddingRight();
                }else if(ratio==Ratio.HEIGHT){
                    result= (int) ((mPaint.descent()-mPaint.ascent())*2+mBitmap.getHeight())
                            +getPaddingTop()+getPaddingBottom();
                }
                if(mode==MeasureSpec.AT_MOST){
                    result=Math.min(result,size);
                }
                break;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap,getWidth()/2-mBitmap.getWidth()/2,getHeight()/2-mBitmap.getWidth()/2,null);
        canvas.drawText(mStr,getWidth()/2,mBitmap.getHeight()+getHeight()/2-mBitmap.getHeight()/2-mPaint.ascent(),mPaint);
    }
}
