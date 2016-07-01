package shengzhe.haiyan.smartrs.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ExpandableListView;

public class ExpandableListViewForScrollView extends ExpandableListView {

	public ExpandableListViewForScrollView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ExpandableListViewForScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ExpandableListViewForScrollView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	/**
	 * ��д�÷������ﵽʹListView��ӦScrollView��Ч��
	 */
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
