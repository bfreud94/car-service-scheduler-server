package com.freudware.carservicescheduler.util;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.freudware.carservicescheduler.constants.Constants;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
public class JsonUtil {

	/**
	 * Converts String into JsonArray
	 * @param string
	 * @return JsonArray
	 */
	public JsonArray convertStringToJsonArray(String string)	{
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(string);
		JsonArray jsonArray = element.getAsJsonArray();
		return jsonArray;
	}
	
	/**
	 * Filters all appointments based on start date and end date
	 * @param jsonArray
	 * @param start
	 * @param end
	 * @return JsonArray
	 */
	public JsonArray filterByDate(JsonArray jsonArray, LocalDateTime start, LocalDateTime end)	{
        JsonArray json = new JsonArray();
		for(JsonElement jsonElement: jsonArray)	{
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			LocalDateTime currentStart = LocalDateTime.parse(jsonObject.get(Constants.start).getAsString());
            LocalDateTime currentEnd = LocalDateTime.parse(jsonObject.get(Constants.end).getAsString());
			if(currentStart.isAfter(start) && currentEnd.isBefore(end))	{
				json.add(jsonElement);
			}
		}
		return json;
	}
}
