package com.ustb.zhang.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;

import com.ustb.zhang.domain.CodeFile;


public interface FileTransService {
    
	public List<CodeFile>  upload(HttpServletRequest request, HttpServletResponse response)
			    throws FileUploadException, FileNotFoundException, IOException, NoSuchAlgorithmException;
}
