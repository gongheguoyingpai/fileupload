package com.ustb.zhang.util;

public class ConstantUtil {
    
	// 文件读写执行的权限
	public static final int  READABLE    = 1 << 1;
	public static final int  WRITEABLE   = 1 << 2;
	public static final int  EXECUTEABLE = 1 << 3;
	
	public class SpecialPages {
		public static final String HOMEPAGE  = "/pages/index.jsp";
		public static final String INDEXPAGE = "";
	}
	
	public class FILEUPLOADPARAMS {
	    public static final String TEMPPATH = "G://";
	    public static final String REPOPATH = "D://fileupload";
		// 最大允许文件为20MB
	    public static final int  FILESIZELIMIT = 20 * 1024 * 1024;
	}
	
	public class Encrypt {
		public static final String SHA1 = "sha1";
		public static final String MD5 = "md5";
	}
}
