package com.ustb.zhang.util;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public class JsonUtil {
	
	public static void sendJson(HttpServletResponse response, Object...params) throws Exception {
	    response.setContentType("application/json;charset=utf-8");
	    PrintWriter writer = response.getWriter();
	    Map<String, Object> map = ConventionLibUtil.makeMap(params); 
	    Iterator<String> iter = map.keySet().iterator();
	    String jsonStr = "";
	    while (iter.hasNext()) {
	    	String key = iter.next();
	    	String val = (String)map.get(key);
	    	jsonStr += String.format("\"%s\": \"%s\"", key, val);
	    }
	    jsonStr = "{" + jsonStr + "}";
	    writer.print(jsonStr);
	}
}
