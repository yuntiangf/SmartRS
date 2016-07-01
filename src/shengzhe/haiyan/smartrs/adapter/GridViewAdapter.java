package shengzhe.haiyan.smartrs.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import shengzhe.haiyan.smartrs.R;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridViewAdapter extends BaseAdapter {

	private static final String TAG = "GridViewAdapter";
	private static final int ROW_NUMBER = 3;
	private Context context;
	private List<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();

	public GridViewAdapter(Context context, List<Map<String, Object>> mData) {
		// TODO Auto-generated constructor stub
		super();
		this.context = context;
		this.mData = mData;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = LayoutInflater.from(context).inflate(R.layout.item_grid,
				null);

		ImageView itemImage = (ImageView) convertView
				.findViewById(R.id.ItemImage);
		TextView itemText = (TextView) convertView.findViewById(R.id.ItemText);

		itemImage.setImageResource((Integer) mData.get(position).get("icon"));
		itemText.setText((CharSequence) mData.get(position).get("iacon_name"));

		AbsListView.LayoutParams param = new AbsListView.LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				parent.getHeight() / ROW_NUMBER);
		convertView.setLayoutParams(param);
		return convertView;
	}

}
