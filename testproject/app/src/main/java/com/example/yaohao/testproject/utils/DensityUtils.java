package com.example.yaohao.testproject.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

/**
 * 常用单位转换的辅助类
 * 
 * @author gch
 * 
 */
public class DensityUtils
{
	private DensityUtils()
	{
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * dp转px
	 * 
	 * @param context
	 * @return
	 */
	public static int dp2px(Context context, float dpVal)
	{
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dpVal, context.getResources().getDisplayMetrics());
	}

	/**
	 * sp转px
	 * 
	 * @param context
	 * @return
	 */
	public static int sp2px(Context context, float spVal)
	{
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
				spVal, context.getResources().getDisplayMetrics());
	}

	/**
	 * px转dp
	 * 
	 * @param context
	 * @param pxVal
	 * @return
	 */
	public static float px2dp(Context context, float pxVal)
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (pxVal / scale);
	}

	/**
	 * px转sp
	 * 
	 * @param pxVal
	 * @return
	 */
	public static float px2sp(Context context, float pxVal)
	{
		return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
	}



	/**
	 * draw 9Path
	 *
	 * @param canvas Canvas
	 * @param bmp 9path bitmap
	 * @param rect 9path rect
	 */
	public static void drawNinePath(Canvas canvas, Bitmap bmp, Rect rect) {
		NinePatch patch = new NinePatch(bmp, bmp.getNinePatchChunk(), null);
		patch.draw(canvas, rect);
	}

	/**
	 *  make a drawable to a bitmap
	 * @param drawable drawable you want convert
	 * @return converted bitmap
	 */
	public static Bitmap drawableToBitmap(int size, Drawable drawable) {
		Bitmap bitmap = null;
		if (drawable instanceof BitmapDrawable) {
			BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
			bitmap = bitmapDrawable.getBitmap();
			if (bitmap != null && bitmap.getHeight() > 0) {
				Matrix matrix = new Matrix();
				float scaleHeight = size * 1.0f / bitmapDrawable.getIntrinsicHeight();
				matrix.postScale(scaleHeight, scaleHeight);
				bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
				return bitmap;
			}
		}
		bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
		drawable.draw(canvas);
		return bitmap;
	}

	/**
	 * Compare the size of two floating point numbers
	 * @param a
	 * @param b
	 * @return 1 is a > b
	 * -1 is a < b
	 * 0 is a == b
	 */
	public static int compareFloat(float a, float b) {
		int ta = Math.round(a * 100000);
		int tb = Math.round(b * 100000);
		if (ta > tb) {
			return 1;
		} else if (ta < tb) {
			return -1;
		} else {
			return 0;
		}
	}

}
