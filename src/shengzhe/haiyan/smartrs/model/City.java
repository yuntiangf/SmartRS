package shengzhe.haiyan.smartrs.model;

import java.util.ArrayList;

public class City extends BaseBean {

	public ArrayList<CityList> result;

	public static class CityList {

		public String id;
		public String father_id;
		public String name;
	}
}
