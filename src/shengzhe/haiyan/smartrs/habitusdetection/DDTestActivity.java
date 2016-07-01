package shengzhe.haiyan.smartrs.habitusdetection;

import java.util.ArrayList;

import shengzhe.haiyan.smartrs.R;
import shengzhe.haiyan.smartrs.habitusdetection.DDatabase.Detection;
import shengzhe.haiyan.smartrs.view.BidirSlidingLayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DDTestActivity extends DetectionActivity implements OnClickListener {
	private ListView listView;
	private TextView previous,next;
	private TextView textView;
	private Button startDetection;
	private ArrayList<Tb_detection> tb_detection;
	private DetectionAdapter dtAdapter;
	private int index=0;
	private int VIEW_COUNT=10;
	private Dialog dialog;
	private int[] score=new int[67];
	public static final int START=Menu.FIRST;
	public static final int ABOUT=Menu.FIRST+1;
	public static int flag = 0;
	private int count = 0;
	private String str = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question);
		
		showData();
		listView=(ListView) findViewById(R.id.list);
		previous=(TextView) findViewById(R.id.previous);
		next=(TextView) findViewById(R.id.next);
		textView=(TextView) findViewById(R.id.textView);
		startDetection = (Button) findViewById(R.id.stratDetection);
		previous.setEnabled(false);
		dtAdapter=new DetectionAdapter(DDTestActivity.this);
		listView.setAdapter(dtAdapter);
		
		previous.setOnClickListener(this);
		next.setOnClickListener(this);
		startDetection.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.previous:
			index--;
			
			dtAdapter.notifyDataSetChanged();
			listView.setAdapter(dtAdapter);
//			textView.setText("第"+(index+1)+"页/共7页");
			changeButtonStatus();
			break;
		case R.id.next:
			index++;
			
			dtAdapter.notifyDataSetChanged();
			listView.setAdapter(dtAdapter);
