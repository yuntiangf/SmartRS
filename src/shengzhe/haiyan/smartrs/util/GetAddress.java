package shengzhe.haiyan.smartrs.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import shengzhe.haiyan.smartrs.model.City;
import shengzhe.haiyan.smartrs.model.QLParser;
import shengzhe.haiyan.smartrs.model.City.CityList;

import android.content.Context;

public class GetAddress {

	private static Context mContext;
	public static final String cityFile = "city";
	public static final String provinceFile = "province";
	public static List<CityList> cityList = new ArrayList<City.CityList>();
	public static List<CityList> provinceList = new ArrayList<City.CityList>();

	public static void initialize(Context context) {
		mContext = context;
	}

	public static List<CityList> getCity() {

		String result = getJson(cityFile);
		City city = QLParser.parse(result, City.class);
		cityList.addAll(city.result);
		return cityList;

	}
	
	public static List<CityList> getProvince() {

		String result = getJson(provinceFile);
		City city = QLParser.parse(result, City.class);
		provinceList.addAll(city.result);
		return provinceList;

	}

	private static String getJson(String filename) {
		// TODO Auto-generated method stub

		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(
				mContext.getAssets().open(filename)));
			String line;
			while ((line = bf.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sb.toString();
	}
}
