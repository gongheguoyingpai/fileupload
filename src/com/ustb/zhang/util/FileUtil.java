package com.ustb.zhang.util;

import  java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class FileUtil {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
    
	public static boolean create(String path) throws IOException {
		File file = new File(path);
		if (file.exists()) {
			return false;
		}
		return file.createNewFile();
	}
	
	public static boolean delete(String path) {
		File file = new File(path);
		if (file.exists()) {
			return false;
		}
		return file.delete();
	}
	
	
	public static long  size(String path) {
		File file = new File(path);
		return file.length();
	}
	
	// Don't use Java renameTo(), I don't know why it failed, but it always failed and never success
	public boolean rename(String oldPath, String newPath) {
		File oldFile = new File(oldPath);
		File newFile = new File(newPath);
		if (!oldFile.exists() || newFile.exists()) {
			return false;
		}
		try {
		    FileUtils.moveFile(oldFile, newFile);
		} catch (IOException err) {
			logger.error(err.getMessage());
			return false;
		}
		return true;
	}
}
