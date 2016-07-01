package shengzhe.haiyan.smartrs.view;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.RelativeLayout;

//˫�򻬶��˵����
public class BidirSlidingLayout extends RelativeLayout implements OnTouchListener {

	public static final int SNAP_VELOCITY = 200;			//����ʱ����Ҫ�ﵽ���ٶ�
	public static final int DO_NOTHING = 0;					//����״̬��һ�֣���ʾδ�����κλ���
	public static final int SHOW_LEFT_MENU = 1;				//����״̬��һ�֣���ʾ���ڻ������˵�
	public static final int SHOW_RIGHT_MENU = 2;			//����״̬��һ�֣���ʾ���ڻ����Ҳ�˵�
//	public static final int HIDE_LEFT_MENU = 3;				//����״̬��һ�֣���ʾ�����������˵�
//	public static final int HIDE_RIGHT_MENU = 4;			//����״̬��һ�֣���ʾ���������Ҳ�˵�
	private int slideState;									//��¼��ǰ�ĵĻ���״̬
	private int screenWidth;								//��Ļ�Ŀ��
	private int touchSlop;									//�ڱ��ж�Ϊ����ǰ��ָ�����ƶ������ֵ
	private float xDown;									//��ָ����ʱ�ĺ�����
	private float xMove;									//��ָ�ƶ�ʱ�ĺ�����
	private float xUp;										//��ָ̧��ʱ�ĺ�����
	private float yDown;									//��ָ����ʱ��������
	private float yMove;									//��ָ�ƶ�ʱ��������
	//���˵���ǰ����ʾ�������ء�ֻ����ȫ��ʾ������ʱ�Ż���Ĵ�ֵ�����������д�ֵ��Ч
	private boolean isLeftMenuVisible;
	//�Ҳ�˵���ǰ����ʾ�������ء�ֻ����ȫ��ʾ������ʱ�Ż���Ĵ�ֵ�����������д�ֵ��Ч
	private boolean isRightMenuVisible;
	private boolean isSliding;								//�Ƿ����ڻ���
	private View leftMenuLayout;							//���˵����ֶ���
	private View rightMenuLayout;							//�Ҳ�˵����ֶ���
	private View contentLayout;								//���ݲ˵����ֶ���
	private View mBindView;									//���ڼ��������¼���View
	private MarginLayoutParams leftMenuLayoutParams;		//���˵����ֵĲ���
	private MarginLayoutParams rightMenuLayoutParams;		//�Ҳ�˵����ֵĲ���
	private RelativeLayout.LayoutParams contentLayoutParams;//���ݲ��ֵĲ���
	private VelocityTracker mVelocityTracker;				//���㻬�����ٶ�
	
	
	/**
	 * ��дBidirSlidingLayout�Ĺ��캯�������л�ȡ����Ļ�Ŀ�Ⱥ�touchSlop��ֵ��
	 * 
	 * @param context
	 * @param attrs
	 */
	public BidirSlidingLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		screenWidth = wm.getDefaultDisplay().getWidth();
		touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
	}

	//�󶨼��������¼���View
	public void setScrollEvent(View bindView){
		mBindView = bindView;
		mBindView.setOnTouchListener(this);
	}

	
