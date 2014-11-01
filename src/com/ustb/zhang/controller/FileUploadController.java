package com.ustb.zhang.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ustb.zhang.domain.CodeFile;
import com.ustb.zhang.service.FileTransService;
import com.ustb.zhang.service.impl.FileTransServiceImpl;
import com.ustb.zhang.util.ConventionLibUtil;

public class FileUploadController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			RequestDispatcher de=request.getRequestDispatcher("/pages/fileupload/upload.jsp");
	        de.forward(request, response);
		} catch (Exception err) {
			logger.error(err.getMessage());
			ConventionLibUtil.redirectIndex(response);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
    		FileTransService service = new FileTransServiceImpl();
    		List<CodeFile> files = service.upload(request, response);
    		// At here you can save the file infomation to DB
		} catch (Exception err) {
			logger.error(err.getMessage());
		}
	}

}
