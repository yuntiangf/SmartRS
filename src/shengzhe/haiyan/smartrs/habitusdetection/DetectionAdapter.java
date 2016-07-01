package shengzhe.haiyan.smartrs.habitusdetection;
//package shengzhe.haiyan.smartrs.habitusdetection;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import shengzhe.haiyan.smartrs.R;
//import android.content.Context;
//import android.graphics.Color;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.View.OnClickListener;
//import android.widget.BaseAdapter;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
///**
// * @author:hj
// * @date:2016-5-5 ÏÂÎç9:21:48
// */
//public class DetectionAdapter extends BaseAdapter {
//	private static ArrayList<Tb_detection> tb_detection;
//	private Context mContext;
//	private List<String> contant;
//
//	private int selectItem = -1;
//	private int sign = -1;
//	
//	
//	public void setSelectItem(int selectItem) {
//		this.selectItem = selectItem;
//	}
//
//	public DetectionAdapter(Context context, List<String> contant) {
//		this.mContext = context;
//		this.contant = contant;
//	}
//
//	@Override
//	public int getCount() {
//		// int num=tb_detection.size();
//		// if(num-index*VIEW_COUNT<VIEW_COUNT){
//		// return num-index*VIEW_COUNT;
//		// }else{
//		// return VIEW_COUNT;
//		// }
//		return contant.size();
//	}
//
//	public Object getItem(int position) {
//		return position;
//	}
//
//	public long getItemId(int position) {
//		return position;
//	}
//
//	public View getView(int position, View convertView, ViewGroup parent) {
//		View view;
//		final ViewHolder viewHolder;
//		final int[] f = { 0 };
//		final Tb_detection tb = tb_detection.get(position + index * VIEW_COUNT);
//		String ques = tb.getDetection_question();
//		int tb_id = tb.getDetection_id();
//
//		try {
//			if (convertView == null) {
//				LayoutInflater inflater = LayoutInflater.from(mContext);
//				view = inflater.inflate(R.layout.question_item, null);
//				viewHolder = new ViewHolder();
//
//				viewHolder.question = (TextView) view
//						.findViewById(R.id.question_item);
//				viewHolder.choose = (TextView) view.findViewById(R.id.choose);
//				viewHolder.choose_1 = (TextView) view
//						.findViewById(R.id.choose_1);
//				viewHolder.choose_2 = (TextView) view
//						.findViewById(R.id.choose_2);
//				viewHolder.choose_3 = (TextView) view
//						.findViewById(R.id.choose_3);
//				viewHolder.choose_4 = (TextView) view
//						.findViewById(R.id.choose_4);
//				viewHolder.choose_5 = (TextView) view
//						.findViewById(R.id.choose_5);
//				viewHolder.choose_layout = (LinearLayout) view
//						.findViewById(R.id.choose_layout);
//				viewHolder.question_layout = (LinearLayout) view
//						.findViewById(R.id.ques_layout);
//
//				view.setTag(viewHolder);
//			} else {
//				view = convertView;
//				viewHolder = (ViewHolder) view.getTag();
//			}
//
//			if (ques.length() > 10) {
//				ques = ques.substring(0, 10);
//				viewHolder.question.setText(tb_id + "¡¢" + ques + "...");
//			} else {
//				viewHolder.question.setText(tb_id + "¡¢" + ques);
//			}
//
//			if (position == selectItem) {
//				System.out.println("selectItem-->" + selectItem);
//				if (sign == selectItem) {
//					System.out.println("sdfadasd");
//					viewHolder.choose_layout.setVisibility(View.GONE);
//					sign = -1;
//				} else {
//					System.out.println("sfvffffffff");
//					viewHolder.choose_layout.setVisibility(View.VISIBLE);
//					sign = selectItem;
//				}
//			} else {
//				System.out.println("oooooooopuioyit");
//				viewHolder.choose_layout.setVisibility(View.GONE);
//			}
//
//			// for (int i = 0; i < contant.size(); i++) {
//			// if(i != position){
//			// viewHolder.choose_layout.setVisibility(View.GONE);
//			// }
//			// }
//
//			viewHolder.question_layout
//					.setOnClickListener(new OnClickListener() {
//
//						@Override
//						public void onClick(View v) {
//
//							if (f[0] == 0) {
//								viewHolder.choose_layout
//										.setVisibility(View.VISIBLE);
//								f[0] = 1;
//							} else if (f[0] == 1) {
//								viewHolder.choose_layout
//										.setVisibility(View.GONE);
//								f[0] = 0;
//							}
//
//							viewHolder.choose_1
//									.setOnClickListener(new OnClickListener() {
//										@Override
//										public void onClick(View v) {
//											str = viewHolder.choose.getText()
//													.toString();
//											ischoose(str);
//											String choose = viewHolder.choose_1
//													.getText().toString();
//											viewHolder.choose.setText(choose);
//											viewHolder.choose
//													.setTextColor(Color.RED);
//											tb.setDetection_choose(choose);
//											add(tb);
//											
//											viewHolder.choose_layout
//													.setVisibility(View.GONE);
//											f[0] = 0;
//										}
//									});
//							viewHolder.choose_2
//									.setOnClickListener(new OnClickListener() {
//										@Override
//										public void onClick(View v) {
//											str = viewHolder.choose.getText()
//													.toString();
//											ischoose(str);
//											String choose = viewHolder.choose_2
//													.getText().toString();
//											viewHolder.choose.setText(choose);
//											viewHolder.choose
//													.setTextColor(Color.RED);
//											tb.setDetection_choose(choose);
//											add(tb);
//											viewHolder.choose_layout
//													.setVisibility(View.GONE);
//											f[0] = 0;
//										}
//									});
//							viewHolder.choose_3
//									.setOnClickListener(new OnClickListener() {
//										@Override
//										public void onClick(View v) {
//											str = viewHolder.choose.getText()
//													.toString();
//											ischoose(str);
//											String choose = viewHolder.choose_3
//													.getText().toString();
//											viewHolder.choose.setText(choose);
//											viewHolder.choose
//													.setTextColor(Color.RED);
//											tb.setDetection_choose(choose);
//											add(tb);
//											viewHolder.choose_layout
//													.setVisibility(View.GONE);
//											f[0] = 0;
//										}
//									});
//							viewHolder.choose_4
//									.setOnClickListener(new OnClickListener() {
//										@Override
//										public void onClick(View v) {
//											str = viewHolder.choose.getText()
//													.toString();
//											ischoose(str);
//											String choose = viewHolder.choose_4
//													.getText().toString();
//											viewHolder.choose.setText(choose);
//											viewHolder.choose
//													.setTextColor(Color.RED);
//											tb.setDetection_choose(choose);
//											add(tb);
//											viewHolder.choose_layout
//													.setVisibility(View.GONE);
//											f[0] = 0;
//										}
//									});
//							viewHolder.choose_5
//									.setOnClickListener(new OnClickListener() {
//										@Override
//										public void onClick(View v) {
//											str = viewHolder.choose.getText()
//													.toString();
//											ischoose(str);
//											String choose = viewHolder.choose_5
//													.getText().toString();
//											viewHolder.choose.setText(choose);
//											viewHolder.choose
//													.setTextColor(Color.RED);
//											tb.setDetection_choose(choose);
//											add(tb);
//											viewHolder.choose_layout
//													.setVisibility(View.GONE);
//											f[0] = 0;
//										}
//									});
//
//						}
//					});
//
//			viewHolder.choose.setText(tb.getDetection_choose());
//			return view;
//		} catch (Exception e) {
//		}
//		return null;
//	}
//
//	private static class ViewHolder {
//		TextView question;
//		TextView choose;
//		TextView choose_1;
//		TextView choose_2;
//		TextView choose_3;
//		TextView choose_4;
//		TextView choose_5;
//		LinearLayout choose_layout;
//		LinearLayout question_layout;
//	}
//}
