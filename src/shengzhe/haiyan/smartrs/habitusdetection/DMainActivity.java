package shengzhe.haiyan.smartrs.habitusdetection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import shengzhe.haiyan.smartrs.R;
import shengzhe.haiyan.smartrs.habitusdetection.DDatabase.Detection;

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DMainActivity extends DetectionActivity {
	private Button button1,button2;
	private Cursor cursor;
	private Dialog dialog;
	StringBuffer sb=new StringBuffer();
	String[] info=new String[10];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dactivity_main);
		 
		addData();
		
		button1=(Button) findViewById(R.id.start);
		button2=(Button) findViewById(R.id.about);
		
		//开始测试
		button1.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Intent intent=new Intent(DMainActivity.this,DDTestActivity.class);
//				Intent intent=new Intent(DMainActivity.this,TestActivity.class);
				Intent intent=new Intent(DMainActivity.this,DTTest.class);
				startActivity(intent);
			}
		});
		 
		//详细解读
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LayoutInflater inflater=LayoutInflater.from(DMainActivity.this);
				View view=inflater.inflate(R.layout.dialog_about, null);
				dialog=new AlertDialog.Builder(DMainActivity.this).setTitle("中医体质分类与判定")
						.setIcon(R.drawable.ic_launcher).setView(view).create();
				dialog.show();
					
					final TextView text1=(TextView) dialog.findViewById(R.id.text1);
					final TextView text2=(TextView) dialog.findViewById(R.id.text2);
					final TextView text3=(TextView) dialog.findViewById(R.id.text3);
					final TextView text4=(TextView) dialog.findViewById(R.id.text4);
					final TextView text5=(TextView) dialog.findViewById(R.id.text5);
					final TextView text6=(TextView) dialog.findViewById(R.id.text6);
					final TextView text7=(TextView) dialog.findViewById(R.id.text7);
					final TextView text8=(TextView) dialog.findViewById(R.id.text8);
					final TextView text9=(TextView) dialog.findViewById(R.id.text9);
					
					text1.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							LayoutInflater inflater=LayoutInflater.from(DMainActivity.this);
							View view=inflater.inflate(R.layout.about_info, null);
							Dialog dialog1=new AlertDialog.Builder(DMainActivity.this).setTitle("平和质")
									.setIcon(R.drawable.ic_launcher).setView(view).create();
							dialog1.show();
							TextView infoText=(TextView) dialog1.findViewById(R.id.info);
							String ss= about_info(1);
							System.out.println("ssdd 1"+ss);
							infoText.setText(ss);
						}
					});
					
					text2.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							LayoutInflater inflater=LayoutInflater.from(DMainActivity.this);
							View view=inflater.inflate(R.layout.about_info, null);
							Dialog dialog1=new AlertDialog.Builder(DMainActivity.this).setTitle("气虚质")
									.setIcon(R.drawable.ic_launcher).setView(view).create();
							dialog1.show();
							TextView infoText=(TextView) dialog1.findViewById(R.id.info);
							String ss= about_info(2);
							System.out.println("ssdd 2"+ss);
							infoText.setText(ss);
						}
					});

					text3.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							LayoutInflater inflater=LayoutInflater.from(DMainActivity.this);
							View view=inflater.inflate(R.layout.about_info, null);
							Dialog dialog1=new AlertDialog.Builder(DMainActivity.this).setTitle("阳虚质")
									.setIcon(R.drawable.ic_launcher).setView(view).create();
							dialog1.show();
							TextView infoText=(TextView) dialog1.findViewById(R.id.info);
							String ss= about_info(3);
							System.out.println("ssdd 3"+ss);
							infoText.setText(ss);
						}
					});
				
					text4.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							LayoutInflater inflater=LayoutInflater.from(DMainActivity.this);
							View view=inflater.inflate(R.layout.about_info, null);
							Dialog dialog1=new AlertDialog.Builder(DMainActivity.this).setTitle("阴虚质")
									.setIcon(R.drawable.ic_launcher).setView(view).create();
							dialog1.show();
							TextView infoText=(TextView) dialog1.findViewById(R.id.info);
							String ss= about_info(4);
							System.out.println("ssdd 4"+ss);
							infoText.setText(ss);
						}
					});

					text5.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							LayoutInflater inflater=LayoutInflater.from(DMainActivity.this);
							View view=inflater.inflate(R.layout.about_info, null);
							Dialog dialog1=new AlertDialog.Builder(DMainActivity.this).setTitle("痰湿质")
									.setIcon(R.drawable.ic_launcher).setView(view).create();
							dialog1.show();
							TextView infoText=(TextView) dialog1.findViewById(R.id.info);
							String ss= about_info(5);
							System.out.println("ssdd 5"+ss);
							infoText.setText(ss);
						}
					});
					
					text6.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							LayoutInflater inflater=LayoutInflater.from(DMainActivity.this);
							View view=inflater.inflate(R.layout.about_info, null);
							Dialog dialog1=new AlertDialog.Builder(DMainActivity.this).setTitle("平和质")
									.setIcon(R.drawable.ic_launcher).setView(view).create();
							dialog1.show();
							TextView infoText=(TextView) dialog1.findViewById(R.id.info);
							String ss= about_info(6);
							System.out.println("ssdd 6"+ss);
							infoText.setText(ss);
						}
					});
					
					text7.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							LayoutInflater inflater=LayoutInflater.from(DMainActivity.this);
							View view=inflater.inflate(R.layout.about_info, null);
							Dialog dialog1=new AlertDialog.Builder(DMainActivity.this).setTitle("血瘀质")
									.setIcon(R.drawable.ic_launcher).setView(view).create();
							dialog1.show();
							TextView infoText=(TextView) dialog1.findViewById(R.id.info);
							String ss= about_info(7);
							System.out.println("ssdd 7"+ss);
							infoText.setText(ss);
						}
					});
					
					text8.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							LayoutInflater inflater=LayoutInflater.from(DMainActivity.this);
							View view=inflater.inflate(R.layout.about_info, null);
							Dialog dialog1=new AlertDialog.Builder(DMainActivity.this).setTitle("气郁质")
									.setIcon(R.drawable.ic_launcher).setView(view).create();
							dialog1.show();
							TextView infoText=(TextView) dialog1.findViewById(R.id.info);
							String ss= about_info(8);
							System.out.println("ssdd 8"+ss);
							infoText.setText(ss);
						}
					});
					
					text9.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							LayoutInflater inflater=LayoutInflater.from(DMainActivity.this);
							View view=inflater.inflate(R.layout.about_info, null);
							Dialog dialog1=new AlertDialog.Builder(DMainActivity.this).setTitle("特禀质")
									.setIcon(R.drawable.ic_launcher).setView(view).create();
							dialog1.show();
							TextView infoText=(TextView) dialog1.findViewById(R.id.info);
							String ss= about_info(9);
							System.out.println("ssdd 9"+ss);
							infoText.setText(ss);
						}
					});
			}
		});
	}

	public void addData(){
		cursor=mDB.query(Detection.Detection_TABLE_NAME, new String[]{
				Detection.Detection_ID,Detection.Detection_QUESTION,Detection.Detection_TYPE,
				Detection.Detection_HABITUS,Detection.Detection_CHOOSE},
				null, null, null, null, null);
		startManagingCursor(cursor);
		int num =0;
		if(cursor.moveToFirst()){
			num=cursor.getCount();
		}
		if(num == 0){  
			try {
				InputStream is=getResources().openRawResource(R.raw.dd);
				BufferedReader br=new BufferedReader(new InputStreamReader(is,"gbk"));
				String str= "";
				while((str=br.readLine())!=null){
					String[] column = str.split(" ");
					ContentValues values=new ContentValues();
					values.put(Detection.Detection_QUESTION, column[0]);
					values.put(Detection.Detection_TYPE, column[1]);
					values.put(Detection.Detection_HABITUS, column[2]);
					values.put(Detection.Detection_CHOOSE, column[3]);
					mDB.insert(Detection.Detection_TABLE_NAME, null, values);
					System.out.println(str);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public String about_info(int a){
		InputStream is=getResources().openRawResource(R.raw.about);
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(is,"gbk"));
			String str="";
			
			while((str=br.readLine())!=null){
				sb.append(str);
				sb.append("\n");
			}
			str=sb.substring(0, sb.length());
			for(int i=0; i < 10; i++){
				info =str.split("a");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info[a];
	}
	
}
