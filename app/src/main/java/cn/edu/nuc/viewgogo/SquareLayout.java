package cn.edu.nuc.viewgogo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by weifucheng on 2016/4/13.
 */
public class SquareLayout extends ViewGroup {
    private static final int ORIENTATION_HORIZONTAL=0;
    private static final int ORIENTATION_VERTICEL=0;
    private static final int DEFAULT_MAX_ROW=Integer.MAX_VALUE;
    private static final int DEFAULT_MAX_COLUM=Integer.MAX_VALUE;

    private int mMaxRow=DEFAULT_MAX_ROW;
    private int mMaxcolum=DEFAULT_MAX_COLUM;

    private int mOrientation=ORIENTATION_HORIZONTAL;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parentDesirWidth=0;
        int parentDesirHeight=0;
        int childMeasureState=0;
        if(getChildCount()>0){
            for(int i=0;i<getChildCount();i++){
                View child=getChildAt(i);
                if(child.getVisibility()!=View.GONE){
                    measureChildWithMargins(child,widthMeasureSpec,0,heightMeasureSpec,0);
                    int childMeasureSize=Math.max(child.getMeasuredWidth(),child.getMeasuredHeight());
                    int childMeasureSpec=MeasureSpec.makeMeasureSpec(childMeasureSize,MeasureSpec.EXACTLY);
                    child.measure(childMeasureSize,childMeasureSize);
                    MarginLayoutParams mlp= (MarginLayoutParams) child.getLayoutParams();
                    int childActualWidth=child.getMeasuredWidth()+mlp.leftMargin+mlp.rightMargin;
                    int childActualHeight=child.getMeasuredHeight()+mlp.topMargin+mlp.bottomMargin;
                    if(mOrientation == ORIENTATION_HORIZONTAL){
                        parentDesirWidth+=childActualWidth;
                        parentDesirHeight=Math.max(parentDesirHeight,childActualHeight);
                    }else if(mOrientation == ORIENTATION_VERTICEL){
                        parentDesirHeight+=childActualHeight;
                        parentDesirWidth=Math.max(parentDesirWidth,childActualWidth);
                    }
                    childMeasureState=combineMeasuredStates(childMeasureState,child.getMeasuredState());
                }
            }
            parentDesirWidth+=getPaddingLeft()+getPaddingRight();
            parentDesirHeight+=getPaddingTop()+getPaddingBottom();
            parentDesirWidth=Math.max(parentDesirWidth,getSuggestedMinimumWidth());
            parentDesirHeight=Math.max(parentDesirHeight,getSuggestedMinimumHeight());
        }
            setMeasuredDimension(resolveSizeAndState(parentDesirWidth,widthMeasureSpec,childMeasureState),
                                resolveSizeAndState(parentDesirHeight,heightMeasureSpec,childMeasureState<<MEASURED_HEIGHT_STATE_SHIFT));
    }

    public SquareLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SquareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof MarginLayoutParams;
    }
}
