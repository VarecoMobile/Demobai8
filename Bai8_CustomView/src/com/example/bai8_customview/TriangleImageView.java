package com.example.bai8_customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class TriangleImageView extends ImageView {

	public TriangleImageView(Context ctx, AttributeSet attrs) {
		super(ctx, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		Drawable drawable = getDrawable();

		if (drawable == null) {
			return;
		}

		if (getWidth() == 0 || getHeight() == 0) {
			return;
		}
		Bitmap b = ((BitmapDrawable) drawable).getBitmap();
		Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);

		int w = getWidth(), h = getHeight();

		Bitmap roundBitmap = getRoundedBitmap(bitmap, w);
		canvas.drawBitmap(roundBitmap, 0, 0, null);

	}

	public static Bitmap getRoundedBitmap(Bitmap bitmap, int radius) {
		Bitmap finalBitmap;
		if (bitmap.getWidth() != radius || bitmap.getHeight() != radius)
			finalBitmap = Bitmap.createScaledBitmap(bitmap, radius, radius,
					false);
		else
			finalBitmap = bitmap;
		Bitmap output = Bitmap.createBitmap(finalBitmap.getWidth(),
				finalBitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, finalBitmap.getWidth(),
				finalBitmap.getHeight());
		//Căn chỉnh toa độ (x,y)
		Point point1_draw = new Point(75, 0);
		Point point2_draw = new Point(0, 180);
		Point point3_draw = new Point(180, 180);

		Path path = new Path();
		//�?ặt đầu của đư�?ng vi�?n bên cạnh điểm(x,y)
		path.moveTo(point1_draw.x, point1_draw.y);
		// thêm 1 đư�?ng từ điểm cuối đến điểm quy định
		path.lineTo(point2_draw.x, point2_draw.y);
		path.lineTo(point3_draw.x, point3_draw.y);
		path.lineTo(point1_draw.x, point1_draw.y);
		path.close();
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(Color.parseColor("#BAB399"));
		canvas.drawPath(path, paint);
		//pt thực hiện vẽ các đư�?ng cụ thể
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(finalBitmap, rect, rect, paint);

		return output;
	}

}