//			textView.setText("第"+(index+1)+"页/共7页");
			changeButtonStatus();
			break;
		case R.id.stratDetection:
			double [] data=new double [9];
			data=calculate(score);
			for(int i=0 ; i < score.length; i++){
				if(score[i]==0){
					flag = 1;
					break;
				}
			}
			Intent intent=new Intent(DDTestActivity.this,ResultActivity.class);
			intent.putExtra("data", data);
			
			if(flag==0){
				startActivity(intent);
			}else if(flag==1){
				Toast.makeText(DDTestActivity.this, "您未选择完所有题目，得不出正确的结论，请继续选择！",
						Toast.LENGTH_SHORT).show();
				flag=0;
			}
			break;
		}
	}
	
	
	private void ischoose(String str){
		if(str.equals("未选")){
			count = count + 1;
			str = "";
		}
		textView.setText("已选"+count+"题/共67题");
	}
	
	
	public void changeButtonStatus(){
		if(index<=0){
			previous.setVisibility(View.INVISIBLE);
			startDetection.setVisibility(View.GONE);
		}else if(tb_detection.size()-index*VIEW_COUNT<=VIEW_COUNT){
			next.setVisibility(View.INVISIBLE);
			startDetection.setVisibility(View.VISIBLE);
		}else{
			previous.setVisibility(View.VISIBLE);
			next.setVisibility(View.VISIBLE);
			previous.setEnabled(true);
			next.setEnabled(true);
			startDetection.setVisibility(View.GONE);
		}
	}
	
	private void add(Tb_detection tb){
		int id=tb.getDetection_id();
		int type=tb.getDetection_type();
		String choose=tb.getDetection_choose();
		if(type==0){
			if(choose.equals("没有")){
				score[id - 1] = 1;
			}else if(choose.equals("很少")){
				score[id - 1] = 2;
			}else if(choose.equals("正常")){
				score[id - 1] = 3;
			}else if(choose.equals("经常")){
				score[id - 1] = 4;
			}else if(choose.equals("总是")){
				score[id - 1] = 5;
			}
		}else if(type==1){
			if(choose.equals("没有")){
				score[id - 1] = 5;
			}else if(choose.equals("很少")){
				score[id - 1] = 4;
			}else if(choose.equals("正常")){
				score[id - 1] = 3;
			}else if(choose.equals("经常")){
				score[id - 1] = 2;
			}else if(choose.equals("总是")){
				score[id - 1] = 1;
			}
		}
	}
	
	public void showData(){
		mCursor=mDB.query(Detection.Detection_TABLE_NAME, new String[]{
				Detection.Detection_ID,Detection.Detection_QUESTION,Detection.Detection_TYPE,
				Detection.Detection_HABITUS,Detection.Detection_CHOOSE},
				null, null, null, null, null);
		int num=mCursor.getCount();
		tb_detection=new ArrayList<Tb_detection>();
		if(num >0 && (mCursor.moveToFirst())){
			for(int i =0; i < num; i++){
				Tb_detection tb=new Tb_detection();
				tb.setDetection_id(mCursor.getInt(mCursor.getColumnIndex
						(Detection.Detection_ID)));
				tb.setDetection_question(mCursor.getString(mCursor.getColumnIndex
						(Detection.Detection_QUESTION)));
				tb.setDetection_type(mCursor.getInt(mCursor.getColumnIndex
						(Detection.Detection_TYPE)));
				tb.setDetection_habitus(mCursor.getString(mCursor.getColumnIndex
						(Detection.Detection_HABITUS)));
				tb.setDetection_choose(mCursor.getString(mCursor.getColumnIndex
						(Detection.Detection_CHOOSE)));
				tb_detection.add(tb);
				mCursor.moveToNext();
			}
		}
	}
	
	public class DetectionAdapter extends BaseAdapter{
		private Context mContext;
		
		public DetectionAdapter(Context context) {
			this.mContext = context;
		}
		
		@Override
		public int getCount() {
			int num=tb_detection.size();
			if(num-index*VIEW_COUNT<VIEW_COUNT){
				return num-index*VIEW_COUNT;
			}else{
				return VIEW_COUNT;
			}
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			final ViewHolder viewHolder;
			final int[] f = {0};
			final Tb_detection tb=tb_detection.get(position+index*VIEW_COUNT);
			String ques=tb.getDetection_question();
			int tb_id=tb.getDetection_id();
			
			try {
				if(convertView == null){
					LayoutInflater inflater = LayoutInflater.from(mContext);
					view = inflater.inflate(R.layout.question_item, null);
					viewHolder = new ViewHolder();
					
					viewHolder.question = (TextView) view.findViewById(R.id.question_item);
					viewHolder.choose = (TextView) view.findViewById(R.id.choose);
					viewHolder.choose_1 = (TextView) view.findViewById(R.id.choose_1);
					viewHolder.choose_2 = (TextView) view.findViewById(R.id.choose_2);
					viewHolder.choose_3 = (TextView) view.findViewById(R.id.choose_3);
					viewHolder.choose_4 = (TextView) view.findViewById(R.id.choose_4);
					viewHolder.choose_5 = (TextView) view.findViewById(R.id.choose_5);
					viewHolder.choose_layout = (LinearLayout) view.findViewById(R.id.choose_layout);
					viewHolder.question_layout = (LinearLayout) view.findViewById(R.id.ques_layout);
					
					view.setTag(viewHolder);
				}else{
					view = convertView;
					viewHolder = (ViewHolder) view.getTag();
				}
				
				if(ques.length() > 11){
					ques = ques.substring(0, 10);
					viewHolder.question.setText(tb_id+"、"+ques+"...");
				}else{
					viewHolder.question.setText(tb_id+"、"+ques); 
				}
				
				if(tb.getDetection_choose().toString().equals("未选")){
					viewHolder.choose.setTextColor(Color.BLACK);
				}else{
					viewHolder.choose.setTextColor(Color.RED);
				}
				
				viewHolder.question_layout.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						if(f[0] == 0){
							viewHolder.choose_layout.setVisibility(View.VISIBLE);
							f[0] = 1;
						}else if(f[0] == 1){
							viewHolder.choose_layout.setVisibility(View.GONE);
							f[0] = 0;
						}
						
						viewHolder.choose_1.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								str = viewHolder.choose.getText().toString(); 
								ischoose(str);
								String choose = viewHolder.choose_1.getText().toString();
								viewHolder.choose.setText(choose);
								viewHolder.choose.setTextColor(Color.RED);
								tb.setDetection_choose(choose);
								add(tb);
								viewHolder.choose_layout.setVisibility(View.GONE);
								f[0] = 0;
							}
						});
						viewHolder.choose_2.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								str = viewHolder.choose.getText().toString(); 
								ischoose(str);
								String choose = viewHolder.choose_2.getText().toString();
								viewHolder.choose.setText(choose);
								viewHolder.choose.setTextColor(Color.RED);
								tb.setDetection_choose(choose);
								add(tb);
								viewHolder.choose_layout.setVisibility(View.GONE);
								f[0] = 0;
							}
						});
						viewHolder.choose_3.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								str = viewHolder.choose.getText().toString(); 
								ischoose(str);
								String choose = viewHolder.choose_3.getText().toString();
								viewHolder.choose.setText(choose);
								viewHolder.choose.setTextColor(Color.RED);
								tb.setDetection_choose(choose);
								add(tb);
								viewHolder.choose_layout.setVisibility(View.GONE);
								f[0] = 0;
							}
						});
						viewHolder.choose_4.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								str = viewHolder.choose.getText().toString(); 
								ischoose(str);
								String choose = viewHolder.choose_4.getText().toString();
								viewHolder.choose.setText(choose);
								viewHolder.choose.setTextColor(Color.RED);
								tb.setDetection_choose(choose);
								add(tb);
								viewHolder.choose_layout.setVisibility(View.GONE);
								f[0] = 0;
							}
						});
						viewHolder.choose_5.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								str = viewHolder.choose.getText().toString(); 
								ischoose(str);
								String choose = viewHolder.choose_5.getText().toString();
								viewHolder.choose.setText(choose);
								viewHolder.choose.setTextColor(Color.RED);
								tb.setDetection_choose(choose);
								add(tb);
								viewHolder.choose_layout.setVisibility(View.GONE);
								f[0] = 0;
							}
						});
						
					}
				});
				
				viewHolder.choose.setText(tb.getDetection_choose());
				return view;
			} catch (Exception e) {
			}
			return null;
		}

	}

	private static class ViewHolder {
		TextView question;
		TextView choose;
		TextView choose_1;
		TextView choose_2;
		TextView choose_3;
		TextView choose_4;
		TextView choose_5;
		LinearLayout choose_layout;
		LinearLayout question_layout;
	}
	
	public double[] calculate(int[] aa){
		int [] habitus =new int[9];
		double [] goal=new double[9];
		for(int i=0;i<aa.length;i++){
			if(i>=0 && i<7){
				habitus[0] += aa[i];
			}else if(i>=7 && i<15){
				habitus[1] += aa[i];
			}else if(i>=15 && i<23){
				habitus[2] += aa[i];
			}else if(i>=23 && i<31){
				habitus[3] += aa[i];
			}else if(i>=31 && i<38){
				habitus[4] += aa[i];
			}else if(i>=38 && i<45){
				habitus[5] += aa[i];
			}else if(i>=45 && i<52){
				habitus[6] += aa[i];
			}else if(i>=52 && i<59){
				habitus[7] += aa[i];
			}else if(i>=59 && i<67){
				habitus[8] += aa[i];
			}
		}
		System.out.println("每项得分"+habitus[0]+" , "+habitus[1]+" , "+habitus[2]+" , "
						+habitus[3]+" , "+habitus[4]+" , "+habitus[5]+" , "
						+habitus[6]+" , "+habitus[7]+" , "+habitus[8]);
		
		goal[0] = transform(habitus[0], 7);
		goal[1] = transform(habitus[1], 8);
		goal[2] = transform(habitus[2], 8);
		goal[3] = transform(habitus[3], 8);
		goal[4] = transform(habitus[4], 7);
		goal[5] = transform(habitus[5], 7);
		goal[6] = transform(habitus[6], 7);
		goal[7] = transform(habitus[7], 7);
		goal[8] = transform(habitus[8], 8);
		
		System.out.println("转化后分数：" +goal[0]+","+goal[1]+","+goal[2]+","+goal[3]+","
				+goal[4]+","+goal[5]+","+goal[6]+","+goal[7]+","+goal[8]);
		return goal;
	}
	
	private double transform(int a,int b){
		double s1= a - b;
		double s2= b * 4;
		double s3= s1 / s2;
		double s4= s3 * 100;
		return s4;
	}

}
