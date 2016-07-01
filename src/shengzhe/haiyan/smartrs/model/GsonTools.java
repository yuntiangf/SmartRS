package shengzhe.haiyan.smartrs.model;

import com.google.gson.Gson;

public class GsonTools {

	public static <T> T changeGsonToBean(String gsonString, Class<T> cls) {
		Gson gson = new Gson();
		T t = gson.fromJson(gsonString, cls);
		return t;
	}
}
