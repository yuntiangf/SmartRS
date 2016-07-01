package shengzhe.haiyan.smartrs.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import shengzhe.haiyan.smartrs.AppManager;
import shengzhe.haiyan.smartrs.BaseActivity;
import shengzhe.haiyan.smartrs.R;
import shengzhe.haiyan.smartrs.adapter.MoreAdapter;
import shengzhe.haiyan.smartrs.view.ExpandableListViewForScrollView;

public class MoreActivity extends BaseActivity implements OnClickListener {

	private TextView title;
	private ImageView back;
	private ExpandableListViewForScrollView list_help;
	private MoreAdapter mAdapter;
	private List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
	private List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
	private String[] help_father = {};
	private String[] help_child = {};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		AppManager.getAppManager().addActivity(this);
		setContentView(R.layout.activity_more);

		getData();
		initView();
		initData();
		setAdapter();

		list_help.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				return false;
			}
		});

		list_help.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// TODO Auto-generated method stub
				return false;
			}
		});

	}

	private void getData() {
		// TODO Auto-generated method stub
		help_father = getResources().getStringArray(R.array.help_father);
		help_child = getResources().getStringArray(R.array.help_child);
	}

	private void initView() {
		// TODO Auto-generated method stub
		title = (TextView) findViewById(R.id.title_text);
		back = (ImageView) findViewById(R.id.back);
		list_help = (ExpandableListViewForScrollView) findViewById(R.id.list_help);

		title.setText(" π”√∞Ô÷˙");
		back.setVisibility(View.VISIBLE);
		back.setOnClickListener(this);
	}

	private void initData() {
		// TODO Auto-generated method stub
		for (int i = 0; i < help_father.length; i++) {
			Map<String, String> groupMap = new HashMap<String, String>();
			groupMap.put("Q", help_father[i]);
			groupData.add(groupMap);
			List<Map<String, String>> childList = new ArrayList<Map<String, String>>();
			Map<String, String> childMap = new HashMap<String, String>();
			childMap.put("A", help_child[i]);
			childList.add(childMap);
			childData.add(childList);
		}
	}

	private void setAdapter() {
		// TODO Auto-generated method stub
		if (mAdapter == null) {
			mAdapter = new MoreAdapter(groupData, childData, this);
			list_help.setAdapter(mAdapter);
			list_help.setGroupIndicator(null);
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.back) {
			finish();
		}
	}
}
