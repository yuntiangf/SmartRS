package shengzhe.haiyan.smartrs.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

public class MyVerticalScrollView extends ScrollView {

	private View inner;//����ͼ
	private float y;// ����
	private Rect normal = new Rect();// ���οհ�
	
	public MyVerticalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyVerticalScrollView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MyVerticalScrollView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	/***
	 * ���� XML ������ͼ�������.�ú�����������ͼ�������ã�����������ͼ�����֮��. ��ʹ���า���� onFinishInflate
	 * ������ҲӦ�õ��ø���ķ�����ʹ�÷�������ִ��.
	 */
	@Override
	protected void onFinishInflate() {
		if (getChildCount() > 0) {
			inner = getChildAt(0);// ��ȡ�亢��
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		try{
			if (inner != null) {
				commOnTouchEvent(ev); //���ܼ�������ͼ����¼�
				onInterceptTouchEvent(ev);
			}
		}catch(IllegalArgumentException  ex){
			ex.printStackTrace();
		}
		try{

			super.onTouchEvent(ev);

		} catch(IllegalArgumentException ex) {
			ex.printStackTrace();
		}
		return true;
	}

	/***
	 * �����¼�
	 * 
	 * @param ev
	 */

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			y = ev.getY();// ��ȡ���y����
			return false;
		case MotionEvent.ACTION_UP:
			if (isNeedAnimation()) {
				animation();
			}
			return false;
		case MotionEvent.ACTION_MOVE:
			final float preY = y;
			float nowY = ev.getY();
			int deltaY = (int) (preY - nowY);// ��ȡ��������

			y = nowY;
			if(Math.abs(deltaY) > 5){
				// �����������ϻ�������ʱ�Ͳ����ٹ�������ʱ�ƶ�����
				if (isNeedMove()) {
					if (normal.isEmpty()) {
						// �����Σ�Ŀ�ģ����Ǹ���this:�������Ѿ����ˣ����ɿ���ʱ��ǵ�Ҫִ�лع鶯��.
						normal.set(inner.getLeft(), inner.getTop(),
								inner.getRight(), inner.getBottom());
					}
					// �ƶ�����
					inner.layout(inner.getLeft(), inner.getTop() - deltaY / 2,
							inner.getRight(), inner.getBottom() - deltaY / 2);
				}
				return true;
			}
			return false;
		default:
			break; 
		}
		return false;
	}


	public void commOnTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			y = ev.getY();// ��ȡ���y����
			break;
		case MotionEvent.ACTION_UP:
			if (isNeedAnimation()) {
				animation();
			}
			break;
		case MotionEvent.ACTION_MOVE:
			final float preY = y;
			float nowY = ev.getY();
			int deltaY = (int) (preY - nowY);// ��ȡ��������

			y = nowY;
			// �����������ϻ�������ʱ�Ͳ����ٹ�������ʱ�ƶ�����
			if (isNeedMove()) {
				if (normal.isEmpty()) {
					// �����Σ�Ŀ�ģ����Ǹ���this:�������Ѿ����ˣ����ɿ���ʱ��ǵ�Ҫִ�лع鶯��.
					normal.set(inner.getLeft(), inner.getTop(),
							inner.getRight(), inner.getBottom());
				}
				// �ƶ�����
				inner.layout(inner.getLeft(), inner.getTop() - deltaY / 2,
						inner.getRight(), inner.getBottom() - deltaY / 2);
			}
			break;
		default:
			break;
		}
	}

	/***
	 * ���������ƶ�
	 */
	public void animation() {
		// �����ƶ�����
		TranslateAnimation ta = new TranslateAnimation(0, 0, inner.getTop(),
				normal.top);
		ta.setDuration(300);
		inner.startAnimation(ta);
		// ���ûص������Ĳ���λ��
		inner.layout(normal.left, normal.top, normal.right, normal.bottom);
		normal.setEmpty();// ��վ���

	}

	/***
	 * �Ƿ���Ҫ��������
	 * 
	 * ������β�Ϊ�գ�����true�����򷵻�false.
	 * 
	 * 
	 * @return
	 */
	public boolean isNeedAnimation() {
		return !normal.isEmpty();
	}

	/***
	 * �Ƿ���Ҫ�ƶ����� inner.getMeasuredHeight():��ȡ���ǿؼ��ĸ߶�
	 * getHeight()����ȡ���ǵ�ǰ�ؼ�����Ļ����ʾ�ĸ߶�
	 * 
	 * @return
	 */
	public boolean isNeedMove() {
		int offset = inner.getMeasuredHeight() - getHeight();
		int scrollY = getScrollY();
		// 0�Ƕ����������Ǹ��ǵײ�
		if (scrollY == 0 || scrollY == offset) {
			return true;
		}
		return false;
	}

	@Override
	protected boolean onRequestFocusInDescendants(int direction,
			Rect previouslyFocusedRect) {
		return false;
	}
}
