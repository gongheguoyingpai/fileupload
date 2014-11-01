package com.ustb.zhang.util;

import java.io.File;

public class StringUtil {
	
	public static String pathJoin(String path, String fileName) {
		File file = new File(path, fileName);
		return file.getPath();
	}

}
