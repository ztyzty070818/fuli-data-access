package io.sugo.access;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by zty on 19-1-25
 */
public class ColumnTypeUtil {
	public static Map<String, String> getNameTypeMap() throws IOException {
		Map<String, String> map = new LinkedHashMap<>();

		String colStr = FileUtils.readFileToString(new File("resource/column/access"));

		for (String str : colStr.split("\n")) {
			String[] strs = str.split("\t");
			String name = strs[0].trim();
			String type = strs[1].trim();
			if (name.endsWith("_time") || name.endsWith("_date")) {
				map.put(name, "date");
			} else if (type.equals("int")) {
				if (name.endsWith("_count") || name.endsWith("weekday") || name.endsWith("hour") || name.endsWith("score")) {
					map.put(name, type);
				} else {
					map.put(name, "string");
				}
			} else if (type.equals("bigint")) {
				map.put(name, "string");
			} else {
				map.put(name, strs[1].trim());
			}
		}
		return map;
	}

	public static List<Map<String, String>> getDimensionList() throws IOException {
		List<Map<String, String>> dimensionList = new ArrayList<>();
		Map<String, String> nameTypeMap = getNameTypeMap();
		for(String dimension : nameTypeMap.keySet()) {
			Map<String, String> map = new HashMap<>();
			String type = nameTypeMap.get(dimension);
			map.put("name", dimension);
			map.put("type", type);
			if(type.equals("date")) {
				map.put("format",  "millis");
			}
			dimensionList.add(map);
		}
		return dimensionList;
	}


	public static void writeToAccessJson() throws IOException {
		String jsonPath = String.format("resource/task/access_record.json");

		File jsonFile = new File(jsonPath);

		String accessJson = FileUtils.readFileToString(jsonFile);
		JSONObject jsonObject = new JSONObject(accessJson);

		JSONObject parseSpec;

		parseSpec = jsonObject.getJSONObject("spec").getJSONObject("dataSchema").getJSONObject("parser").getJSONObject("parseSpec");
		parseSpec.put("columns", new JSONArray(getNameTypeMap().keySet()));

		JSONObject dimensionsSpec = parseSpec.getJSONObject("dimensionsSpec");
		dimensionsSpec.put("dimensions", new JSONArray(getDimensionList()));

		FileUtils.writeStringToFile(jsonFile, jsonObject.toString(2));
	}

	public static void main(String[] args) throws IOException {
		writeToAccessJson();
	}
}
