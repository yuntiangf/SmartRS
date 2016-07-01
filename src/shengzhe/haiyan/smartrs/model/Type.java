package shengzhe.haiyan.smartrs.model;

import java.util.ArrayList;

public class Type extends BaseBean{

	public ArrayList<TypeList> result;
	
	public static class TypeList{
		public int id;
		public int province_id;
		public String province_name;
		public String area_name;
		public int area_id;
	}
	
}
