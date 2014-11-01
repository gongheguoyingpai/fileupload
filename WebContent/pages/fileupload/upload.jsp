<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
  <title>文件上传</title>
  <link type="text/css" rel="stylesheet" href="<%=basePath%>/resources/css/bootstrap/css/bootstrap.css" />
  <link href="<%=basePath%>/resources/css/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="http://localhost:8080/CodeManager/resources/css/bootstrap/css/theme.css" />
<script type="text/javascript" src="<%=basePath%>/resources/js/fileupload/js/plupload.full.min.js"></script>
</head>
<body style="font: 13px Verdana; background: #eee; color: #333">
<!-- You Need Start -->
  <ul id="filelist"></ul>
  <br />
  <div id="container">
    <a id="browse" href="javascript:;" class="btn btn-xs btn-success">
      + 添加文件
    </a>
    <a id="start-upload" href="javascript:;" class="btn btn-xs btn-success">
           开始上传
    </a>
    <a id="clean-list" href="javascript:;" class="btn btn-xs btn-success" onclick="clear_filelist()">
      X  清空上传列表
    </a>
  </div>
  <script type="text/javascript">
  var context = "<%=basePath%>";
  var uploader = new plupload.Uploader({
    browse_button: 'browse',
    url: context + '/fileupload',
    file_data_name: "file",
    filters: {
    	max_file_size: '5mb'
    }
  });
  uploader.init();
  
  uploader.bind('FilesAdded', function(up, files) {
	var html = '';
	plupload.each(files, function(file) {
	  html += '<li id="' + file.id + '">' + file.name 
	       + ' (' + plupload.formatSize(file.size) 
	       + ") <b></b><a href='javascript:;' title='删除' onclick='delete_file(\"delete_" 
	       + file.id 
	       + "\")' class='btn btn-xs btn-danger'>X</a></li>";
	});
	document.getElementById('filelist').innerHTML += html;
  });
  
  uploader.bind('UploadProgress', function(up, file) {
	document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = 
		'<span>' + file.percent + "%</span>";
	$("#delete_"+file.id).hide()
  });
 
  document.getElementById('start-upload').onclick = function() {
	uploader.start();
  };
  
  function clear_filelist() {
	$("#filelist").empty();
	uploader.splice()
  }
  
  function delete_file(id) {
	  id = id.substring("delete_".length);
	  $("#"+id).remove();
	  uploader.removeFile(id);
  }
  </script>
  <!-- You Need End -->
  <script src="<%=basePath%>/resources/js/jquery/jquery.js"></script>
</body>
</html>
