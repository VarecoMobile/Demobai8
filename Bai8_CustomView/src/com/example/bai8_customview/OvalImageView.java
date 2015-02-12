package com.example.bai8_customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class OvalImageView extends ImageView {

	public OvalImageView(Context ctx, AttributeSet attrs) {

		super(ctx, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {// phương thức 
										//tiến hành vẽ
		Drawable drawable = getDrawable();
	if (drawable == null) {		return;		}
	if (getWidth() == 0 || getHeight() == 0) {	return;	}
		// thiết lập cấu hình bitmap mới, và sao chép các
		// điểm ảnh của bitmap này vào bitmap mới.
		Bitmap b = ((BitmapDrawable) drawable).getBitmap();
		Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);
		int w = getWidth(), h = getHeight();
		Bitmap roundBitmap = getOvalBitmap(bitmap, w);
		canvas.drawBitmap(roundBitmap, 0, 0, null);// hien thi
	}

	public static Bitmap getOvalBitmap(Bitmap bitmap, int radius) {
		Bitmap finalBitmap;
		if (bitmap.getWidth() != radius || bitmap.getHeight() != radius) {
			finalBitmap = Bitmap
					.createScaledBitmap(bitmap, radius, radius,
					false);// tao 1 bitmap va trả lại một phiên bản thu nh�? của
							// hình ảnh mà có
							// kích thước theo kích thước được cung cấp
		} else
			finalBitmap = bitmap;
		// ham này trả v�? 1 Bitmap có thể thay đổi với chi�?u rộng và chi�?u cao
		// quy định
		Bitmap output = Bitmap.createBitmap(finalBitmap.getWidth(),
				finalBitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);// khai bao canvas :nhiệm vụ nó là để
											// cho chúng ta vẽ lên
		Paint paint = new Paint();// khai báo paint để vẽ lên canvas đó

		final Rect rect = new Rect(0, 0, finalBitmap.getWidth(),
				finalBitmap.getHeight());// Tạo một hình chữ nhật mới với các
											// t�?a độ quy định.

		// làm mịn các cạnh, góc
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);

		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(Color.parseColor("#BAB399"));
		RectF oval = new RectF(0, 0, 120, 150);// căn chỉnh
		canvas.drawOval(oval, paint);//vẽ hình bầu dục

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(finalBitmap, rect, oval, paint);//vẽ bitmap

		return output;
	}

}