//	@Override
//	protected void onLayout(boolean changed, int l, int t, int r, int b) {
//		// TODO Auto-generated method stub
//		super.onLayout(changed, l, t, r, b);
//		if(changed){
//			//��ȡ���˵��Ĳ��ֶ���
//			leftMenuLayout = getChildAt(0);
//			leftMenuLayoutParams = (MarginLayoutParams) leftMenuLayout.getLayoutParams();
//			//��ȡ�Ҳ�˵��Ĳ��ֶ���
//			rightMenuLayout = getChildAt(1);
//			rightMenuLayoutParams = (MarginLayoutParams) rightMenuLayout.getLayoutParams();
//			//��ȡ���ݵĲ��ֶ���
//			contentLayout = getChildAt(2);
//			contentLayoutParams = (RelativeLayout.LayoutParams) contentLayout.getLayoutParams();
//			contentLayoutParams.width = screenWidth;
//			contentLayout.setLayoutParams(contentLayoutParams);
//		}
//	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		createVelocityTracker(event);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			//��ָ���µ�����
			xDown = event.getRawX();
			yDown = event.getRawY();
			//������״̬��ʼ��ΪDO_NOTHING
			slideState = DO_NOTHING;
			break;
		case MotionEvent.ACTION_MOVE:
			//��ָ�ƶ�������
			xMove = event.getRawX();
			yMove = event.getRawY();
			//��ָ�ƶ�ʱ���ԱȰ���ʱ�����꣬������ƶ��ľ���
			int moveDistanceX = (int) (xMove - xDown);
			int moveDistanceY = (int) (yMove - yDown);
			//��鵱ǰ�Ļ���״̬
			checkSlideState(moveDistanceX, moveDistanceY);
			//���ݵ�ǰ����״̬�������ƫ�����ݲ���
			switch(slideState){
			case SHOW_LEFT_MENU:
				contentLayoutParams.rightMargin = -moveDistanceX;
				checkLeftMenuBorder();
				contentLayout.setLayoutParams(contentLayoutParams);
				break;
//			case HIDE_LEFT_MENU:
//				contentLayoutParams.rightMargin = -leftMenuLayoutParams.width - moveDistanceX;
//				checkLeftMenuBorder();
//				contentLayout.setLayoutParams(contentLayoutParams);
//				break;
			case SHOW_RIGHT_MENU:
				contentLayoutParams.leftMargin = moveDistanceX;
				checkRightMenuBorder();
				contentLayout.setLayoutParams(contentLayoutParams);
				break;
//			case HIDE_RIGHT_MENU:
//				contentLayoutParams.leftMargin = -rightMenuLayoutParams.width + moveDistanceX;
//				checkRightMenuBorder();
//				contentLayout.setLayoutParams(contentLayoutParams);
//				break;
			default:
				break;
			}
			break;
		case MotionEvent.ACTION_UP:
			xUp = event.getRawX();
			int upDistanceX = (int) (xUp - xDown);
			if(isSliding){
				//��ָ̧��ʱ���жϵ�ǰ���Ƶ���ͼ
				switch (slideState) {
				case SHOW_LEFT_MENU:
					if(shouldScrollToLeftMenu()){
						scrollToLeftMenu();
					}else{
						scrollToContentFromLeftMenu();
					}
					break;
//				case HIDE_LEFT_MENU:
//					if(shouldScrollToContentFromLeftMenu()){
//						scrollToContentFromLeftMenu();
//					}else{
//						scrollToLeftMenu();
//					}
//					break;
				case SHOW_RIGHT_MENU:
					if(shouldScrollToRightMenu()){
						scrollToRightMenu();
					}else{
						scrollToContentFromRightMenu();
					}
					break;
//				case HIDE_RIGHT_MENU:
//					if(shouldScrollToContentFromRightMenu()){
//						scrollToContentFromRightMenu();
//					}else{
//						scrollToRightMenu();
//					}
//					break;
				default:
					break;
				}
			}else if(upDistanceX < touchSlop && isLeftMenuVisible){
				//�����˵���ʾʱ������û����һ�����ݲ��֣���ֱ�ӹ��������ݽ���
				scrollToContentFromLeftMenu();
			}else if(upDistanceX <touchSlop && isRightMenuVisible){
				//���Ҳ�˵���ʾʱ������û����һ�����ݲ��֣���ֱ�ӹ��������ݽ���
				scrollToContentFromRightMenu();
			}
			recycleVelocityTracker();
			break;
		}
		if(v.isEnabled()){
			if(isSliding){
				//���ڻ���ʱ�ÿؼ��ò�������
				unFocusBindView();
				return true;
			}
			if(isLeftMenuVisible || isRightMenuVisible){
				//�������Ҳ಼����ʾʱ�����󶨿ؼ����¼����ε�
				return true;
			}
			return false;
		}
		return true;
	}
	
	//������ָ���ƶ����룬�жϵ�ǰ�û��Ļ�����ͼ��Ȼ���slideState��ֵ����Ӧ�Ļ���״̬
	private void checkSlideState(int moveDistanceX,int moveDiatanceY){
		if(isLeftMenuVisible){
//			if(!isSliding && Math.abs(moveDistanceX) >= touchSlop && moveDistanceX < 0){
//				isSliding = true;
//				slideState = HIDE_LEFT_MENU;
//			}
//		}else if(isRightMenuVisible){
//			if(!isSliding && Math.abs(moveDistanceX) >= touchSlop && moveDistanceX > 0){
//				isSliding = true;
//				slideState = HIDE_RIGHT_MENU;
//			}
		}else{
			if(!isSliding && Math.abs(moveDistanceX) >= touchSlop && moveDistanceX > 0
					&& Math.abs(moveDiatanceY) < touchSlop){
				isSliding = true;
				slideState = SHOW_LEFT_MENU;
				initShowLeftState();
			}else if(!isSliding && Math.abs(moveDistanceX) >= touchSlop && moveDistanceX < 0
					&& Math.abs(moveDiatanceY) < touchSlop){
				isSliding = true;
				slideState = SHOW_RIGHT_MENU;
				initShowRightState();
			}
		}
	}
	
	//�ڻ��������м�����˵��ı߽�ֵ����ֹ�󶨲��ֻ�����Ļ
	private void checkLeftMenuBorder(){
		if(contentLayoutParams.rightMargin > 0){
			contentLayoutParams.rightMargin = 0;
		}else if(contentLayoutParams.rightMargin < -leftMenuLayoutParams.width){
			contentLayoutParams.rightMargin = - leftMenuLayoutParams.width;
		}
	}
	
	//�ڻ��������м���Ҳ�˵��ı߽�ֵ����ֹ�󶨲��ֻ�����Ļ
	private void checkRightMenuBorder(){
		if(contentLayoutParams.leftMargin > 0){
			contentLayoutParams.leftMargin = 0;
		}else if(contentLayoutParams.leftMargin < -rightMenuLayoutParams.width){
			contentLayoutParams.leftMargin = -rightMenuLayoutParams.width;
		}
	}
	
	/**
	 * �ж��Ƿ�Ӧ�ù��������˵�չʾ�����������ָ�ƶ�����������˵���ȵ�1/2��������ָ�ƶ��ٶȴ���SNAP_VELOCITY��
	 * ����ΪӦ�ù��������˵�չʾ������
	 * 
	 * @return ���Ӧ�ý����˵�չʾ��������true�����򷵻�false��
	 */
	private boolean shouldScrollToLeftMenu(){
		return xUp - xDown > leftMenuLayoutParams.width / 2 || getScrollVelocity() > SNAP_VELOCITY;
	}
	
	/**
	 * �ж��Ƿ�Ӧ�ù������Ҳ�˵�չʾ�����������ָ�ƶ���������Ҳ�˵���ȵ�1/2��������ָ�ƶ��ٶȴ���SNAP_VELOCITY��
	 * ����ΪӦ�ù������Ҳ�˵�չʾ������
	 * 
	 * @return ���Ӧ�ý��Ҳ�˵�չʾ��������true�����򷵻�false��
	 */
	private boolean shouldScrollToRightMenu(){
		return xDown - xUp > rightMenuLayoutParams.width / 2 || getScrollVelocity() > SNAP_VELOCITY; 
	}
	
	/**
	 * �ж��Ƿ�Ӧ�ô����˵����������ݲ��֣������ָ�ƶ�����������˵���ȵ�1/2��������ָ�ƶ��ٶȴ���SNAP_VELOCITY��
	 * ����ΪӦ�ô����˵����������ݲ��֡�
	 * 
	 * @return ���Ӧ�ô����˵����������ݲ��ַ���true�����򷵻�false��
	 */
	private boolean shouldScrollToContentFromLeftMenu(){
		return xDown - xUp > leftMenuLayoutParams.width / 2 || getScrollVelocity() > SNAP_VELOCITY;
	} 
	
	/**
	 * �ж��Ƿ�Ӧ�ô��Ҳ�˵����������ݲ��֣������ָ�ƶ���������Ҳ�˵���ȵ�1/2��������ָ�ƶ��ٶȴ���SNAP_VELOCITY��
	 * ����ΪӦ�ô��Ҳ�˵����������ݲ��֡�
	 * 
	 * @return ���Ӧ�ô��Ҳ�˵����������ݲ��ַ���true�����򷵻�false��
	 */
	private boolean shouldScrollToContentFromRightMenu(){
		return xUp - xDown > rightMenuLayoutParams.width / 2 || getScrollVelocity() > SNAP_VELOCITY;
	} 
	
	//����VelocityTracker���󣬲��������¼����뵽VelocityTracker����
	private void createVelocityTracker(MotionEvent event) {
		// TODO Auto-generated method stub
		if(mVelocityTracker == null){
			mVelocityTracker = VelocityTracker.obtain();
		}
		mVelocityTracker.addMovement(event);
	}
	
	//��ȡ��ָ�ڰ󶨲����ϵ��ƶ��ٶȣ���ÿ���ƶ��������Ϊ��λ
	private int getScrollVelocity(){
		mVelocityTracker.computeCurrentVelocity(1000);
		int velocity = (int) mVelocityTracker.getXVelocity();
		return Math.abs(velocity);
	}
	
	//����VelocityTracker����
	private void recycleVelocityTracker(){
		mVelocityTracker.recycle();
		mVelocityTracker = null;
	}
	
	//ʹ�ÿ��Ի�ý���Ŀؼ��ڻ�����ʱ��ʧȥ����
	private void unFocusBindView(){
		if(mBindView != null){
			mBindView.setPressed(false);
			mBindView.setFocusable(false);
			mBindView.setFocusableInTouchMode(false);
		}
	}
	
	//��������������˵����棬�����ٶ�Ϊ-30
	public void scrollToLeftMenu(){
		new LeftMenuScrollTask().execute(-30);
	}
	
	//������������Ҳ�˵����棬�����ٶ�Ϊ-30
	public void scrollToRightMenu(){
		new RightMenuScrollTask().execute(-30);
	}
	
	//����������˵����滬�������ݽ��棬�����ٶ�Ϊ30
	public void scrollToContentFromLeftMenu(){
		new LeftMenuScrollTask().execute(30);
	}
	
	//��������Ҳ�˵����滬�������ݽ��棬�����ٶ�Ϊ30
	public void scrollToContentFromRightMenu(){
		new RightMenuScrollTask().execute(30);
	}
		
	//���˵��Ƿ���ȫ��ʾ�����������д�ֵ��Ч,��ȫ��ʾ����true�����򷵻�false
	public boolean isLeftLayoutVisible(){
		return isLeftMenuVisible; 
	}
	
	//�Ҳ�˵��Ƿ���ȫ��ʾ�����������д�ֵ��Ч,��ȫ��ʾ����true�����򷵻�false
	public boolean isRightLayoutVisible(){
		return isRightMenuVisible; 
	}	
	
	public void initShowLeftState(){
		contentLayoutParams.rightMargin = 0;
		contentLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
		contentLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		contentLayout.setLayoutParams(contentLayoutParams);
		//����û��뻬����˵�������˵���ʾ���Ҳ˵�����
		leftMenuLayout.setVisibility(View.VISIBLE);
		rightMenuLayout.setVisibility(View.GONE);
	}
	
	public void initShowRightState(){
		contentLayoutParams.leftMargin = 0;
		contentLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
		contentLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		contentLayout.setLayoutParams(contentLayoutParams);
		//����û��뻬���Ҳ˵������Ҳ˵���ʾ����˵�����
		rightMenuLayout.setVisibility(View.VISIBLE);
		leftMenuLayout.setVisibility(View.GONE);
	}
	
	class LeftMenuScrollTask extends AsyncTask<Integer, Integer, Integer>{

		@Override
		protected Integer doInBackground(Integer... speed) {
			int rightMargin = contentLayoutParams.rightMargin;
			//���ݴ�����ٶ����������棬����������߽�ֵʱ��������ѭ��
			while(true){
				rightMargin = rightMargin + speed[0];
				if(rightMargin <= -leftMenuLayoutParams.width){
					rightMargin = -leftMenuLayoutParams.width;
					break;
				}
				if(rightMargin > 0){
					rightMargin = 0;
					break;
				}
				publishProgress(rightMargin);
				//Ϊ��Ҫ�й���Ч��������ÿ��ѭ��ʹ�߳�˯��һ��ʱ�䣬���������ܹ�������������
				sleep(20);
			}
			if(speed[0] > 0){
				isLeftMenuVisible = false;
			}else{
				isLeftMenuVisible = true;
			}
			isSliding = false;
			return rightMargin;
		}

		@Override
		protected void onPostExecute(Integer result) {
			contentLayoutParams.rightMargin = result;
			contentLayout.setLayoutParams(contentLayoutParams);
			super.onPostExecute(result);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			contentLayoutParams.rightMargin = values[0];
			contentLayout.setLayoutParams(contentLayoutParams);
			unFocusBindView();
			super.onProgressUpdate(values);
		}
		
	}
	
	class RightMenuScrollTask extends AsyncTask<Integer, Integer, Integer>{

		@Override
		protected Integer doInBackground(Integer... speed) {
			int leftMargin = contentLayoutParams.leftMargin;
			//���ݴ�����ٶ����������棬����������߽�ֵʱ��������ѭ��
			while(true){
				leftMargin = leftMargin + speed[0];
				if(leftMargin < -rightMenuLayoutParams.width){
					leftMargin = -rightMenuLayoutParams.width;
					break;
				}
				if(leftMargin > 0){
					leftMargin = 0;
					break;
				}
				publishProgress(leftMargin);
				//Ϊ��Ҫ�й���Ч��������ÿ��ѭ��ʹ�߳�˯��һ��ʱ�䣬���������ܹ�������������
				sleep(20);
			}
			if(speed[0] > 0){
				isRightMenuVisible = false;
			}else{
				isRightMenuVisible = true;
			}
			isSliding = false;
			return leftMargin;
		}

		@Override
		protected void onPostExecute(Integer result) {
			contentLayoutParams.leftMargin = result;
			contentLayout.setLayoutParams(contentLayoutParams);
			super.onPostExecute(result);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			contentLayoutParams.leftMargin = values[0];
			contentLayout.setLayoutParams(contentLayoutParams);
			unFocusBindView();
			super.onProgressUpdate(values);
		}
		
	}
	
	//ʱ��ǰ�߳�˯��ָ���ĺ���
	private void sleep(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
