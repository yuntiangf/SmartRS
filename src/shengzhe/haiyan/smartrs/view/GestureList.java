package shengzhe.haiyan.smartrs.view;
//package shengzhe.haiyan.smartrs.view;
//
//import android.content.Context;
//import android.gesture.Gesture;
//import android.util.AttributeSet;
//import android.view.GestureDetector;
//import android.view.MotionEvent;
//import android.widget.ListView;
//
///**
// * @author:hj
// * @date:2016-5-4 下午2:25:23
// *自定义带有手势的listview
// */
//
//public class GestureList extends ListView {
//
//    int flag=BaseActivity
//    Context context;
//    GestureDetector gestureDetector;
//    /**
//     * 在xml布局里面使用GestureList，默认的会调用这个构造方法
//     * @param context
//     * @param attrs
//     */
//    public GestureList(Context context, AttributeSet attrs) {
//       super(context, attrs);
//       // TODO Auto-generated constructor stub
//       this.context=context;
//       gestureDetector=new GestureDetector(context,new Gesture(context));      
//    }
//    public GestureList(Context context, AttributeSet attrs, int defStyle) {
//       super(context, attrs, defStyle);
//       // TODO Auto-generated constructor stub
//       this.context=context;
//       gestureDetector=new GestureDetector(context,new Gesture(context));
//    }
//    public GestureList(Context context) {
//       super(context);
//       // TODO Auto-generated constructor stub
//       this.context=context;
//       gestureDetector=new GestureDetector(context,new Gesture(context));
//    }
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//      
//       if(gestureDetector.onTouchEvent(ev)) return true;
//       return super.onTouchEvent(ev);
//    }  
//}
// 