package shengzhe.haiyan.smartrs.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

public class CircleImageView extends MaskedImage{

	private int cornerRadius;
	public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	public CircleImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public CircleImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	private void init() {
		this.cornerRadius = (int) (100.0F * getScreenDensity(getContext()));
	}
	
	private float getScreenDensity(Context context) {
		// TODO Auto-generated method stub
		return (int) (160.0F * context.getApplicationContext()
				.getResources().getDisplayMetrics().density);
	}

	@Override
	public Bitmap createMask() {
		// TODO Auto-generated method stub
		Bitmap localBitmap = Bitmap.createBitmap(getWidth(), getHeight(),
				Bitmap.Config.ARGB_4444);
		Canvas localCanvas = new Canvas(localBitmap);
		Paint localPaint = new Paint(1);
		localPaint.setColor(-16777216);
		int i = dipToPx(getContext(), this.cornerRadius);
		localCanvas.drawRoundRect(
				new RectF(0.0F, 0.0F, getWidth(), getHeight()), i, i,
				localPaint);
		return localBitmap;
	}

	public int dipToPx(Context paramContext, int paramInt) {
		return roundUp(paramInt
				* paramContext.getResources().getDisplayMetrics().density);
	}
	public int roundUp(float paramFloat) {
		return (int) (0.5F + paramFloat);
	}
}
