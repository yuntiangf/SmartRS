package shengzhe.haiyan.smartrs.adapter;

import java.util.List;

import shengzhe.haiyan.smartrs.R;
import shengzhe.haiyan.smartrs.adapter.TypesAdapter.ViewHolder;
import shengzhe.haiyan.smartrs.model.Menu;
import shengzhe.haiyan.smartrs.model.Menu.MenuList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuByTypeAdapter extends BaseAdapter {

	private Context context;
	private List<Menu.MenuList> listData;
	private MenuList menuResult;
	
	
	public MenuByTypeAdapter(Context context, List<MenuList> listData) {
		super();
		this.context = context;
		this.listData = listData;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = null;
		ViewHolder holder = null;

		menuResult = listData.get(position);
		if (convertView == null) {
			view = LayoutInflater.from(context).inflate(R.layout.foodtype_item,
					null);
			holder = new ViewHolder(view);
			view.setTag(holder);
		} else {
			view = convertView;
		}

		holder = (ViewHolder) view.getTag();
		holder.type_name.setText(menuResult.scenic_name);

//		if (currentPos != null) {
//			if (currentPos == position) {
//				holder.rl_type_bg
//						.setBackgroundResource(R.drawable.listview_selector_false);
//				// holder.img_selected.setVisibility(View.VISIBLE);
//			} else {
//				holder.rl_type_bg
//						.setBackgroundResource(R.drawable.listview_selector2);
//				// holder.img_selected.setVisibility(View.GONE);
//			}
//		}

		return view;
	}

	class ViewHolder {
		// ImageView img_selected;
		TextView type_name;

//		RelativeLayout rl_type_bg;

		public ViewHolder(View v) {
			// img_selected = (ImageView) v.findViewById(R.id.img_selected);
			type_name = (TextView) v.findViewById(R.id.type_name);
//			rl_type_bg = (RelativeLayout) v.findViewById(R.id.rl_type_bg);
		}
	}

}
