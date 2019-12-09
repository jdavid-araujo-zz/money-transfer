package util;

import java.util.List;

import com.google.gson.Gson;

import spark.ResponseTransformer;

public class JsonUtil {

	public static String toJson(Object object) {
		return new Gson().toJson(object);
	}
	
	public static String toJson(List<Object> object) {
		return new Gson().toJson(object);
	}


	public static ResponseTransformer json() {
		return JsonUtil::toJson;
	}

}
