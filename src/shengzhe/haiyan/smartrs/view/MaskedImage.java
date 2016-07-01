package shengzhe.haiyan.smartrs.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public abstract class MaskedImage extends ImageView {

	private static final Xfermode MASK_XFERMODE = new PorterDuffXfermode(
			PorterDuff.Mode.DST_IN);
	
	
	public MaskedImage(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MaskedImage(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MaskedImage(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public abstract Bitmap createMask();

	protected void onDraw(Canvas paramCanvas) {
		Drawable localDrawable = getDrawable();
		if (localDrawable == null)
			return;
		Paint localPaint = new Paint();
		localPaint.setFilterBitmap(false);
		int i = paramCanvas.saveLayer(0.0F, 0.0F, getWidth(), getHeight(),
				null, 31);
		localDrawable.setBounds(0, 0, getWidth(), getHeight());
		localDrawable.draw(paramCanvas);
		localPaint.setXfermode(MASK_XFERMODE);
		paramCanvas.drawBitmap(createMask(), 0.0F, 0.0F, localPaint);
		paramCanvas.restoreToCount(i);
	}
}
