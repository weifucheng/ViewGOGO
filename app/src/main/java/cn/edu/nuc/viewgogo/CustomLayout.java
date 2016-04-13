package cn.edu.nuc.viewgogo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by weifucheng on 2016/4/11.
 */
public class CustomLayout extends ViewGroup {
    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        int parentDesireWidth=0;
        int parentDesireHeight=0;
        if(getChildCount()>0){
            for(int i=0;i<getChildCount();i++){
                View child=getChildAt(i);
                CustomLayoutParams clp= (CustomLayoutParams) child.getLayoutParams();
                measureChildWithMargins(child,widthMeasureSpec,0,heightMeasureSpec,0);
                parentDesireWidth=((child.getMeasuredWidth()+clp.leftMargin+clp.rightMargin)>parentDesireWidth)?
                        (child.getMeasuredWidth()+clp.leftMargin+clp.rightMargin):parentDesireWidth;
                parentDesireHeight+=child.getMeasuredHeight()+clp.topMargin+clp.bottomMargin;
                Log.d("weifucheng",parentDesireWidth+"");
            }
            parentDesireWidth+=getPaddingLeft()+getPaddingRight();
            parentDesireHeight+=getPaddingBottom()+getPaddingTop();
            Log.d("weifucheng",parentDesireWidth+"");
            parentDesireWidth=Math.max(parentDesireWidth,getSuggestedMinimumWidth());
            parentDesireHeight=Math.max(parentDesireHeight,getSuggestedMinimumHeight());
            Log.d("weifucheng",parentDesireWidth+"");
        }
        setMeasuredDimension(parentDesireWidth,parentDesireHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int parentPaddingLeft=getPaddingLeft();
        int parentPaddingTop=getPaddingTop();
        if(getChildCount()>0){
            int mutilHeight=0;
            for(int i=0;i< getChildCount();i++){
                View child=getChildAt(i);
                CustomLayoutParams clp= (CustomLayoutParams) child.getLayoutParams();

                child.layout(parentPaddingLeft+clp.leftMargin,
                        mutilHeight+parentPaddingTop+clp.topMargin,
                        child.getMeasuredWidth()+parentPaddingLeft+clp.leftMargin,
                        child.getMeasuredHeight()+mutilHeight+parentPaddingTop+clp.topMargin);
                mutilHeight+=child.getMeasuredHeight()+clp.topMargin+clp.bottomMargin;
            }
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new CustomLayoutParams(getContext(),attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new CustomLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new CustomLayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof CustomLayoutParams;
    }

    public static class CustomLayoutParams extends MarginLayoutParams {
        public CustomLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public CustomLayoutParams(int width, int height) {
            super(width, height);
        }

        public CustomLayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public CustomLayoutParams(LayoutParams source) {
            super(source);
        }
    }
}
