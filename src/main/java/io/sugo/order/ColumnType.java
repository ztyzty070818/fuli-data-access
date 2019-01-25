package io.sugo.order;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by zty on 18-10-19
 */
public class ColumnType {

	private String colStr;
	private String name;
	private String type;

	public ColumnType(String name, String type, String colStr) {
		this.name = name;
		this.type = type;
		this.colStr = colStr;
	}

	public void writeToAccessJson() throws IOException {
		String jsonPath = String.format("resource/task/%s-%s.json", name, type);

		File jsonFile = new File(jsonPath);

		String accessJson = FileUtils.readFileToString(jsonFile);
		JSONObject jsonObject = new JSONObject(accessJson);

		JSONObject parseSpec;
		if(type.equals("update")) {
			parseSpec = jsonObject.getJSONObject("dataSchema").getJSONObject("parser").getJSONObject("parseSpec");
		} else {
			parseSpec = jsonObject.getJSONObject("spec").getJSONObject("dataSchema").getJSONObject("parser").getJSONObject("parseSpec");
			parseSpec.put("columns", new JSONArray(getNameTypeMap().keySet()));
		}

		JSONObject dimensionsSpec = parseSpec.getJSONObject("dimensionsSpec");
		dimensionsSpec.put("dimensions", new JSONArray(getDimensionList()));

		FileUtils.writeStringToFile(jsonFile, jsonObject.toString(2));
	}

	public Map<String, String> getNameTypeMap() {
		Map<String, String> map = new LinkedHashMap<>();

		for(String str : colStr.split("\n")) {
			String[] strs = str.split("\t");
			String name = strs[0].trim();
			String type = strs[1].trim();
			if (name.endsWith("_time") && !name.endsWith("express_time")) {
				map.put(name, "date");
			} else if (type.equals("int")) {
				if (name.endsWith("verify_times") || name.endsWith("amount")) {
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

	public  List<Map<String, String>> getDimensionList() {
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
}
