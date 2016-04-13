package cn.edu.nuc.viewgogo;

import android.app.Activity;
import android.util.DisplayMetrics;


public final class MeasureUtil {
	public static int[] getScreenSize(Activity activity) {
		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return new int[] { metrics.widthPixels, metrics.heightPixels };
	}
}
