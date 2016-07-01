package shengzhe.haiyan.smartrs.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lidroid.xutils.view.annotation.ViewInject;

import shengzhe.haiyan.smartrs.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**
 * @author:hj
 * @date:2016-6-16 上午10:41:30
 */
public class ChatList extends Activity implements OnItemClickListener {
	private IDemoChart[] mCharts = new IDemoChart[] { new LineChart(),
			new BudgetPieChart(), new BarChart() };

	private String[] mMenuText ;

//	private String[] mMenuSummary;

	// 标题
	private TextView title_text;
	private ImageView back;
	private ListView listview;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chatlist);

		int length = mCharts.length;
		mMenuText = new String[length];
		for(int i = 0; i < length; i++) {
		   mMenuText[i] = mCharts[i].getName();
		}
		
//		mMenuText[length] = "自定义折线图";
//		mMenuText[length+1] = "自定义饼状图";
//		mMenuText[length+2] = "随机值图表";
		
		title_text= (TextView) findViewById(R.id.title_text);
		title_text.setText("行情分析");	
		back = (ImageView) findViewById(R.id.back);
		back.setVisibility(View.VISIBLE);
		listview = (ListView) findViewById(R.id.chatlist);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, mMenuText);
		
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(this);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

//	private List<Map<String, String>> getListValues() {
//		List<Map<String, String>> values = new ArrayList<Map<String, String>>();
//		int length = mMenuText.length;
//		for (int i = 0; i < length; i++) {
//			Map<String, String> v = new HashMap<String, String>();
//			v.put(IDemoChart.NAME, mMenuText[i]);
//			v.put(IDemoChart.DESC, mMenuSummary[i]);
//			values.add(v);
//		}
//		return values;
//	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = null;
//		if (position == mCharts.length) {
//			intent = new Intent(this, XYChartBuilder.class);
//		} 
//		else if (position == mCharts.length+1) {
//			intent = new Intent(this, PieChartBuilder.class);
//		} else if (position == mCharts.length+2) {
//			intent = new Intent(this, GeneratedChartDemo.class);
//		}
//		else {
			intent = mCharts[position].execute(this);
//		}
		startActivity(intent);
		
	}
}
