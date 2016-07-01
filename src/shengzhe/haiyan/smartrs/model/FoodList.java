package shengzhe.haiyan.smartrs.model;

import java.util.ArrayList;

public class FoodList extends BaseBean {

	public ArrayList<FoodListResult> result;

	public static class FoodListResult {
		public String id;
		public String imageRealPath;
		public String imageRelativePath;
		public String name;
		public String tip;
	}
}
