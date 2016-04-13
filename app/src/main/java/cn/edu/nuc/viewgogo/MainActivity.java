package cn.edu.nuc.viewgogo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ImgView mImgView=null;
    private int mScreenWidth=0;
    private int mScreenHeight=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
//        DisplayMetrics metrics=new DisplayMetrics();
//        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        mScreenHeight=metrics.heightPixels;
//        mScreenWidth=metrics.widthPixels;
//        mImgView= (ImgView) findViewById(R.id.iv);
//        Bitmap bitmap= BitmapFactory.decodeResource(this.getResources(),R.drawable.test04);
//        bitmap=Bitmap.createScaledBitmap(bitmap,mScreenWidth,mScreenHeight,true);
//        mImgView.setmBitmap(bitmap);
    }
}
