package shengzhe.haiyan.smartrs.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import shengzhe.haiyan.smartrs.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MoreAdapter extends BaseExpandableListAdapter {

	private Context context;
	List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
	List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();

	public MoreAdapter(Context context) {
		super();
		this.context = context;
	}

	public MoreAdapter(List<Map<String, String>> groupData,
			List<List<Map<String, String>>> childData, Context context) {
		super();
		this.context = context;
		this.groupData = groupData;
		this.childData = childData;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childData.get(groupPosition).get(childPosition).get("A")
				.toString();
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		View view = convertView;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(
					R.layout.expand_list_item, null);
		}

		TextView childTextView1 = (TextView) view
				.findViewById(R.id.list_text_item_id);
		childTextView1.setText(getChild(groupPosition, childPosition)
				.toString());

		return view;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return childData.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return groupData.get(groupPosition).get("Q");
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return groupData.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		View view = convertView;
		if(view == null){
			view = LayoutInflater.from(context).inflate(R.layout.expand_list, null);
		}
		
		TextView groupTextView = (TextView) view.findViewById(R.id.all_list_text_id);
		groupTextView.setText((CharSequence) getGroup(groupPosition));
		ImageView groupImageView = (ImageView) view.findViewById(R.id.group_icon);
		if(isExpanded){
			groupImageView.setBackgroundResource(R.drawable.down_arrow);
		}else{
			groupImageView.setBackgroundResource(R.drawable.arrow_right);
		}
		return view;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}
