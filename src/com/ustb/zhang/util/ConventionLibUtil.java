package com.ustb.zhang.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public class ConventionLibUtil {
	
	public static Map<String, Object> makeMap(Object...params) throws Exception {
		int len = params.length;
		if ((len & 1) != 0) {
		    throw new Exception("makeEntry params参数个数应该为偶数!");
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		for (int i = 0; i < len; i += 2) {
			Object keyObj = params[i];
			Object valObj = params[i+1];
			if (!(keyObj instanceof String)) {
				throw new Exception("makeEntry key value应该为String类型!");
			}
			attrs.put((String)keyObj, valObj);
		}
		return attrs;
	}
	
	public static void redirectIndex(HttpServletResponse response) throws IOException {
		response.sendRedirect(ConstantUtil.SpecialPages.INDEXPAGE);
	}

}
