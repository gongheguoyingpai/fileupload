package com.ustb.zhang.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ustb.zhang.domain.CodeFile;
import com.ustb.zhang.service.FileTransService;
import com.ustb.zhang.util.ConstantUtil;
import com.ustb.zhang.util.CryptUtil;
import com.ustb.zhang.util.FileUtil;
import com.ustb.zhang.util.StringUtil;

public class FileTransServiceImpl implements FileTransService {
	
	// 在文件上传中我们用SHA-1对文件生成签名，主要有两个目的
	// 1.  防止重复上传同一个文件，防止抄袭，我们坚信不会存在完全相同的文件
	// 2.  对文件进行重命名，从而避免重复文件名的问题
	@Override
	public List<CodeFile> upload(HttpServletRequest request, HttpServletResponse response) throws FileUploadException, 
	     FileNotFoundException, IOException, NoSuchAlgorithmException {
	    List<CodeFile> files = null;
    	DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(ConstantUtil.FILEUPLOADPARAMS.TEMPPATH));
		factory.setSizeThreshold(ConstantUtil.FILEUPLOADPARAMS.FILESIZELIMIT);
		ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> list = (List<FileItem>)upload.parseRequest(request);
        String reporitoryBasePath = ConstantUtil.FILEUPLOADPARAMS.REPOPATH;
        Integer pos = 1;
		for(FileItem item : list) {
			CodeFile file = new CodeFile();
			if (item.isFormField() == false) {
				String value = item.getName();
				int start = value.lastIndexOf("\\");
                String fileName = value.substring(start+1);
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
                file.setName(fileName);
                OutputStream out = null;
                InputStream  in = null;
                Map<String, String> filePathPairs = new HashMap<String, String>();
                try {
                	// 为了防止命名会重复，我们会给每个文件名加上一个递增的数字
                	String oldPath = StringUtil.pathJoin(reporitoryBasePath, pos.toString() + fileName);
                    out = new FileOutputStream(oldPath);
				    in = item.getInputStream() ;
				    int length = 0 ;
				    byte [] buf = new byte[1024];
				    String content = "";
				    while( (length = in.read(buf) ) != -1){
					    out.write(buf, 0, length);
					    content += buf;
				    }
				    String newPath = StringUtil.pathJoin(reporitoryBasePath, CryptUtil.sha1Encrypt(content)+suffixName);
				    file.setPath(newPath);
				    filePathPairs.put(oldPath, newPath);
				    ++pos;
                } finally {
				    in.close();
				    out.close();
				    // 将文件重命名移动到这里,因为流不关闭是无法对文件进行移动的
				    Iterator<String> iter = filePathPairs.keySet().iterator();
				    FileUtil fileUtil = new FileUtil();
				    while (iter.hasNext()) {
				    	String oldPath = iter.next();
				    	String newPath = filePathPairs.get(oldPath);
				    	fileUtil.rename(oldPath, newPath);
				    }
                }
			}
		}
		return files;
	}
}
